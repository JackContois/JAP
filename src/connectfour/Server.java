package connectfour;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private final int PORT = 6868;
    private Network network;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }


    @Override
    public void run() {
        try {
            System.out.println("Server started on localhost. Waiting for clients...");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

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
