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
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Send a message to the client
                network.sendMessage(clientSocket, "Connection successful");

                // Pass the socket to handleMessage method
                new Thread(() -> network.handleMessage(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
