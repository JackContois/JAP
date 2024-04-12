package connectfour;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private final String HOSTNAME = "127.0.0.1";
    private final int PORT = 6868;
    private Network network;
    private Controller controller;

    public Client(Network network, Controller controller) {
        this.network = network;
        this.controller = controller;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(HOSTNAME, PORT)) {
        	controller.setThisPlayer(2);
        	controller.setOtherPlayer(1);
        	network.setSocket(socket);
            network.handleMessage();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
