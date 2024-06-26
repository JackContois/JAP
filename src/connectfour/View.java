package connectfour;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * The View class represents the graphical user interface of the Connect Four game.
 * It displays the game board, player information, timers, chat functionality, and menu options.
 * Handles user interactions and updates the GUI accordingly.
 */
public class View extends JFrame {
	/**
	 * The size of each cell in the game board grid.
	 */
	private static final int CELL_SIZE = 80;

	/**
	 * The number of rows in the game board.
	 */
	private static final int NUM_ROWS = 6;

	/**
	 * The number of columns in the game board.
	 */
	private static final int NUM_COLS = 7;

	/**
	 * The number of red chips played in the game.
	 */
	private int redChipsPlayed;

	/**
	 * The number of black chips played in the game.
	 */
	private int blackChipsPlayed;

	/**
	 * The color used for the game status section of the GUI.
	 */
	private static final Color GAME_STATUS_SECTION_COLOR = new Color(209, 209, 209);

	/**
	 * Indicates the current option in the game.
	 */
	int option = 0;

	private int thisPlayer = 1;

	private int otherPlayer = 2;

	/**
	 * Timer object for real-time updates during the game.
	 */
	protected Timer realTime;

	/**
	 * The main panel of the GUI.
	 */
	private JPanel mainPanel;

	/**
	 * Panel containing the game board.
	 */
	private JPanel boardPanel;

	/**
	 * Panel containing the player controls and buttons.
	 */
	private JPanel playPanel;

	/**
	 * Panel containing the game log.
	 */
	private JPanel gameLog;

	/**
	 * Panel containing the title label.
	 */
	private JPanel titlePanel;

	/**
	 * Label displaying the title of the game.
	 */
	private JLabel title;

	/**
	 * Label displaying the active player's turn.
	 */
	private JLabel activePlayerLabel;

	/**
	 * Label displaying the number of red chips played.
	 */
	private JLabel redPlayerChipsLabel;

	/**
	 * Label displaying the number of black chips played.
	 */
	private JLabel blackPlayerChipsLabel;

	/**
	 * Label displaying whose turn it is.
	 */
	private JLabel playerTurnLabel;

	/**
	 * Label displaying the game timer.
	 */
	private JLabel gameTimerLabel;

	/**
	 * Label displaying the turn timer.
	 */
	private JLabel turnTimerLabel;

	/**
	 * Object to manage the game timer.
	 */
	private TimeKeeper gameTimerText;

	/**
	 * Object to manage the turn timer.
	 */
	private TimeKeeper turnTimerText;

	/**
	 * Label indicating the number of chips played.
	 */
	private JLabel chipsPlayedLabel;

	/**
	 * Panel containing various information about the game status.
	 */
	private JPanel infoPanel;

	/**
	 * Panel containing the game status indicators.
	 */
	private JPanel gameStatusPanel;

	/**
	 * Panel containing the top part of the game status panel.
	 */
	private JPanel topGSPanel;

	/**
	 * Panel containing the turn indicator.
	 */
	private JPanel turnPanel;

	/**
	 * Panel containing the timer indicators.
	 */
	private JPanel timersPanel;

	/**
	 * Panel containing the chips played indicator.
	 */
	private JPanel chipsPlayedPanel;

	/**
	 * Panel containing the top part of the chips played panel.
	 */
	private JPanel topCPPanel;

	/**
	 * Panel containing the bottom part of the chips played panel.
	 */
	private JPanel bottomCPPanel;

	/**
	 * Text area for displaying game instructions.
	 */
	private JTextArea instructionsTextArea;

	/**
	 * Text field for entering chat messages.
	 */
	private JTextField messageField;

	/**
	 * Button for sending chat messages.
	 */
	private JButton sendButton;

	/**
	 * Button for closing dialogs.
	 */
	private JButton closeButton;

	/**
	 * Array of buttons representing each column in the game board.
	 */
	protected JButton[] columnButtons;

	/**
	 * Image for the red chip.
	 */
	private BufferedImage redChipImage;

	/**
	 * Image for the black chip.
	 */
	private BufferedImage blackChipImage;

	/**
	 * Image for an empty cell in the game board.
	 */
	private BufferedImage emptyCellImage;

	/**
	 * Icon for the red player.
	 */
	private ImageIcon redPlayerImage;

	/**
	 * Icon for the black player.
	 */
	private ImageIcon blackPlayerImage;

	/**
	 * 2D array representing the game board.
	 */
	private int[][] board;

	/**
	 * The controller responsible for handling user inputs.
	 */
	private Controller controller;

	/**
	 * The model containing the game logic.
	 */
	private Model model;

	/**
	 * Menu for game-related actions.
	 */
	private JMenu g;

	/**
	 * Menu for network-related actions.
	 */
	private JMenu n;

	/**
	 * Menu for language-related actions.
	 */
	private JMenu l;

	/**
	 * Menu for help-related actions.
	 */
	private JMenu h;

