package Project2.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import Project2.common.Constants;
import Project2.common.Phase;
import Project2.common.Player;
import Project2.common.TimedEvent;

import java.util.logging.Logger;

public class GameRoom extends Room {
    Phase currentPhase = Phase.READY;
    private static Logger logger = Logger.getLogger(GameRoom.class.getName());
    private TimedEvent readyTimer = null;
    private static ConcurrentHashMap<Long, ServerPlayer> players = new ConcurrentHashMap<Long, ServerPlayer>();
    private static Map<Long, String> playerChoices = new HashMap<>();
    List<ServerPlayer> spectators = new ArrayList<>();
    Random random = new Random();
       


    public GameRoom(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public enum Choice {
        ROCK,
        PAPER,
        SCISSORS
    }

    @Override
    protected void addClient(ServerThread client) {
        logger.info("Adding client as player");
        players.computeIfAbsent(client.getClientId(), id -> {
            ServerPlayer player = new ServerPlayer(client);
            super.addClient(client);
            syncGameState(client);
            logger.info(String.format("Total clients %s", clients.size()));// change visibility to protected
            return player;
        });
    }

    private void syncGameState(ServerThread incomingClient) {
        incomingClient.sendPhaseSync(currentPhase);
    }

    protected void setReady(ServerThread client) {
        logger.info("Ready check triggered");
        if (currentPhase != Phase.READY) {
            logger.warning(String.format("readyCheck() incorrect phase: %s", Phase.READY.name()));
            return;
        }
        if (readyTimer == null) {
            sendMessage(null, "Ready Check Initiated, 30 seconds to join");
            readyTimer = new TimedEvent(30, () -> {
                readyTimer = null;
                readyCheck(true);
            });
        }
        players.values().stream().filter(p -> p.getClient().getClientId() == client.getClientId()).findFirst()
                .ifPresent(p -> {
                    p.setReady(true);
                    logger.info(String.format("Marked player %s[%s] as ready", p.getClient().getClientName(), p
                            .getClient().getClientId()));
                    syncReadyStatus(p.getClient().getClientId());
                });
        readyCheck(false);
    }

    private void readyCheck(boolean timerExpired) {
        if (currentPhase != Phase.READY) {
            return;
        }
        long numReady = players.values().stream().filter(ServerPlayer::isReady).count();
        if (numReady >= Constants.MINIMUM_PLAYERS) {

            if (timerExpired) {
                sendMessage(null, "Ready Timer expired, starting session");
                start();
            } else if (numReady >= players.size()) {
                sendMessage(null, "Everyone in the room marked themselves ready, starting session");
                if (readyTimer != null) {
                    readyTimer.cancel();
                    readyTimer = null;
                }
                start();
            }

        } else {
            if (timerExpired) {
                resetSession();
                sendMessage(null, "Ready Timer expired, not enough players. Resetting ready check");
            }
        }
    }

    private void start() {
        updatePhase(Phase.SELECTION);
        startSelectionPhase();
        // TODO example
        sendMessage(null, "Session started");
        new TimedEvent(30, () -> resetSession())
                .setTickCallback((time) -> {
                    sendMessage(null, String.format("Example running session, time remaining: %s", time));
                });
    }

    private synchronized void resetSession() {
        players.values().stream().forEach(p -> p.setReady(false));
        updatePhase(Phase.READY);
        sendMessage(null, "Session ended, please intiate ready check to begin a new one");
    }

    private void updatePhase(Phase phase) {
        if (currentPhase == phase) {
            return;
        }
        currentPhase = phase;
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendPhaseSync(currentPhase);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    protected void handleDisconnect(ServerPlayer player) {
        if (player == null) {
            logger.severe("handleDisconnect() player is null");
            return;
        }
        if (players.containsKey(player.getClient().getClientId())) {
            players.remove(player.getClient().getClientId());
        }
        super.handleDisconnect(null, player.getClient());
        logger.info(String.format("Total clients %s", clients.size()));
        if (players.isEmpty()) {
            close();
        }
    }

    private void syncReadyStatus(long clientId) {
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendReadyStatus(clientId);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    public static void makeChoice(long clientId, String choice) {
        playerChoices.put(clientId, choice);
        if (playerChoices.size() == 2) {
            calculateResult();
        }
    }

    private static void calculateResult() {
        String[] choices = playerChoices.values().toArray(new String[0]);

        if (choices[0].equals(choices[1])) {
            sendMessageToAll("It's a tie!");
        } else if ((choices[0].equals("Rock") && choices[1].equals("Scissors")) ||
                   (choices[0].equals("Paper") && choices[1].equals("Rock")) ||
                   (choices[0].equals("Scissors") && choices[1].equals("Paper"))) {
            sendMessageToAll("Player 1 wins!");
        } else {
            sendMessageToAll("Player 2 wins!");
        }
        
        playerChoices.clear();
    }

    private static void sendMessageToAll(String message) {
        // Send the message to all players in the room
    }


    public void close(){
        super.close();
    }

    private void calculateWinners() {
        List<ServerPlayer> activePlayers = new ArrayList<>(players.values());
    int playerCount = activePlayers.size();
    int[] playerResults = new int[playerCount]; // to store the result for each player

 for (int i = 0; i < playerCount; i++) {
        ServerPlayer currentPlayer = activePlayers.get(i);
        GameRoom.Choice currentChoice = currentPlayer.getChoice();
        boolean lost = false;

        for (int j = i + 1; j < playerCount; j++) {
            ServerPlayer nextPlayer = activePlayers.get(j);
            GameRoom.Choice nextChoice = nextPlayer.getChoice();

            if (currentChoice != null && nextChoice != null) {
                if (currentChoice == nextChoice) {
                    // Handle tie condition
                    reportResult(currentPlayer, nextPlayer, "tie");
                    syncResult(currentPlayer.getClient().getClientId(), "tie");
                    syncResult(nextPlayer.getClient().getClientId(), "tie");
                } else if ((currentChoice == GameRoom.Choice.ROCK && nextChoice == GameRoom.Choice.SCISSORS) ||
                           (currentChoice == GameRoom.Choice.PAPER && nextChoice == GameRoom.Choice.ROCK) ||
                           (currentChoice == GameRoom.Choice.SCISSORS && nextChoice == GameRoom.Choice.PAPER)) {
                    // Handle current player wins
                    reportResult(currentPlayer, nextPlayer, "win");
                    syncResult(currentPlayer.getClient().getClientId(), "win");
                    syncResult(nextPlayer.getClient().getClientId(), "lose");
                } else {
                    // Handle next player wins
                    reportResult(currentPlayer, nextPlayer, "lose");
                    syncResult(nextPlayer.getClient().getClientId(), "win");
                    syncResult(currentPlayer.getClient().getClientId(), "lose");
                    lost = true;
                }
            }
        }

        if (lost) {
            // Move losing player to spectator list
            currentPlayer.setSpectator(true);
            sendMessage(null, String.format("%s has lost and moved to the spectator list.", currentPlayer.getClient().getClientName()));
        }
    }

    // Check if any players are left to continue the game
    boolean continueGame = false;
    for (int result : playerResults) {
        if (result == 1) {
            continueGame = true;
            break;
        }
    }

    if (!continueGame) {
        sendMessage(null, "Game ended, no players left to continue.");
        // Reset the session or perform any other necessary actions
        resetSession();
    }
    }

    private void startSelectionPhase() {
        updatePhase(Phase.SELECTION);
        sendMessage(null, "Selection phase started");
        int selectionTimeLimit = 10; // 10 seconds for selection
        Timer selectionTimer = new Timer();
        
        selectionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                selectionTimer.cancel();
                handleSelectionPhaseEnd();
            }
        }, selectionTimeLimit * 1000); // Convert seconds to milliseconds
    }

    private void reportResult(ServerPlayer player1, ServerPlayer player2, String result) {
        String message;
        if (result.equals("tie")) {
            message = "It's a tie between " + player1.getClient().getClientName() +
                      " and " + player2.getClient().getClientName() + "!";
        } else if (result.equals("win")) {
            message = player1.getClient().getClientName() + " wins against " +
                      player2.getClient().getClientName() + "!";
        } else {
            message = player2.getClient().getClientName() + " wins against " +
                      player1.getClient().getClientName() + "!";
        }
        sendMessage(null, message);
    }
    
    private void syncResult(long clientId, String result) {
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendResult(clientId, result);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    private void handleSelectionPhaseEnd() {

        calculateWinners();
        resetSession(); 
    }

    public void startGame(){
        if (players.size() >= Constants.MINIMUM_PLAYERS) {
            boolean allReady = players.values().stream().allMatch(ServerPlayer::isReady);
            if (allReady) {
                start();
            } else {
                sendMessageToAll("Not enough players are ready to start the game.");
            }
        } else {
            sendMessageToAll("Not enough players to start the game.");
        }
    }

}