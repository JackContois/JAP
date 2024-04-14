package connectfour;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

/**
 * The Controller class handles user interactions and updates the model and view
 * accordingly.
 */
public class Controller implements ActionListener {
	/** The model of the game. */
	private Model model;
	/** The view of the game. */
	private View view;
	/** The network handler for communication. */
	private Network network;

	/**
	 * Constructs a Controller object with the specified model and view.
	 * 
	 * @param model The model to be associated with this controller.
	 * @param view  The view to be associated with this controller.
	 */
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.network = new Network(this);
	}

	/**
	 * Makes a move in the game based on the column chosen by the player.
	 * 
	 * @param column The column where the player made the move.
	 */
	public void makeMove(int column) {
		int row = -1;
		if (model.getCurrentPlayer() == view.getThisPlayer()) {
			network.sendMessage("MOVE|" + column + "," + model.getCurrentPlayer());
			row = model.makeMove(column);
		}
		if (row != -1) {
			int winner = view.checkValue(column, row);
			if (winner == 0) {
				view.resetTurnTimer();
			}
		}
	}

	/**
	 * Processes the move received from the opponent player.
	 * 
	 * @param column The column where the opponent player made the move.
	 */
	public void recievedMove(int column) {
		int row = model.makeMove(column);
		if (row != -1) {
			int winner = view.checkValue(column, row);
			if (winner == 0) {
				view.resetTurnTimer();
			}
		}
	}
	
	/**
	 * Processes the chat message received from the opponent player.
	 * 
	 * @param message The chat message received from the opponent player.
	 */
	public void recievedChat(String message) {
		view.appendMessage(message, view.getOtherPlayer());
	}

	/**
	 * Handles action events triggered by user interactions.
	 * 
	 * @param e The ActionEvent object representing the user's action.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
		case "restart":
			network.sendMessage("RESTART|");
			break;
		case "quit":
			System.exit(0);
			break;
		case "changeLanguageEnglish":
			view.setLanguage("English");
			break;
		case "changeLanguageFrench":
			view.setLanguage("French");
			break;
		case "updateTimer":
			view.updateGameTimer();
			view.updateTurnTimer();
			break;
		case "send":
			String message = view.sendMessage();
			network.sendMessage("CHAT|" + message);
			break;
		case "howToPlay":
			view.showHowToPlayDialog();
			break;
		case "deleteDialog":
			view.deleteDialog();
			break;
		case "verifyHost":
			view.handleHostData();
			break;
		case "hostDialog":
			view.hostDialog();
			break;
		case "clientDialog":
			view.clientDialog();
			break;
		case "verifyClient":
			view.handleClientData();
			break;
		case "cancelHost":
			view.hostDialog.dispose();
			break;
		case "cancelClient":
			view.clientDialog.dispose();
			break;
		case "disconnect":
			network.sendMessage("DISCONNECT|");
			network.disconnect();
			this.resetGame();
			break;
		}
	}

	/**
	 * Retrieves the column index associated with the specified button.
	 * 
	 * @param button The JButton object representing the column button.
	 * @return The column index.
	 */
	private int getColumnFromButton(JButton button) {
		return (int) button.getClientProperty("column");
	}

	/**
	 * Sets the local player's identity.
	 * 
	 * @param thisPlayer The local player's identity.
	 */
	public void setThisPlayer(int thisPlayer) {
		view.setThisPlayer(thisPlayer);
	}
	
	/**
	 * Sets the opponent player's identity.
	 * 
	 * @param otherPlayer The opponent player's identity.
	 */
	public void setOtherPlayer(int otherPlayer) {
		view.setOtherPlayer(otherPlayer);
	}
	
	/**
	 * Confirms an action.
	 */
	public void confirm() {
		view.confirm();
	}
	
	/**
	 * Sends a confirmation message.
	 * 
	 * @param option The confirmation option.
	 */
	public void confirmed(String option) {
		network.sendMessage("CONFIRMED|" + option);
	}
	
	/**
	 * Resets the game state.
	 */
	public void resetGame() {
		view.resetGame();
		view.resetGameTimer();
		view.resetTurnTimer();
		view.stopTimerThread();
		view.startTimerThread();
	}
	
	/**
	 * Starts a server to host the game.
	 * 
	 * @param port The port to listen on.
	 * @param name The name of the local player.
	 */
	public void runServer(int port, String name) {
		try {
			Server server = new Server(network, port, name);
			Thread threadServer = new Thread(server);
			threadServer.start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Connects to a server to join a game.
	 * 
	 * @param hostName The hostname or IP address of the server.
	 * @param port     The port number of the server.
	 * @param name     The name of the local player.
	 */
	public void runClient(String hostName, int port, String name) {
		Client client = new Client(network, this, hostName, port, name);
		Thread threadClient = new Thread(client);
		threadClient.start();
	}
	
	/**
	 * Sets the name of the local player.
	 * 
	 * @param name The name of the local player.
	 */
	public void setMyName(String name) { 
		view.setMyName(name);
	}
	
	/**
	 * Gets the name of the local player.
	 * 
	 * @return The name of the local player.
	 */
	public String getMyName() {
		return view.getMyName();
	}
	
	/**
	 * Sets the name of the opponent player.
	 * 
	 * @param name The name of the opponent player.
	 */
	public void setOtherName(String name) { 
		view.setOtherName(name);
	}
	
	/**
	 * Appends a chat message to the view.
	 * 
	 * @param message The chat message to append.
	 */
	public void appendChat(String message) {
		view.appendMessage(message, view.getOtherPlayer());
	}
}