	/**
	 * Menu item for restarting the game.
	 */
	private JMenuItem g1;

	/**
	 * Menu item for quitting the game.
	 */
	private JMenuItem g2;

	/**
	 * Menu item for changing the language to English.
	 */
	private JMenuItem l1;

	/**
	 * Menu item for opening the "How To Play" dialog.
	 */
	private JMenuItem h1;

	/**
	 * Menu item for changing the language to French.
	 */
	private JMenuItem l2;

	/**
	 * Menu item representing option 1.
	 */
	private JMenuItem n1;

	/**
	 * Menu item representing option 2.
	 */
	private JMenuItem n2;

	/**
	 * Menu item representing option 3.
	 */
	private JMenuItem n3;


	/**
	 * Manager for handling language localization.
	 */
	private LanguageManager languageManager;

	/**
	 * Map containing the current set of localized phrases.
	 */
	private HashMap<String, String> currentPhrases;

	/**
	 * Text area for the game log
	 */
	private JTextArea gameLogTextArea;

	/**
	 * The selected language for the GUI.
	 */
	String newLanguage = "French";

	/**
	 * Message displayed when player 1 wins.
	 */
	String player1Wins;

	/**
	 * Message displayed when player 2 wins.
	 */
	String player2Wins;

	/**
	 * Message displayed for closing dialogs.
	 */
	String close;

	/**
	 * Rules of the game.
	 */
	String rules;

	/**
	 * Message displayed when player 1 makes a move.
	 */
	String player1Plays;

	/**
	 * Message displayed when player 2 makes a move.
	 */
	String player2Plays;

	/**
	 * Menu item label for opening the "How To Play" dialog.
	 */
	String HTPMenu;

	/**
	 * Message displayed when the game ends in a draw.
	 */
	String drawMessage;
	
	/**
	 * Stores the name of the local player.
	 */
	String myName;

	/**
	 * Stores the name of the opponent player.
	 */
	String otherName;

	/**
	 * Dialog box for the client to join a game.
	 */
	JDialog clientDialog;

	/**
	 * Dialog box for the host to create a game.
	 */
	JDialog hostDialog;

	/**
	 * Text field for entering the host name in the host dialog.
	 */
	JTextField hostNameField;

	/**
	 * Text field for entering the port number in the host dialog.
	 */
	JTextField hostPortField;

	/**
	 * Text field for entering the client name in the client dialog.
	 */
	JTextField clientNameField;

	/**
	 * Text field for entering the port number in the client dialog.
	 */
	JTextField clientPortField;

	/**
	 * Text field for entering the first part of the server's IP address in the client dialog.
	 */
	JTextField addressField1;

	/**
	 * Text field for entering the second part of the server's IP address in the client dialog.
	 */
	JTextField addressField2;

	/**
	 * Text field for entering the third part of the server's IP address in the client dialog.
	 */
	JTextField addressField3;

	/**
	 * Text field for entering the fourth part of the server's IP address in the client dialog.
	 */
	JTextField addressField4;

	/**
	 * Label for displaying status messages in the host dialog.
	 */
	JLabel hostStatusMessage;

	/**
	 * Label for displaying status messages in the client dialog.
	 */
	JLabel clientStatusMessage;



