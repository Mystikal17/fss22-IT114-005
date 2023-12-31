package Project.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.Phase;
import Project.common.WordList;
import Project.common.Grid;
public class Room implements AutoCloseable{
	protected static Server server;// used to refer to accessible server functions
	private String name;
	private List<ServerThread> clients = new ArrayList<ServerThread>();
	private boolean isRunning = false;
	private List<String> wordList;
    private int currentWordIndex;
	private Timer roundTimer;
    private int correctGuessCount = 0;
	private ServerThread drawer;
	private Grid grid;
	private List<ServerThread> readyPlayers = new ArrayList<>();
	private int roundTime = 0;
	private int maxPoints = 100;
	private Timer pointTimer;
	private Phase currentPhase = Phase.READY;

	// Commands
	private final static String COMMAND_TRIGGER = "/";
	private final static String CREATE_ROOM = "createroom";
	private final static String JOIN_ROOM = "joinroom";
	private final static String DISCONNECT = "disconnect";
	private final static String LOGOUT = "logout";
	private final static String LOGOFF = "logoff";
	private final static String START_GAME = "startgame";
	private final static String ROUND_OVER = "roundover";
	private final static String GAME_OVER = "gameover";
	private final static String READY = "ready";
	private final static String GUESS = "guess";


	public Room(String name) {
		this.name = name;
		isRunning = true;
		wordList = WordList.getWordList();
        Collections.shuffle(wordList); 
        currentWordIndex = 0;
		this.grid = new Grid();
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
					// slight delay to let potentially new client to finish
					// binding input/output streams
					// comment out the Thread.sleep to see what happens
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
		// we don't need to broadcast it to the server
		// only to our own Room
		if (clients.size() > 0) {
			//sendMessage(client, "left the room");
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

	private boolean isAllPlayersReady() {
        return readyPlayers.size() == clients.size();
    }

	private void handleReadyCommand(ServerThread client) {
        if (!readyPlayers.contains(client)) {
            readyPlayers.add(client);
            sendMessage(client, "You are ready!");
            if (isAllPlayersReady()) {
                startGame();
            }
        } else {
            sendMessage(client, "You are already ready!");
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
						break;
					case GAME_OVER:
						handleEndOfGame();
						break;
					case ROUND_OVER:
						endRound();
						break;
					case READY:
						handleReadyCommand(client);
						
						break;
					case GUESS:
						processGuessCommand(client, comm2);
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
	// end command helper methods

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
		if (roundTimer != null) {
            roundTimer.cancel();
            roundTimer = null;
        }
        server.removeRoom(this);
        server = null;
        isRunning = false;
        clients = null;
    }
	
	private void handleReadyPhase() {
			if (currentPhase == Phase.READY) {
				if (isAllPlayersReady()) {
					startGame();
				}
			}
		}
		
		

	private boolean isRoundOver = false;
	public void endRound() {
		if (isGameStarted && !isRoundOver) {
            isRoundOver = true;
            String endOfRoundMessage = "End of Round. Get ready for the next one!";
    		sendMessage(null, endOfRoundMessage);
			schedulePointTimer();
			showEndOfRoundInfo();
        } 
		switch (currentPhase) {
			case READY:
				break;
			case PREP:
				startPrepPhase();
				currentPhase = Phase.GUESS;
				// Additional logic for GUESS phase setup
				break;
			case GUESS:
				startGuessPhase();
				currentPhase = Phase.END;
				// Additional logic for END phase setup
				break;
			case END:
				// Handle the end of the game or any cleanup
				// You can reset the game or close the room here
				break;
		}

		// Handle transitions specific to each phase
		switch (currentPhase) {
			case GUESS:
				// Additional setup for the GUESS phase
				break;
			case END:
				// Handle the end of the game or any cleanup
				break;
		}
		if (currentWordIndex < wordList.size()) {
            String nextWord = wordList.get(currentWordIndex);
            currentWordIndex++;
            drawer = selectDrawer();
            if (drawer != null) {
                String drawerMessage = "You are the drawer! The word to draw is: " + nextWord;
                drawer.sendMessage("Server", drawerMessage);
                String guesserMessage = "Guess the word: " + getBlankWord(nextWord);
                sendMessage(drawer, guesserMessage);
            }
        } else {
            handleEndOfGame();
        }
	
	}

	//fss22, Nov 15 2023
	private final Timer prepTimer = new Timer();
	private void startPrepPhase() {
		if (currentPhase == Phase.PREP) {
			if (drawer != null) {
				// Notify the drawer about their role
				drawer.sendMessage("Server", "You are the drawer! You have 45 seconds to draw.");
	
				// Notify other players about the drawer and the waiting phase
				for (ServerThread player : clients) {
					if (player != drawer) {
						player.sendMessage("Server", "The drawer is drawing. Please wait.");
					}
				}
	
				// Start a 45-second timer for the drawer to draw
				prepTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						prepTimer.cancel();
						startGuessPhase(); // Move to the guess phase after 45 seconds
					}
				}, 45000); // 45 seconds
			}
		}
	}

	
	private final Timer guessTimer = new Timer(); 
	private void startGuessPhase() {
		if (currentPhase == Phase.PREP) {
			currentPhase = Phase.GUESS;
	
			// Notify players about the start of the guess phase
			sendMessage(null, "Guess phase started! Other players, start guessing.");
	
			// Start a 45-second timer for other players to guess
			guessTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					guessTimer.cancel();
					startEndPhase(); // Move to the end phase after guess time
				}
			}, 45000); // 45 seconds for guessing
		}
	}

	private void startEndPhase() {
		if (currentPhase == Phase.GUESS) {
			currentPhase = Phase.END;
	
			// Additional logic for the END phase setup if needed
			// ...
	
			sendMessage(null, "Time's up for guessing! Moving to the end phase.");
		}
	}

	private void showEndOfRoundInfo() {
		// Display the word
		String currentWord = wordList.get(currentWordIndex - 1);
		sendMessage(null, "The word was: " + currentWord);
	
		// Display points earned by each player
		for (ServerThread player : clients) {
			sendMessage(null, player.getClientName() + " earned " + maxPoints + " points!");
			// Assuming all players get the same points at the end of the round
		}
	}

	private void schedulePointTimer() {
        pointTimer = new Timer();
        pointTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updatePoints();
            }
        }, 0, 5000); // Decrease points every 5 seconds
    }

	private void updatePoints() {
        if (maxPoints > 0) {
            maxPoints -= 10;
            sendMessage(null, "Points updated! Max points: " + maxPoints);
        }
    }

	private String getBlankWord(String word) {
		StringBuilder blankWord = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			if (Character.isLetter(currentChar)) {
				// Replace letters with underscores
				blankWord.append("_");
			} else {
				// Keep non-letter characters as they are (e.g., spaces, punctuation)
				blankWord.append(currentChar);
			}
		}
		return blankWord.toString();
	}

	private void scheduleNextRound() {
        isRoundOver = false;
    	correctGuessCount = 0;
    	Timer delayTimer = new Timer();
    delayTimer.schedule(new TimerTask() {
        @Override
        public void run() {
            delayTimer.cancel(); 
            scheduleNextRound(); 
        }
    }, 10000); 
    }

	private void startNextRound() {
        isRoundOver = false;
		correctGuessCount = 0;
		scheduleNextRound();
        
    }
	
	public void handleEndOfGame() {
		if (isGameStarted) {
            isGameStarted = false;
            String endOfGameMessage = "Game Over! Thank you for playing!";
    		sendMessage(null, endOfGameMessage);
        } 
	}

	private boolean isGameStarted = false;
    public void startGame() {
		if (!isGameStarted) {
            isGameStarted = true;
			currentPhase = Phase.PREP;
            String startGameMessage = "The game has started!";
    		sendMessage(null, startGameMessage);
        } 
    }

	public synchronized void playerGuessedCorrectly() {
        correctGuessCount++;
        if (correctGuessCount == clients.size()) {
            if (roundTimer != null) {
                roundTimer.cancel();
                roundTimer = null;
            }
            startNextRound();
        }
    }

	
	private ServerThread selectDrawer() {
        if (clients.size() > 0) {
            Collections.shuffle(clients);
            return clients.get(0);
        }
        return null;
		
    }

	public synchronized void updateGrid(Grid newGrid) {
		grid.setBoard(newGrid.getBoard());
		sendMessage(null, "Grid updated!"); 
	}
	private void processGuessCommand(ServerThread client, String[] comm2) {
		if (drawer == client || comm2.length < 2) {
			client.sendMessage("Server: ", "Invalid guess command.");
			return;
		}
	
		String guessedWord = comm2[1].toLowerCase();
	
		if (currentWordIndex <= 0 || currentWordIndex > wordList.size()) {
			client.sendMessage("Server: ","No word to guess. The game might not be in progress.");
			return;
		}
	
		String actualWord = wordList.get(currentWordIndex - 1).toLowerCase();
	
		if (guessedWord.equals(actualWord)) {
			// Correct guess
			client.sendMessage("Server: ", "Congratulations! You guessed the word correctly!");
			playerGuessedCorrectly();
		} else {
			// Incorrect guess
			client.sendMessage("Server: ", "Incorrect guess. Try again!");
		}
	}

	private boolean isReadyCommand(String command) {
		return command.equalsIgnoreCase(READY);
	}
}

           