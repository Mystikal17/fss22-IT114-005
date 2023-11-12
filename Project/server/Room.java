package Project.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Project.common.DrawingOrder;
import Project.common.Payload;
import Project.common.WordList;
import Project.common.Grid;
public class Room implements AutoCloseable{
	protected static Server server;// used to refer to accessible server functions
	private String name;
	private List<ServerThread> clients = new ArrayList<ServerThread>();
	private boolean isRunning = false;
	private boolean isRoundActive = false;
	private int roundTimeInSeconds = 30;
	private Thread roundThread;
	private boolean isGameRunning = false;
	private ServerThread currentDrawer;
	private boolean isDrawingTurn;
	private DrawingOrder drawingOrder = new DrawingOrder();
	private Grid grid;

	// Commands
	private final static String COMMAND_TRIGGER = "/";
	private final static String CREATE_ROOM = "createroom";
	private final static String JOIN_ROOM = "joinroom";
	private final static String DISCONNECT = "disconnect";
	private final static String LOGOUT = "logout";
	private final static String LOGOFF = "logoff";		 
	private final static String START_GAME = "startgame";
	private final static String START_ROUND = "startround";
	private final static String ROUND_OVER = "roundover";
	private final static String DRAWING = "drawing";
	private final static String GAME_OVER = "gameover";
	private final static String READY = "ready";
	
	public void startRound(){
		if(!isRoundActive){
			isRoundActive = true;
			roundThread = new Thread(() -> {
				try {
					currentDrawer = drawingOrder.getNextDrawer(); 
					sendMessage(null, "Round started!" + currentDrawer.getClientName() + "is drawing.");
					
					isDrawingTurn = true;
					for (int seconds = roundTimeInSeconds; seconds > 0; seconds--) {
						if (areAllClientsGuessed()) {
							sendMessage(null, "All players have guessed correctly!");
							endRound();
							startRound(); //automatically goes to the next round.
							return; // Exit the thread
						}
						Thread.sleep(1000);
					}
					sendMessage(null, "Round ended! The word was: " + getRandomWord());
					isDrawingTurn = false;
					currentDrawer = null;
					endRound();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			roundThread.start();
		}
	}

	private boolean areAllClientsGuessed() {
		for (ServerThread client : clients) {
			if (!client.hasGuessedCorrectly()) {
				return false;
			}
		}
		return true;
	}

	public void endRound(){
		isRoundActive = false;
		if(roundThread != null){
			roundThread.interrupted();
		}
		if (!WordList.getWordList().isEmpty()) {
			startRound();
		} else {
			handleEndOfGame();
		}
	}
	
	private void handleEndOfGame() {
		sendMessage(null, "Game over! Thank you for playing.");
		close();
	}

	private String getRandomWord() {
    	List<String> words = WordList.getWordList();
    	int randomIndex = (int) (Math.random() * words.size());
    	return words.get(randomIndex);
	}

	private void handleReady(ServerThread client) {
    	client.setReady(true);
    	if (areAllClientsReady()) {
       		startGame();
    	}
	}

	private void handleDrawing(ServerThread client, String[] commandParts){
		if (isDrawingTurn && client == currentDrawer) {
        	String drawingAction = String.join(" ", commandParts).substring(DRAWING.length() + 1);
        	sendMessage(null, currentDrawer.getClientName() + " is drawing: " + drawingAction);
        	informGuessingClients(currentDrawer, drawingAction);
    	} else {
        	sendMessage(client, "It's not your turn to draw.");
    	}
	}

	private void informGuessingClients(ServerThread drawingPlayer, String drawingAction) {
    	Iterator<ServerThread> iter = clients.iterator();
    	while (iter.hasNext()) {
        	ServerThread client = iter.next();
        	if (client != drawingPlayer) {
            	boolean messageSent = client.sendMessage("Server", drawingPlayer.getClientName() + " is drawing: " + drawingAction);
            	if (!messageSent) {
                	handleDisconnect(iter, client);
            	}
        	} else {
				client.sendMessage("Server", "The word is: " + getRandomWord());
			}
    	}
	}

	private boolean areAllClientsReady() {
    	for (ServerThread client : clients) {
       		if (!client.isReady()) return false;
    	}
    return true;
	}

	public void startGame() {
		if (!isGameRunning && areAllClientsReady()) {
			isGameRunning = true;
			sendMessage(null, "Game started! Let the fun begin!");
		
			startRound();
		} else {
			sendMessage(null, "Not all players are ready. Cannot start the game.");
		}
	}

	public Grid getGrid(){
		return grid;
	}

	public void clearGrid(){
		grid.clearBoard();
		sendMessage(null, "The Board has been Cleared.");

	}

	public Room(String name) {
		this.name = name;
		isRunning = true;
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
			drawingOrder.addClient(client);
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
						if(areAllClientsReady()){
							startGame();
						} else {
							sendMessage(null, "Not all Players are ready.");
						}
						break;
					case START_ROUND:
						startRound();
						break;
					case ROUND_OVER:
						endRound();
						break;
					case GAME_OVER:
						handleEndOfGame();
						break;
					case DRAWING:
						handleDrawing(client, comm2);
						break;
					case READY:
						handleReady(client);
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
		server.removeRoom(this);
		server = null;
		isRunning = false;
		clients = null;
	}
}