	/**
	 * Constructs a View object associated with the specified model.
	 * @param model The Model object representing the game's logic.
	 */
	public View(Model model) {
		SwingUtilities.invokeLater(() -> {
			this.model = model;
			Controller controller = new Controller(model, this);
			setController(controller);
			// Initialize the frame
			setTitle("Connect Four");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			initializeBoard();
			initializeMenu();

			// Load images
			loadImages();

			instructionsTextArea = new JTextArea("");
			closeButton = new JButton("");

			// Create main panel
			mainPanel = new JPanel(new BorderLayout());
			add(mainPanel);
			playPanel = new JPanel(new BorderLayout());

			// create info panel
			infoPanel = new JPanel(new BorderLayout());

			// create game status panel
			redPlayerImage = new ImageIcon(getClass().getResource("/resources/redPlayerV1.png"));
			blackPlayerImage = new ImageIcon(getClass().getResource("/resources/blackPlayerV1.png"));
			gameStatusPanel = new JPanel(new BorderLayout());
			topGSPanel = new JPanel(new GridLayout(1, 2));
			turnPanel = new JPanel(new BorderLayout());
			timersPanel = new JPanel(new GridLayout(2, 2));
			chipsPlayedPanel = new JPanel(new BorderLayout());
			topCPPanel = new JPanel();
			bottomCPPanel = new JPanel();

			// setup turnPanel
			turnPanel.setBackground(GAME_STATUS_SECTION_COLOR);
			turnPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			activePlayerLabel = new JLabel(redPlayerImage);
			playerTurnLabel = new JLabel();
			playerTurnLabel.setFont(new Font("Serif", Font.PLAIN, 25));
			turnPanel.add(playerTurnLabel, BorderLayout.NORTH);
			turnPanel.add(activePlayerLabel, BorderLayout.CENTER);

			// setup timers panel
			timersPanel.setBackground(GAME_STATUS_SECTION_COLOR);
			timersPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			gameTimerLabel = new JLabel();
			gameTimerText = new TimeKeeper();
			gameTimerLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			gameTimerText.setFont(new Font("Serif", Font.PLAIN, 20));

			turnTimerLabel = new JLabel();
			turnTimerText = new TimeKeeper();
			turnTimerLabel.setFont(new Font("Serif", Font.PLAIN, 10));
			turnTimerText.setFont(new Font("Serif", Font.PLAIN, 10));

			timersPanel.add(gameTimerLabel);
			timersPanel.add(gameTimerText);
			timersPanel.add(turnTimerLabel);
			timersPanel.add(turnTimerText);

			// setup top GS panel
			topGSPanel.setPreferredSize(new Dimension(0, 100));
			topGSPanel.add(turnPanel);
			topGSPanel.add(timersPanel);

			// setup top CP panel
			topCPPanel.setBackground(GAME_STATUS_SECTION_COLOR);
			chipsPlayedLabel = new JLabel();
			chipsPlayedLabel.setFont(new Font("Serif", Font.PLAIN, 25));
			topCPPanel.add(chipsPlayedLabel);

			// setup bottom CP panel
			bottomCPPanel.setBackground(GAME_STATUS_SECTION_COLOR);
			redPlayerChipsLabel = new JLabel(redPlayerImage);
			blackPlayerChipsLabel = new JLabel(blackPlayerImage);
			bottomCPPanel.add(redPlayerChipsLabel);
			bottomCPPanel.add(blackPlayerChipsLabel);
			redPlayerChipsLabel.setText(": " + redChipsPlayed);
			blackPlayerChipsLabel.setText(": " + blackChipsPlayed);

			// setup chips played panel
			chipsPlayedPanel.setBackground(GAME_STATUS_SECTION_COLOR);
			chipsPlayedPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			chipsPlayedPanel.setPreferredSize(new Dimension(0, 100));
			chipsPlayedPanel.add(topCPPanel, BorderLayout.NORTH);
			chipsPlayedPanel.add(bottomCPPanel, BorderLayout.SOUTH);
			redChipsPlayed = 0;
			blackChipsPlayed = 0;

			// setup game status panel
			gameStatusPanel.setPreferredSize(new Dimension(0, 200));
			gameStatusPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			gameStatusPanel.add(topGSPanel, BorderLayout.NORTH);
			gameStatusPanel.add(chipsPlayedPanel, BorderLayout.SOUTH);

			// setup info panel
			infoPanel.setPreferredSize(new Dimension(425, 0));
			infoPanel.add(gameStatusPanel, BorderLayout.NORTH);

			mainPanel.add(infoPanel, BorderLayout.EAST);

			// add title panel
			titlePanel = new JPanel();
			title = new JLabel("Connect 4");
			title.setFont(new Font("Arial", Font.BOLD, 40));
			titlePanel.add(title);
			mainPanel.add(titlePanel, BorderLayout.NORTH);

			// create and start game timer
			createTimerThread();

			// Create board panel for drawing the board
			boardPanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					for (int row = 0; row < NUM_ROWS; row++) {
						for (int col = 0; col < NUM_COLS; col++) {
							int x = col * CELL_SIZE;
							int y = row * CELL_SIZE;
							int value = model.getBoardValue(col, row);
							if (value == 0) {
								g.drawImage(emptyCellImage, x, y, CELL_SIZE, CELL_SIZE, null);
							} else if (value == 1) {
								g.drawImage(redChipImage, x, y, CELL_SIZE, CELL_SIZE, null);
							} else if (value == 2) {
								g.drawImage(blackChipImage, x, y, CELL_SIZE, CELL_SIZE, null);
							}
							g.setColor(Color.BLACK);
							g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
						}
					}
				}
			};
			boardPanel.setPreferredSize(new Dimension(CELL_SIZE * NUM_COLS, CELL_SIZE * NUM_ROWS));
			playPanel.add(boardPanel, BorderLayout.CENTER);
			mainPanel.add(playPanel, BorderLayout.WEST);

			gameLogTextArea = new JTextArea(10, 20);
			gameLogTextArea.setEditable(false);
			JScrollPane gameLogScrollPane = new JScrollPane(gameLogTextArea);
			gameLog = new JPanel(new BorderLayout());
			gameLog.add(gameLogScrollPane, BorderLayout.CENTER);

			infoPanel.add(gameLog, BorderLayout.CENTER);

			// Create send button for chat
			messageField = new JTextField(20);
			sendButton = new JButton();
			sendButton.setActionCommand("send");
			sendButton.addActionListener(controller);

			// Add input field and button to a panel
			JPanel inputPanel = new JPanel();
			inputPanel.add(messageField);
			inputPanel.add(sendButton);

			// Add input panel to the bottom of the main panel
			infoPanel.add(inputPanel, BorderLayout.SOUTH);

			// Add buttons
			addButtons();

			// Setting up language
			languageManager = new LanguageManager();
			setLanguage("English");

			// Pack and set frame visibility
			pack();
			setLocationRelativeTo(null);
		});

	}

	/**
	 * Updates the game timer display.
	 */
	public void updateGameTimer() {
		gameTimerText.updateTimer();
	}

	/**
	 * Updates the turn timer display.
	 */
	public void updateTurnTimer() {
		turnTimerText.updateTimer();
	}

	/**
	 * Resets the game timer.
	 */
	public void resetGameTimer() {
		gameTimerText.resetTimer();

	}

	/**
	 * Resets the turn timer.
	 */
	public void resetTurnTimer() {
		turnTimerText.resetTimer();

	}

	/**
	 * Creates and starts the timer thread.
	 */
	protected void createTimerThread() {
		SwingUtilities.invokeLater(() -> {
			realTime = new Timer(1000, controller);
			realTime.setActionCommand("updateTimer");
			startTimerThread();
		});

	}

	/**
	 * Starts the timer thread.
	 */
	protected void startTimerThread() {
		SwingUtilities.invokeLater(() -> {
			realTime.start();
		});

	}

	/**
	 * Stops the timer thread.
	 */
	protected void stopTimerThread() {
		SwingUtilities.invokeLater(() -> {
			realTime.stop();
		});

	}
	/**
	 * Displays a dialog for hosting a game, allowing the user to input their name and the port.
	 * Allows the user to host a game or cancel the operation.
	 */
	protected void hostDialog() {
		// adding dialog components
		hostDialog = new JDialog(this, "Host Game",true);
		JLabel hostName = new JLabel("Name:");
		hostNameField = new JTextField();
		JPanel hostPanel = new JPanel();
		JLabel port = new JLabel("Port:");
		hostPortField = new JTextField();
		JPanel portPanel = new JPanel();
		JLabel status = new JLabel("Status");
		hostStatusMessage = new JLabel();
		JPanel statusPanel = new JPanel();
		JButton hostButton = new JButton("Host");
		JButton cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();

		// host button
		hostButton.addActionListener(controller);
		hostButton.setActionCommand("verifyHost");
		
		// cancel button
		cancelButton.addActionListener(controller);
		cancelButton.setActionCommand("cancelHost");

		// configuring dialog box
		hostDialog.setSize(new Dimension(300, 250));
		hostDialog.setLayout(new GridLayout(4,1));

		// configuring the text fields
		hostNameField.setPreferredSize(new Dimension(100,20));
		hostPortField.setPreferredSize(new Dimension(100, 20));

		// adding components 
		hostPanel.add(hostName);
		hostPanel.add(hostNameField);
		hostDialog.add(hostPanel);
		portPanel.add(port);
		portPanel.add(hostPortField);
		hostDialog.add(portPanel);
		statusPanel.add(status);
		statusPanel.add(hostStatusMessage);
		hostDialog.add(statusPanel);
		buttonPanel.add(hostButton);
		buttonPanel.add(cancelButton);
		hostDialog.add(buttonPanel);



		// making visible
		hostDialog.setLocationRelativeTo(this);
		hostDialog.setVisible(true);

	}

	/**
	 * Handles the data entered by the user in the host dialog.
	 * Verifies the input data, checks for validity, and initiates the server if all conditions are met.
	 * Displays appropriate error messages if input data is invalid or incomplete.
	 */
	protected void handleHostData() {
		String hostName = hostNameField.getText();
		String portName = hostPortField.getText();
		int portAsInt = 0;
		if(!portName.isEmpty() && !hostName.isEmpty()) {
			try {
				portAsInt = Integer.parseInt(portName);
				if(portAsInt < 1020 || portAsInt > 65535) {
					hostStatusMessage.setText("port is out of range [1020 - 65535]");
				}
				else if(!hostName.matches("[a-zA-Z]+")) {
					hostStatusMessage.setText("invalid name, letters only");
				}

				else {
					hostStatusMessage.setText("Opening Connection");
					System.out.println(hostName);
					System.out.println(portName);
					controller.runServer(portAsInt, hostName);
				}
			}catch(NumberFormatException e) {
				hostStatusMessage.setText("port is not a number");
			}
		} else {
			hostStatusMessage.setText("must enter both a name and a port");
		}

	}

	/**
	 * Displays a dialog for joining a game, allowing the user to input their name, the server's IP address, and port.
	 * Allows the user to connect to a game server or cancel the operation.
	 */
	protected void clientDialog() {
		// adding dialog components
		clientDialog = new JDialog(this, "Join Game",true);
		JLabel clientName = new JLabel("Name:");
		clientNameField = new JTextField();
		JPanel clientPanel = new JPanel();

		// address components
		JLabel address = new JLabel("Address:");
		addressField1 = new JTextField();
		addressField2 = new JTextField();
		addressField3 = new JTextField();
		addressField4 = new JTextField();
		JPanel addressPanel = new JPanel();


		JLabel port = new JLabel("Port:");
		clientPortField = new JTextField();
		JPanel portPanel = new JPanel();
		JLabel status = new JLabel("Status");
		clientStatusMessage = new JLabel();
		JPanel statusPanel = new JPanel();
		JButton connectButton = new JButton("Connect");
		JButton cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();

		// connect button
		connectButton.addActionListener(controller);
		connectButton.setActionCommand("verifyClient");
		
		// cancel button
		cancelButton.addActionListener(controller);
		cancelButton.setActionCommand("cancelClient");
		
		// configuring dialog box
		clientDialog.setSize(new Dimension(300, 250));
		clientDialog.setLayout(new GridLayout(5,1));

		// configuring the text fields
		clientNameField.setPreferredSize(new Dimension(100,20));
		clientPortField.setPreferredSize(new Dimension(100, 20));
		addressField1.setPreferredSize(new Dimension(30, 20));
		addressField2.setPreferredSize(new Dimension(30, 20));
		addressField3.setPreferredSize(new Dimension(30, 20));
		addressField4.setPreferredSize(new Dimension(30, 20));




		// adding name components
		clientPanel.add(clientName);
		clientPanel.add(clientNameField);
		clientDialog.add(clientPanel);

		// adding address components 
		addressPanel.add(address);
		addressPanel.add(addressField1);
		addressPanel.add(addressField2);
		addressPanel.add(addressField3);
		addressPanel.add(addressField4);
		clientDialog.add(addressPanel);

		// adding port components
		portPanel.add(port);
		portPanel.add(clientPortField);
		clientDialog.add(portPanel);

		// adding status message components
		statusPanel.add(status);
		statusPanel.add(clientStatusMessage);
		clientDialog.add(statusPanel);

		// adding button components
		buttonPanel.add(connectButton);
		buttonPanel.add(cancelButton);
		clientDialog.add(buttonPanel);



		// making visible
		clientDialog.setLocationRelativeTo(this);
		clientDialog.setVisible(true);
	}

	/**
	 * Handles the data entered by the user in the client dialog.
	 * Verifies the input data, checks for validity, and initiates the client connection if all conditions are met.
	 * Displays appropriate error messages if input data is invalid or incomplete.
	 */
	protected void handleClientData() {
		String clientName = clientNameField.getText();
		String portName = clientPortField.getText();
		String addressPartOne = addressField1.getText();
		String addressPartTwo = addressField2.getText();
		String addressPartThree = addressField3.getText();
		String addressPartFour = addressField4.getText();


		int portAsInt = 0;
		int addressPartOneAsInt = 0;
		int addressPartTwoAsInt = 0;
		int addressPartThreeAsInt = 0;
		int addressPartFourAsInt = 0;

		if(!portName.isEmpty() && !clientName.isEmpty() && !addressPartOne.isEmpty() && !addressPartTwo.isEmpty() && !addressPartThree.isEmpty() && !addressPartFour.isEmpty()) {

			if(addressPartOne.length() == 3 && addressPartTwo.length() == 3 && addressPartThree.length() == 3 && addressPartFour.length() == 3 ) {

			try {
				addressPartOneAsInt = Integer.parseInt(addressPartOne);
				addressPartTwoAsInt = Integer.parseInt(addressPartTwo);
				addressPartThreeAsInt = Integer.parseInt(addressPartThree);
				addressPartFourAsInt = Integer.parseInt(addressPartFour);
				portAsInt = Integer.parseInt(portName);
				if(portAsInt < 1020 || portAsInt > 65535) {
					clientStatusMessage.setText("port is out of range [1020 - 65535]");
				}
				else if(!clientName.matches("[a-zA-Z]+")) {
					clientStatusMessage.setText("invalid name, letters only");
				}

				else {
					// successful data entry 
					String address = addressPartOne + "." + addressPartTwo + "." + addressPartThree + "." + addressPartFour;
					clientStatusMessage.setText("Connecting");
					System.out.println(clientName);
					System.out.println(address);
					System.out.println(portName);
					controller.runClient(address, portAsInt, clientName);
				}
			}catch(NumberFormatException e) {
				clientStatusMessage.setText("port or address is not a number");
			}
			}
			else {
				clientStatusMessage.setText("each adress section must have 3 integers");
			}
		} else {
			clientStatusMessage.setText("must fill all inputs appropriately");
		}
	}
	/**
	 * Loads images for the red and black chips, as well as for empty cells, from the resources folder.
	 * Displays an error message if any image fails to load.
	 */
	private void loadImages() {
		try {
			redChipImage = ImageIO.read(getClass().getResource("/resources/red.png"));
			blackChipImage = ImageIO.read(getClass().getResource("/resources/black.png"));
			emptyCellImage = ImageIO.read(getClass().getResource("/resources/empty.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to load images: " + e.getMessage());
		}
	}

	/**
	 * Adds buttons to the GUI for each column in the game board.
	 * Sets up button icons and action listeners.
	 * Displays an error message if the arrow image fails to load.
	 */
	private void addButtons() {

		// Create button panel for the buttons
		JPanel buttonPanel = new JPanel(new GridLayout(1, NUM_COLS));
		columnButtons = new JButton[NUM_COLS];
		try {
			for (int col = 0; col < NUM_COLS; col++) {
				Image img = ImageIO.read(View.class.getResource("/resources/arrow.png"));
				ImageIcon icon = new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
				JButton button = new JButton(icon);
				int finalCol = col;
				button.addActionListener(e -> controller.makeMove(finalCol));
				buttonPanel.add(button);
				columnButtons[col] = button;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to load image: " + e.getMessage());
		}

		// Add buttonPanel to the NORTH region
		playPanel.add(buttonPanel, BorderLayout.NORTH);
	}

	/**
	 * Checks the value of a cell in the game board and updates the GUI accordingly.
	 * Updates player information, game log, and handles end-game scenarios.
	 * @param col The column of the cell to check.
	 * @param row The row of the cell to check.
	 * @return 1 if the game is over, 0 otherwise.
	 */
	public int checkValue(int col, int row) {
		board[row][col] = model.getBoardValue(col, row);
		boardPanel.repaint();
		int winner = model.checkWinner();
		int player = model.getCurrentPlayer();
		if (player == thisPlayer) {
			String playerMoveMessage = otherName + player2Plays + col + "\n";
			appendToGameLog(playerMoveMessage);
			if (winner == 0) {
				activePlayerLabel.setIcon(redPlayerImage);
			}
			blackChipsPlayed++;
		} else {
			String playerMoveMessage = myName + player1Plays + col + "\n";
			appendToGameLog(playerMoveMessage);
			redChipsPlayed++;
			if (winner == 0) {
				activePlayerLabel.setIcon(blackPlayerImage);
			}
		}

		redPlayerChipsLabel.setText(": " + redChipsPlayed);
		blackPlayerChipsLabel.setText(": " + blackChipsPlayed);

		if (winner != 0 & winner != -1) {
			realTime.stop();
			String message;
			if (winner == thisPlayer) {
				message = myName + player1Wins;
			} else {
				message = otherName + player2Wins;
			}
			
			appendToGameLog(message + "\n");
			disableColumnButtons();
			// Create a custom dialog
			JDialog dialog = new JDialog(this, true);
			JLabel label = new JLabel(message);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			JButton okButton = new JButton("OK");

			// Add ActionListener to the "OK" button
			okButton.addActionListener(controller);
			okButton.setActionCommand("deleteDialog");

			// Set layout manager for the dialog
			dialog.setLayout(new BorderLayout());

			// Add components to the dialog
			dialog.add(label, BorderLayout.CENTER);
			dialog.add(okButton, BorderLayout.SOUTH);

			// Set the size of the dialog
			dialog.setSize(new Dimension(300, 150));

			// Pack and set dialog visibility
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			return 1;

		} else if (winner == -1) {
			realTime.stop();
			String message = drawMessage;
			// Create a custom dialog
			JDialog dialog = new JDialog(this, true);
			JLabel label = new JLabel(message);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			JButton okButton = new JButton("OK");

			// Add ActionListener to the "OK" button
			okButton.addActionListener(controller);
			okButton.setActionCommand("deleteDialog");

			// Set layout manager for the dialog
			dialog.setLayout(new BorderLayout());

			// Add components to the dialog
			dialog.add(label, BorderLayout.CENTER);
			dialog.add(okButton, BorderLayout.SOUTH);

			// Set the size of the dialog
			dialog.setSize(new Dimension(300, 150));

			// Pack and set dialog visibility
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			return 1;

		}
		return 0;
	}

	/**
	 * Appends a message to the game log text area.
	 * Automatically scrolls to the bottom of the log.
	 * @param message The message to append.
	 */
	private void appendToGameLog(String message) {
		gameLogTextArea.append(message);
		gameLogTextArea.setCaretPosition(gameLogTextArea.getDocument().getLength());
	}

	/**
	 * Sets the controller responsible for handling user inputs.
	 * @param controller The controller object.
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Sets the model associated with this view.
	 * @param model The model object.
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Initializes the game board with empty cells.
	 * Called when starting a new game.
	 */
	private void initializeBoard() {
		board = new int[NUM_ROWS][NUM_COLS];
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				board[row][col] = 0;
			}
		}
	}

	/**
	 * Resets the game state, including the game board, game log, and player information.
	 * Called when starting a new game.
	 */
	protected void resetGame() {
		deleteDialog();
		model.resetGame();
		initializeBoard();
		boardPanel.repaint();
		gameLogTextArea.setText("");
		redChipsPlayed = 0;
		blackChipsPlayed = 0;
		redPlayerChipsLabel.setText(": " + redChipsPlayed);
		blackPlayerChipsLabel.setText(": " + blackChipsPlayed);
		enableColumnButtons();
		activePlayerLabel.setIcon(redPlayerImage);
	}

	/**
	 * Initializes the menu bar with game options and actions.
	 * Sets up action listeners for menu items.
	 */
	private void initializeMenu() {
		// Create menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create menus
		g = new JMenu();
		n = new JMenu();
		l = new JMenu();
		h = new JMenu();

		// Create menu items
		g1 = new JMenuItem();
		g2 = new JMenuItem();
		l1 = new JMenuItem();
		l2 = new JMenuItem();
		h1 = new JMenuItem();
		n1 = new JMenuItem("Host Game");
		n2 = new JMenuItem("Join Game");
		n3 = new JMenuItem("Disconnect");

		// Add menu items to menus
		g.add(g1);
		g.add(g2);
		l.add(l1);
		l.add(l2);
		h.add(h1);
		n.add(n1);
		n.add(n2);
		n.add(n3);

		// Add menus to menu bar
		menuBar.add(g);
		menuBar.add(n);
		menuBar.add(l);
		menuBar.add(h);

		// Set the menu bar to the frame
		setJMenuBar(menuBar);

		// Set up actions
		g1.addActionListener(controller);
		g1.setActionCommand("restart");

		g2.addActionListener(controller);
		g2.setActionCommand("quit");

		l1.addActionListener(controller);
		l1.setActionCommand("changeLanguageEnglish");

		l2.addActionListener(controller);
		l2.setActionCommand("changeLanguageFrench");

		h1.addActionListener(controller);
		h1.setActionCommand("howToPlay");

		n1.addActionListener(controller);
		n1.setActionCommand("hostDialog");

		n2.addActionListener(controller);
		n2.setActionCommand("clientDialog");
		
		n3.addActionListener(controller);
		n3.setActionCommand("disconnect");
	}

	/**
	 * Sets the language of the GUI components based on the specified language.
	 * Updates text labels and menu items accordingly.
	 * @param language The language code (e.g., English or French).
	 */
	protected void setLanguage(String language) {
		// Toggle between English and French
		currentPhrases = languageManager.getPhrases(language);

		// Update GUI components with text from currentPhrases
		updateLabels();
	}

	/**
	 * Closes all open dialogs currently displayed on the screen.
	 * Used to clean up dialogs after they are no longer needed.
	 */
	protected void deleteDialog() {
		Window[] windows = Window.getWindows();
		for (Window window : windows) {
			if (window instanceof JDialog) {
				JDialog dialog = (JDialog) window;
				if (dialog.isVisible()) {
					dialog.dispose();
				}
			}
		}
	}

	/**
	 * Updates the text labels of GUI components with the current language settings.
	 * Used when changing the language to update all displayed text accordingly.
	 */
	private void updateLabels() {
		String gameMenu = currentPhrases.getOrDefault("gameLabel", "Game");
		g.setText(gameMenu);

		String networkMenu = currentPhrases.getOrDefault("networkLabel", "Network");
		n.setText(networkMenu);

		String languageMenu = currentPhrases.getOrDefault("languageLabel", "Language");
		l.setText(languageMenu);

		String helpMenu = currentPhrases.getOrDefault("helpLabel", "Help");
		h.setText(helpMenu);

		String restartMenu = currentPhrases.getOrDefault("restartLabel", "Restart Game");
		g1.setText(restartMenu);

		String quitMenu = currentPhrases.getOrDefault("quitLabel", "Quit Game");
		g2.setText(quitMenu);

		String cLanguageMenu = currentPhrases.getOrDefault("cLanguageLabelEnglish", "English");
		l1.setText(cLanguageMenu);

		String cLanguageMenu2 = currentPhrases.getOrDefault("cLanguageLabelFrench", "French");
		l2.setText(cLanguageMenu2);

		HTPMenu = currentPhrases.getOrDefault("HTPLabel", "How To Play");
		h1.setText(HTPMenu);

		this.player1Wins = currentPhrases.getOrDefault("P1Wins", " Wins");
		this.player2Wins = currentPhrases.getOrDefault("P2Wins", " Wins");

		String chipsPlayed = currentPhrases.getOrDefault("chipsPlayed", "Chips Played");
		chipsPlayedLabel.setText(chipsPlayed);

		String playerTurn = currentPhrases.getOrDefault("playerTurn", "Player's Turn");
		playerTurnLabel.setText(playerTurn);

		String timer = currentPhrases.getOrDefault("timer", "Timer");
		gameTimerLabel.setText(timer + ": ");

		String turnTimer = currentPhrases.getOrDefault("turnTimer", "Turn Timer");
		turnTimerLabel.setText(turnTimer + ": ");

		this.player1Plays = currentPhrases.getOrDefault("player1Plays", "Plays in Column: ");
		this.player2Plays = currentPhrases.getOrDefault("player2Plays", "Plays in Column: ");

		String send = currentPhrases.getOrDefault("send", "Send");
		sendButton.setText(send);

		this.drawMessage = currentPhrases.getOrDefault("draw", "Draw");

		this.rules = currentPhrases.getOrDefault("rules",
				"Instructions:\n1. Each player takes turns dropping a chip into one of the columns.\n2. The chip falls to the lowest empty slot in the selected column.\n3. The first player to connect four chips in a row wins.\n4. The connection can be horizontal, vertical, or diagonal.");

		this.close = currentPhrases.getOrDefault("close", "Close");
	}

	/**
	 * Appends a message to the chat area.
	 */
	protected String sendMessage() {
		String message = messageField.getText();
		appendMessage(message, thisPlayer);
		return message;
	}

	protected void appendMessage(String message, int player) {
		if (player == thisPlayer) {
		appendToGameLog("["+myName+"]"+ " "+ message + "\n");
		} else {
			appendToGameLog("["+otherName+"]"+ " "+ message + "\n");
		}
		messageField.setText("");
	}

	/**
	 * Sets the column buttons as enabled.
	 * @param enabled Pass in that we want the buttons enabled
	 */
	private void setColumnButtonsEnabled(boolean enabled) {
		for (JButton button : columnButtons) {
			button.setEnabled(enabled);
		}
	}

	/**
	 * Disables buttons to place pieces.
	 */
	protected void disableColumnButtons() {
		setColumnButtonsEnabled(false);
	}

	/**
	 * Enables the buttons to place pieces for use.
	 */
	protected void enableColumnButtons() {
		setColumnButtonsEnabled(true);
	}

	/**
	 * Displays the "How To Play" dialog with instructions for playing the game.
	 * Includes rules and gameplay instructions.
	 */
	protected void showHowToPlayDialog() {
		// Create a new dialog
		JDialog howToPlayDialog = new JDialog(this, HTPMenu, true);

		// Set layout manager
		howToPlayDialog.setLayout(new BorderLayout());

		String message = rules;
		// Add instructions text
		instructionsTextArea = new JTextArea(message);
		instructionsTextArea.setEditable(false);
		howToPlayDialog.add(instructionsTextArea, BorderLayout.CENTER);

		// Add a close button
		String message2 = close;
		closeButton = new JButton(message2);
		closeButton.setActionCommand("deleteDialog");
		closeButton.addActionListener(controller);
		howToPlayDialog.add(closeButton, BorderLayout.SOUTH);

		// Set dialog properties
		howToPlayDialog.setSize(500, 200);
		howToPlayDialog.setLocationRelativeTo(this);
		howToPlayDialog.setVisible(true);
	}

	/**
	 * Displays a confirmation dialog and performs actions based on the user's choice.
	 * If the user chooses 'Yes', confirms the reset request, resets the game, and prints "Confirmed!".
	 * If the user chooses 'No', cancels the reset request, and prints "Cancelled!".
	 */
	protected void confirm() {
	    int option = JOptionPane.showConfirmDialog(null, "The other Player wants to reset the game? Would you like to?", "Confirmation", JOptionPane.YES_NO_OPTION);

	    if (option == JOptionPane.YES_OPTION) {
	        controller.confirmed("yes");
	        System.out.println("Confirmed!");
	        controller.resetGame();
	    } else {
	        controller.confirmed("no");
	        System.out.println("Cancelled!");
	    }
	}

	/**
	 * Sets the player identifier for this player.
	 * 
	 * @param thisPlayer The player identifier for this player.
	 */
	public void setThisPlayer(int thisPlayer) {
	    this.thisPlayer = thisPlayer;
	}

	/**
	 * Retrieves the player identifier for this player.
	 * 
	 * @return The player identifier for this player.
	 */
	protected int getThisPlayer() {
	    return thisPlayer;
	}

	/**
	 * Sets the player identifier for the other player.
	 * 
	 * @param otherPlayer The player identifier for the other player.
	 */
	public void setOtherPlayer(int otherPlayer) {
	    this.otherPlayer = otherPlayer;
	}

	/**
	 * Retrieves the player identifier for the other player.
	 * 
	 * @return The player identifier for the other player.
	 */
	protected int getOtherPlayer() {
	    return otherPlayer;
	}

	/**
	 * Sets the name of this player.
	 * 
	 * @param name The name of this player.
	 */
	public void setMyName(String name) { 
	    this.myName = name;
	}

	/**
	 * Retrieves the name of this player.
	 * 
	 * @return The name of this player.
	 */
	public String getMyName() {
	    return myName;
	}

	/**
	 * Sets the name of the other player.
	 * 
	 * @param name The name of the other player.
	 */
	public void setOtherName(String name) { 
	    this.otherName = name;
	}

	/**
	 * Retrieves the name of the other player.
	 * 
	 * @return The name of the other player.
	 */
	public String getOtherName() {
	    return otherName;
	}

}
