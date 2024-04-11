package connectfour;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.LinkedList;

public class Network {

    private Model model;
    private Controller controller;
    private final String HOSTNAME = "127.0.0.1";
    private final int PORT = 6868;
    private Queue<String> messageQueue = new LinkedList<>();
    
    public Network(Controller controller) {
        this.controller = controller;
    }

    // Method to send a message to either server or client
    public void sendMessage(Socket socket, String message) {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);
            System.out.println("Sent message: " + message);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    // Method to enqueue a message to be sent
    public void enqueueMessage(String message) {
        messageQueue.offer(message);
    }

    // Method to send queued messages
    public void sendQueuedMessages(Socket socket) {
        while (!messageQueue.isEmpty()) {
            String message = messageQueue.poll();
            sendMessage(socket, message);
        }
    }

    public void handleMessage(Socket clientSocket) {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
        	System.out.println("Recieved");
            String command;
            while ((command = read.readLine()) != null) {
                String[] message = command.split("\\|");

                /* Handle game moves */
                if (message.length == 2 && message[0].trim().equals("MOVE")) {
                    String[] moveInfo = message[1].trim().split(",");
                    if (moveInfo.length == 2) {
                        int columnIndex = Integer.parseInt(moveInfo[0].trim());
                        int player = Integer.parseInt(moveInfo[1].trim());
                        
                        // call methods to update board based on move
                        controller.makeMove(columnIndex);
                    }
                }
                /* Handle chat messages */
                else if (message.length == 2 && message[0].trim().equals("CHAT")) {
                    String[] chatInfo = message[1].trim().split(","); 
                    if (chatInfo.length == 2) {
                        String chatMessage = chatInfo[0].trim();
                        int player = Integer.parseInt(chatInfo[1].trim());

                        // call methods to update chat panel based on message
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
