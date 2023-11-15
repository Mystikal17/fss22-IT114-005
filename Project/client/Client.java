package Project.client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Project.common.Grid;
import Project.common.Payload;
import Project.common.PayloadType;

public class Client {

    Socket server = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    final String ipAddressPattern = "/connect\\s+(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{3,5})";
    final String localhostPattern = "/connect\\s+(localhost:\\d{3,5})";
    boolean isRunning = false;
    private Thread inputThread;
    private Thread fromServerThread;
    private String clientName = "";
    private Grid grid;

    public Client() {
        System.out.println("");
    }

    public boolean isConnected() {
        if (server == null) {
            return false;
        }
        return server.isConnected() && !server.isClosed() && !server.isInputShutdown() && !server.isOutputShutdown();
    }

    private boolean connect(String address, int port) {
        try {
            server = new Socket(address, port);
            // channel to send to server
            out = new ObjectOutputStream(server.getOutputStream());
            // channel to listen to server
            in = new ObjectInputStream(server.getInputStream());
            System.out.println("Client connected");
            listenForServerMessage();
            sendConnect();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isConnected();
    }

    private boolean isConnection(String text) {
        return text.matches(ipAddressPattern)
                || text.matches(localhostPattern);
    }

    private boolean isQuit(String text) {
        return text.equalsIgnoreCase("/quit");
    }

    private boolean isName(String text) {
        if (text.startsWith("/name")) {
            String[] parts = text.split(" ");
            if (parts.length >= 2) {
                clientName = parts[1].trim();
                System.out.println("Name set to " + clientName);
            }
            return true;
        }
        return false;
    }

    private void sendStartGameCommand() {
        try {
            Payload p = new Payload();
            p.setPayloadType(PayloadType.START_GAME); 
            out.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendGameOverCommand() {
        try {
            Payload p = new Payload();
            p.setPayloadType(PayloadType.GAME_OVER); 
            out.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendRoundOverCommand() {
        try {
            Payload p = new Payload();
            p.setPayloadType(PayloadType.ROUND_OVER); 
            out.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isStartGameCommand(String text) {
        return text.equalsIgnoreCase("/startgame");
    }
    
    private boolean isGameOverCommand(String text) {
        return text.equalsIgnoreCase("/gameover");
    }
    
    private boolean isRoundOverCommand(String text) {
        return text.equalsIgnoreCase("/roundover");
    }
    private boolean processCommand(String text) {
        if (isConnection(text)) {
            if (clientName.isBlank()) {
                System.out.println("You must set your name before you can connect via: /name your_name");
                return true;
            }
            // replaces multiple spaces with single space
            // splits on the space after connect (gives us host and port)
            // splits on : to get host as index 0 and port as index 1
            String[] parts = text.trim().replaceAll(" +", " ").split(" ")[1].split(":");
            connect(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            return true;
        } else if (isQuit(text)) {
            isRunning = false;
            return true;
        } else if (isName(text)) {
            return true;
        } else if (isStartGameCommand(text)) {
            sendStartGameCommand();
            return true;
        } else if (isGameOverCommand(text)) {
            sendGameOverCommand();
            return true;
        } else if (isRoundOverCommand(text)) {
            sendRoundOverCommand();
            return true;
        } else if (isReadyCommand(text)) {
            sendReadyCommand();
            return true;
        } else if (isGuessCommand(text)) {
            String guessedWord = text.substring("/guess ".length());
            sendGuessCommand(guessedWord);
            return true;
        }
        return false;
    }

  
    // Send methods
    private void sendConnect() throws IOException {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CONNECT);
        p.setClientName(clientName);
        out.writeObject(p);
    }

    private void sendMessage(String message) throws IOException {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setMessage(message);
        p.setClientName(clientName);
        out.writeObject(p);
    }

        

    // end send methods
    private void listenForKeyboard() {
        inputThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Listening for input");
                try (Scanner si = new Scanner(System.in);) {
                    String line = "";
                    isRunning = true;
                    while (isRunning) {
                        try {
                            System.out.println("Waiting for input");
                            line = si.nextLine();
                            if (!processCommand(line)) {
                                if (isConnected()) {
                                    if (line != null && line.trim().length() > 0) {
                                        sendMessage(line);
                                    }

                                } else {
                                    System.out.println("Not connected to server");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Connection dropped");
                            break;
                        }
                    }
                    System.out.println("Exited loop");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }
        };
        inputThread.start();
    }

    private void listenForServerMessage() {
        fromServerThread = new Thread() {
            @Override
            public void run() {
                try {
                    Payload fromServer;

                    // while we're connected, listen for strings from server
                    while (!server.isClosed() && !server.isInputShutdown()
                            && (fromServer = (Payload) in.readObject()) != null) {

                        System.out.println("Debug Info: " + fromServer);
                        processMessage(fromServer);

                    }
                    System.out.println("Loop exited");
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!server.isClosed()) {
                        System.out.println("Server closed connection");
                    } else {
                        System.out.println("Connection closed");
                    }
                } finally {
                    close();
                    System.out.println("Stopped listening to server input");
                }
            }
        };
        fromServerThread.start();// start the thread
    }

    private void processMessage(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:// for now connect,disconnect are all the same
            case DISCONNECT:
                System.out.println(String.format("*%s %s*",
                        p.getClientName(),
                        p.getMessage()));
                break;
            case MESSAGE:
                System.out.println(String.format("%s: %s",
                        p.getClientName(),
                        p.getMessage()));
                break;
            case START_GAME:
                System.out.println("The game has started!");
                break;
            case GAME_OVER:
                System.out.println("Game Over! Thank you for playing!");
                break;
            case ROUND_OVER:
                System.out.println("End of Round. Get ready for the next one!");
                break;
            case GRID:
                 this.grid.setBoard(p.getGrid().getBoard());
                 System.out.println("Grid updated!"); 
                break;
            default:
                break;

        }
    }

    public void start() throws IOException {
        listenForKeyboard();
    }

    private void close() {
        try {
            inputThread.interrupt();
        } catch (Exception e) {
            System.out.println("Error interrupting input");
            e.printStackTrace();
        }
        try {
            fromServerThread.interrupt();
        } catch (Exception e) {
            System.out.println("Error interrupting listener");
            e.printStackTrace();
        }
        try {
            System.out.println("Closing output stream");
            out.close();
        } catch (NullPointerException ne) {
            System.out.println("Server was never opened so this exception is ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Closing input stream");
            in.close();
        } catch (NullPointerException ne) {
            System.out.println("Server was never opened so this exception is ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Closing connection");
            server.close();
            System.out.println("Closed socket");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ne) {
            System.out.println("Server was never opened so this exception is ok");
        }
    }

    public static void main(String[] args) {
        Client client = new Client();

        try {
            // if start is private, it's valid here since this main is part of the class
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendGridUpdate(Grid updatedGrid) {
        try {
            Payload p = new Payload();
            p.setPayloadType(PayloadType.GRID);
            p.setGrid(updatedGrid);
            out.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyGridCell(int row, int col, char value) {
        grid.getBoard()[row][col] = value;
        sendGridUpdate(grid);
    }

    private boolean isGuessCommand(String text) {
        return text.startsWith("/guess ");
    }
    private void sendGuessCommand(String guessedWord) {
        try {
            Payload p = new Payload();
            p.setPayloadType(PayloadType.GUESS);
            p.setMessage(guessedWord);
            p.setClientName(clientName);
            out.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isReadyCommand(String text) {
        return text.equalsIgnoreCase("/ready");
    }
    
    private void sendReadyCommand() {
        try {
            Payload p = new Payload();
            p.setPayloadType(PayloadType.READY);
            out.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}