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
    private Socket socket;
    
    public Network(Controller controller) {
        this.controller = controller;
    }
    
    public void setSocket(Socket socket) {
    	this.socket = socket;
    }

    // Method to send a message to either server or client
    public void sendMessage(String message) {
    	System.out.println("Sending!");
    	System.out.println("Sent message: " + message);
        try {
            OutputStream output = socket.getOutputStream();
            System.out.println("1");
            PrintWriter writer = new PrintWriter(output, true);
            System.out.println("2");
            writer.println(message);
            System.out.println("3");
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error2: " + ex.getMessage());
        }
    }


    public void handleMessage() {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
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
                        controller.recievedMove(columnIndex);
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
