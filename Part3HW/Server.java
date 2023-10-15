package Part3HW;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Server {
    int port = 3001;
    // connected clients
    private List<ServerThread> clients = new ArrayList<ServerThread>();

    // Random number generator for coin/dice games
    private Random randomNum = new Random();

    // cointoss method to make it flip a coin for heads or tails
    private String coinToss() {
        int toss = randomNum.nextInt(2);
        if (toss == 0)
            System.out.println("Heads");
        else if (toss == 1)
            System.out.println("Tails");
    }

    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket incoming_client = null;
            System.out.println("Server is listening on port " + port);
            do {
                System.out.println("waiting for next client");
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, this);

                    clients.add(sClient);
                    sClient.start();
                    incoming_client = null;

                }
            } while ((incoming_client = serverSocket.accept()) != null);
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }

    protected synchronized void disconnect(ServerThread client) {
        long id = client.getId();
        client.disconnect();
        broadcast("Disconnected", id);
    }

    protected synchronized void broadcast(String message, long id) {
        if (processCommand(message, id)) {

            return;
        }
        // let's temporarily use the thread id as the client identifier to
        // show in all client's chat. This isn't good practice since it's subject to
        // change as clients connect/disconnect
        message = String.format("User[%d]: %s", id, message);
        // end temp identifier

        // loop over clients and send out the message
        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from list", client.getId()));
                it.remove();
                broadcast("Disconnected", id);
            }
        }
    }

    private boolean processCommand(String message, long clientId){
        System.out.println("Checking command: " + message);
        if(message.equalsIgnoreCase("disconnect")){
            Iterator<ServerThread> it = clients.iterator();

            //making coin game a command with using '/cointoss' for client to use in server
            else if (message.equalsIgnoreCase("/tosscoin")){

                //holds result for the toss and broadcasts out in server what the result and who tossed the coin
                String result = cointoss();
                broadcast("Coin Toss landed on " + result, clientId);
                return true;

            while (it.hasNext()) {
                ServerThread client = it.next();
                if(client.getId() == clientId){
                    it.remove();
                    disconnect(client);
                    
                    break;
                }
            }
            return true;

          // sa 
        } else if (message.startsWith("roll ")){
            String[] parts = message.split("");
            if(parts.length == 2) {
                try{
                    String[] dicePieces = parts[1].split("d");
                    if(dicePieces.length == 2){
                        int numDice = Integer.parseInt(dicePieces[0]);
                        int numSides = Integer.parseInt(dicePieces[1]);
                        if(numDice > 0 && numSides > 0){
                            rollDice (clientId, numDice, numSides);
                        }
                    }
                } catch (NumberFormatException e){

                }
            }
            return true;
        }
        return false;
    }

    public void rollDice(long clientId, int numDice, intnumSides){
        String clientName = getClientName(clientId);
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append(clientName).append("rolled").append(numDice).append("d").append(numSides).append(" and got : ");

        for(int i = 0; i < numDice; i++){
            int roll = randomNum.nextInt(numSides) + 1;
            resultMessage.append(roll);
            if(i<numDice -1){
                resultMessage.append(", ");
            }
        }
        broadcast(resultMessage.toString(), clientId);
    }

    private String getClientName(long clientId) {
        return "User[" + clientId + "]";
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // can ignore, will either be index out of bounds or type mismatch
            // will default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}