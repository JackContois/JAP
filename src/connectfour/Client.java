package connectfour;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Represents a client for the Connect Four game.
 */
public class Client implements Runnable {
    private String HOSTNAME = "";
    private int PORT = 0;
    private Network network;
    private Controller controller;
    private String name;

    /**
     * Constructs a new client instance.
     * 
     * @param network    The network handler associated with the client.
     * @param controller The controller instance handling the game logic.
     * @param hostName   The hostname or IP address of the server to connect to.
     * @param port       The port number of the server to connect to.
     * @param name       The name of the client.
     */
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
