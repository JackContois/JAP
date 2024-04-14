package connectfour;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private String HOSTNAME = "";
    private int PORT = 0;
    private Network network;
    private Controller controller;
    private String name;

    public Client(Network network, Controller controller, String hostName, int port, String name) {
    	this.name = name;
    	this.HOSTNAME = hostName;
    	this.PORT = port;
        this.network = network;
        this.controller = controller;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(HOSTNAME, PORT)) {
        	controller.setThisPlayer(2);
        	controller.setOtherPlayer(1);
        	controller.setMyName(name);
        	network.setSocket(socket);
        	controller.resetGame();
            network.handleMessage();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
