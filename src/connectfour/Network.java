package connectfour;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {

    private Model model;
    private Controller controller;
    private final String HOSTNAME = "127.0.0.1";
    private final int PORT = 6868;

    public void sendMessageToServer(String message) {
        try (Socket socket = new Socket(HOSTNAME, PORT)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    public void handleMessage(Socket clientSocket) {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
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
