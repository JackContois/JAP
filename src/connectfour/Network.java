package connectfour;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

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
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);
            System.out.println("Sent");
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }


    public void handleMessage() {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
        	System.out.println("Recieved");
            String command;
            while ((command = read.readLine()) != null) {
                String[] message = command.split("\\|");
                /* Handle chat messages */
                if (message.length == 2 && message[0].trim().equals("CHAT")) {
                	System.out.println("received");
                	String chatMessage = command.substring(command.indexOf("CHAT|") + "CHAT|".length());
                    controller.recievedChat(chatMessage);
                    }
                /* Handle game moves */
                else if (message.length == 2 && message[0].trim().equals("MOVE")) {
                    String[] moveInfo = message[1].trim().split(",");
                    if (moveInfo.length == 2) {
                        int columnIndex = Integer.parseInt(moveInfo[0].trim());
                        int player = Integer.parseInt(moveInfo[1].trim());
                        
                        // call methods to update board based on move
                        controller.recievedMove(columnIndex);
                    }
                } else if (message.length == 1 && message[0].trim().equals("RESTART")) { 
                		controller.confirm();
                }else if (message.length == 2 && message[0].trim().equals("CONFIRMED")) {
                	String[] confirmInfo = message[1].trim().split(",");
                	String confirmMessage = confirmInfo[0].trim();
                    if (confirmMessage.equals("yes")) {
                    	controller.resetGame();
                    } else {
                    	controller.recievedChat("The other player has denied your reset request");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
