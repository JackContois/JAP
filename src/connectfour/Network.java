package connectfour;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
    private Controller controller;
    private Socket socket;
    
    public Network(Controller controller) {
        this.controller = controller;
    }
    
    public void setSocket(Socket socket) {
    	this.socket = socket;
    }

    // Method to send a message to either server or client
    public void sendMessage(String message) {
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
                } else if (message.length == 2 && message[0].trim().equals("NAME")) {
                	String[] nameInfo = message[1].trim().split(",");
                	String nameMessage = nameInfo[0].trim();
                	controller.setOtherName(nameMessage);
                	sendMessage("MYNAME|" + controller.getMyName());
                } else if (message.length == 2 && message[0].trim().equals("MYNAME")) {
                	String[] myNameInfo = message[1].trim().split(",");
                	String myNameMessage = myNameInfo[0].trim();
                	controller.setOtherName(myNameMessage);
                } else if (message.length == 1 && message[0].trim().equals("DISCONNECT")) {
                	controller.appendChat("The other player has disconnected, will restart the game in 10 seconds");
                	Thread.sleep(10000);
                	disconnect();
                	controller.resetGame();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void setMyName(String name) {
    	controller.setMyName(name);
    }
    
    public void resetGame() {
    	controller.resetGame();
    }
    
    public void disconnect() {
    	try {
        	socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
