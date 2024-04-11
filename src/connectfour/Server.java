package connectfour;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final ServerSocket serverSocket;
    private final int PORT = 6868;
    private Network network;

    public Server(Network network) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.network = network;
    }

    @Override
    public void run() {
        try {
            System.out.println("Server started on localhost. Waiting for clients...");

            while (true) {
            	System.out.println("top");
                Socket clientSocket = serverSocket.accept();
                System.out.println("after");
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                
                network.setSocket(clientSocket);
                
                // Send a message to the client
                network.sendMessage("Connection successful");
                

                // Pass the socket to handleMessage method
                new Thread(() -> network.handleMessage()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	System.out.println("closing");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
