package connectfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private final String HOSTNAME = "127.0.0.1";
    private final int PORT = 6868;
    private Network network;

    public Client(Network network) {
        this.network = network;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(HOSTNAME, PORT)) {
            network.handleMessage(socket);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
