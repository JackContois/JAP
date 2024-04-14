package connectfour;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a server for the Connect Four game.
 */
public class Server implements Runnable {
    private final ServerSocket serverSocket;
    private int PORT = 0;
    private Network network;
    private String name = "";
    private boolean isRunning = true;

    /**
     * Constructs a new server instance.
     * 
     * @param network The network handler associated with the server.
     * @param port    The port number to listen on.
     * @param name    The name of the server.
     * @throws IOException if an I/O error occurs when opening the server socket.
     */
    public Server(Network network, int port, String name) throws IOException {
        this.name = name;
        this.PORT = port;
        this.network = network;
        network.setMyName(name);
        this.serverSocket = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        try {
            System.out.println("Server started on localhost. Waiting for clients...");

            while (isRunning) {
                System.out.println("top");
                Socket clientSocket = serverSocket.accept();
                System.out.println("after");
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                
                network.setSocket(clientSocket);
                
                // Send a message to the client
                network.sendMessage("Connection successful");
                
                // Pass the socket to handleMessage method
                new Thread(() -> network.handleMessage()).start();
                
                network.sendMessage("NAME|" + name);
                network.resetGame();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            network.sendMessage("DISCONNECT|");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Initiates the disconnection process for the server.
     */
    public void disconnect() {
        this.isRunning = false;
    }
}
