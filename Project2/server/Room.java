package Project2.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.Timer;

public class Room implements AutoCloseable{
	protected static Server server;// used to refer to accessible server functions
	private String name;
	private List<ServerThread> clients = new ArrayList<ServerThread>();
	private boolean isRunning = false;
	private int remainingPlayers;
    private Map<ServerThread, String> playerChoices;
    private List<ServerThread> spectators;
	private Map<ServerThread, Integer> playerScores = new HashMap<>();
	
	// Commands
	private final static String COMMAND_TRIGGER = "/";
	private final static String CREATE_ROOM = "createroom";
	private final static String JOIN_ROOM = "joinroom";
	private final static String DISCONNECT = "disconnect";
	private final static String LOGOUT = "logout";
	private final static String LOGOFF = "logoff";
	private final static String START_GAME = "startgame";

	public Room(String name) {
		this.name = name;
		isRunning = true;
		remainingPlayers = 0;
        playerChoices = new HashMap<>();
        spectators = new ArrayList<>();
	}

	private void info(String message) {
		System.out.println(String.format("Room[%s]: %s", name, message));
	}

	public String getName() {
		return name;
	}

	protected synchronized void addClient(ServerThread client) {
		if (!isRunning) {
			return;
		}
		client.setCurrentRoom(this);
		if (clients.indexOf(client) > -1) {
			info("Attempting to add a client that already exists");
		} else {
			clients.add(client);
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//sendMessage(client, "joined the room " + getName());
					sendConnectionStatus(client, true);
				}
			}.start();

		}
	}

	protected synchronized void removeClient(ServerThread client) {
		if (!isRunning) {
			return;
		}
		clients.remove(client);
		if (clients.size() > 0) {
			sendConnectionStatus(client, false);
		}
		checkClients();
	}

	private void checkClients() {
		// Cleanup if room is empty and not lobby
		if (!name.equalsIgnoreCase("lobby") && clients.size() == 0) {
			close();
		}
	}

	private boolean processCommands(String message, ServerThread client) {
		boolean wasCommand = false;
		try {
			if (message.startsWith(COMMAND_TRIGGER)) {
				String[] comm = message.split(COMMAND_TRIGGER);
				String part1 = comm[1];
				String[] comm2 = part1.split(" ");
				String command = comm2[0];
				String roomName;
				wasCommand = true;
				switch (command) {
					case CREATE_ROOM:
						roomName = comm2[1];
						Room.createRoom(roomName, client);
						break;
					case JOIN_ROOM:
						roomName = comm2[1];
						Room.joinRoom(roomName, client);
						break;
					case DISCONNECT:
					case LOGOUT:
					case LOGOFF:
						Room.disconnectClient(client, this);
						break;
					case START_GAME:
						startGame();
						wasCommand = true;
						break;
					default:
						wasCommand = false;
						break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wasCommand;
	}

	// Command helper methods
	protected static void createRoom(String roomName, ServerThread client) {
		if (server.createNewRoom(roomName)) {
			//server.joinRoom(roomName, client);
			Room.joinRoom(roomName, client);
		} else {
			client.sendMessage("Server", String.format("Room %s already exists", roomName));
		}
	}

	protected static void joinRoom(String roomName, ServerThread client) {
		if (!server.joinRoom(roomName, client)) {
			client.sendMessage("Server", String.format("Room %s doesn't exist", roomName));
		}
	}

	protected static void disconnectClient(ServerThread client, Room room) {
		client.setCurrentRoom(null);
		client.disconnect();
		room.removeClient(client);
	}

	protected synchronized void sendMessage(ServerThread sender, String message) {
		if (!isRunning) {
			return;
		}
		info("Sending message to " + clients.size() + " clients");
		if (sender != null && processCommands(message, sender)) {
			// it was a command, don't broadcast
			return;
		}
		
		String from = (sender == null ? "Room" : sender.getClientName());
		Iterator<ServerThread> iter = clients.iterator();
		while (iter.hasNext()) {
			ServerThread client = iter.next();
			boolean messageSent = client.sendMessage(from, message);
			if (!messageSent) {
				handleDisconnect(iter, client);
			}
		}
	}
	protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected){
		Iterator<ServerThread> iter = clients.iterator();
		while (iter.hasNext()) {
			ServerThread client = iter.next();
			boolean messageSent = client.sendConnectionStatus(sender.getClientName(), isConnected);
			if (!messageSent) {
				handleDisconnect(iter, client);
			}
		}
	}
	private void handleDisconnect(Iterator<ServerThread> iter, ServerThread client){
		iter.remove();
		info("Removed client " + client.getClientName());
		checkClients();
		sendMessage(null, client.getClientName() + " disconnected");
	}
	public void close() {
		server.removeRoom(this);
		server = null;
		isRunning = false;
		clients = null;
	}

	public synchronized void startGame() {
        remainingPlayers = clients.size();
        playerChoices.clear();
        spectators.clear();

        // Inform players to make their choices
        sendMessage(null, "New round starting! Please make your choice: Rock, Paper, or Scissors.");

        // Set a timer for 10 seconds for players to make their choices
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endRound();
            }
        }, 10000);
    }

    private void endRound() {
        if (remainingPlayers <= 1) {
            endGame();
            return;
        }

        // Determine winners, losers, and spectators
        Map<String, List<ServerThread>> choices = new HashMap<>();
        for (ServerThread client : clients) {
            String choice = playerChoices.get(client);
            if (choice == null || choice.isEmpty()) {
                spectators.add(client); // Add non-participating players as spectators
                remainingPlayers--; // Decrease count for players who didn't make a choice
				playerScores.put(client, playerScores.getOrDefault(client, 0) + 1);
            } else {
                choices.computeIfAbsent(choice, k -> new ArrayList<>()).add(client);
            }
        }

		StringBuilder scoresMessage = new StringBuilder("Scores:\n");
		for (Map.Entry<ServerThread, Integer> entry : playerScores.entrySet()) {
			scoresMessage.append(entry.getKey().getClientName()).append(": ").append(entry.getValue()).append("\n");
		}
		sendMessage(null, scoresMessage.toString());
	
        for (Map.Entry<String, List<ServerThread>> entry : choices.entrySet()) {
            List<ServerThread> players = entry.getValue();
            if (players.size() > 1) {
                sendMessage(null, "Tie between: " + players.stream().map(ServerThread::getClientName).toList());
            } else {
                ServerThread winner = players.get(0);
                sendMessage(null, winner.getClientName() + " wins!");
                remainingPlayers--; // Decrease count for eliminated players
                spectators.add(winner); // Add winner as a spectator
            }
        }
		calculatePlayerComparisons();	
        startGame(); // Start a new round
    }

    private void endGame() {
        if (remainingPlayers == 1) {
            sendMessage(null, "Game ended! Winner is: " + clients.get(0).getClientName());
        } else {
            sendMessage(null, "Game ended! No winner.");
        }

        // Make all remaining players spectators at the end of the game
        for (ServerThread client : clients) {
            if (!spectators.contains(client)) {
                spectators.add(client);
            }
        }

        // Handle spectators watching the game
        for (ServerThread spectator : spectators) {
            // Implement logic for spectators, e.g., sending messages or limiting actions
        }

        // Clear player choices and spectators
        playerChoices.clear();
        spectators.clear();
        remainingPlayers = 0;

		StringBuilder finalScoresMessage = new StringBuilder("Final Scores:\n");
    for (Map.Entry<ServerThread, Integer> entry : playerScores.entrySet()) {
        finalScoresMessage.append(entry.getKey().getClientName()).append(": ").append(entry.getValue()).append("\n");
    }
    sendMessage(null, finalScoresMessage.toString());
    }
	private void calculatePlayerComparisons() {
		int totalPlayers = clients.size();
		List<ServerThread> playerList = new ArrayList<>(clients);
	
		for (int i = 0; i < totalPlayers; i++) {
			ServerThread currentPlayer = playerList.get(i);
			ServerThread nextPlayer = playerList.get((i + 1) % totalPlayers); // Wrap around for last player
	
			String currentChoice = playerChoices.get(currentPlayer);
			String nextChoice = playerChoices.get(nextPlayer);
	
			if (currentChoice == null || nextChoice == null) {
				// Handle case where one or both players didn't make a choice
				// This could be spectator(s) or players who didn't select an option
				continue;
			}
	
			// Logic for comparing choices and determining the result
			if (currentChoice.equals(nextChoice)) {
				sendMessage(null, currentPlayer.getClientName() + " vs " + nextPlayer.getClientName() + ": Tie!");
			} else if ((currentChoice.equals("Rock") && nextChoice.equals("Scissors")) ||
					   (currentChoice.equals("Paper") && nextChoice.equals("Rock")) ||
					   (currentChoice.equals("Scissors") && nextChoice.equals("Paper"))) {
				sendMessage(null, currentPlayer.getClientName() + " vs " + nextPlayer.getClientName() + ": " + currentPlayer.getClientName() + " wins!");
				playerScores.put(currentPlayer, playerScores.getOrDefault(currentPlayer, 0) + 1);
			} else {
				sendMessage(null, currentPlayer.getClientName() + " vs " + nextPlayer.getClientName() + ": " + nextPlayer.getClientName() + " wins!");
				playerScores.put(nextPlayer, playerScores.getOrDefault(nextPlayer, 0) + 1);
			}
		}
	}

}