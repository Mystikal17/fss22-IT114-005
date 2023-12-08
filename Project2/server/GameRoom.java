package Project2.server;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import Project2.common.Constants;
import Project2.common.Phase;
import Project2.common.TimedEvent;

import java.util.logging.Logger;

public class GameRoom extends Room {
    Phase currentPhase = Phase.READY;
    private static Logger logger = Logger.getLogger(GameRoom.class.getName());
    private TimedEvent readyTimer = null;
    private ConcurrentHashMap<Long, ServerPlayer> players = new ConcurrentHashMap<Long, ServerPlayer>();

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
            logger.info(String.format("Total clients %s", clients.size()));// change visibility to protected
            return player;
        });
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

     private void handlePlayerChoices() {
        Timer choiceTimer = new Timer();
        choiceTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean allPlayersChosen = players.values().stream()
                    .allMatch(player -> player.isReady() || player.isSpectator());
                
                if (allPlayersChosen) {
                    choiceTimer.cancel(); // Cancel the timer
                    start(); // Start the game
                } else {
                    players.values().forEach(player -> {
                        if (!player.isReady() && !player.isSpectator()) {
                            player.setSpectator(true);
                            sendMessage(null, String.format("%s has been moved to the spectator list.", player.getClient().getClientName()));
                        }
                    });
                    start(); // Start the game (if needed) even if not all players have chosen
                }
            }
        }, 10000);  // 10 seconds timeout for player choice
    }

    public void close(){
        super.close();
    }


}