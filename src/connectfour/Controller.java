package connectfour;


import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * The Controller class handles user interactions and updates the model and view
 * accordingly.
 */
public class Controller implements ActionListener {
	/**
	 * this is the creation of the model to use its methods
	 */
	private Model model;
	/**
	 * this is the creation of the view to use its methods
	 */
	private View view;

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

	public void recievedMove(int column) {
		int row = model.makeMove(column);
		if (row != -1) {
			int winner = view.checkValue(column, row);
			if (winner == 0) {
				view.resetTurnTimer();
			}
		}
	}
	
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

	public void setThisPlayer(int thisPlayer) {
		view.setThisPlayer(thisPlayer);
	}
	
	public void setOtherPlayer(int otherPlayer) {
		view.setOtherPlayer(otherPlayer);
	}
	
	public void confirm() {
		view.confirm();
	}
	
	public void confirmed(String option) {
		network.sendMessage("CONFIRMED|" + option);
	}
	
	public void resetGame() {
		view.resetGame();
		view.resetGameTimer();
		view.resetTurnTimer();
		view.stopTimerThread();
		view.startTimerThread();
	}
	
	public void runServer(int port, String name) {
		try {
			Server server = new Server(network, port, name);
			Thread threadServer = new Thread(server);
			threadServer.start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void runClient(String hostName, int port, String name) {
		Client client = new Client(network, this, hostName, port, name);
		Thread threadClient = new Thread(client);
		threadClient.start();
	}
	
	public void setMyName(String name) { 
		view.setMyName(name);
	}
	
	public String getMyName() {
		return view.getMyName();
	}
	
	public void setOtherName(String name) { 
		view.setOtherName(name);
	}
	
	public void appendChat(String message) {
		view.appendMessage(message, view.getOtherPlayer());
	}
}
