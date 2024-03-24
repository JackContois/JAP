package connectfour;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class View extends JFrame {
    private static final int CELL_SIZE = 80;
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private int redChipsPlayed;
    private int blackChipsPlayed;
	private static final Color GAME_STATUS_SECTION_COLOR = new Color(209,209,209);
    int option = 0;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel playPanel;
    private JPanel gameLog;
    private JPanel titlePanel;
    private JLabel title;
    private JLabel activePlayerLabel;
    private JLabel redPlayerChipsLabel;
    private JLabel blackPlayerChipsLabel;
    private JLabel playerTurnLabel;
    private JLabel gameTimerLabel;
    private JLabel turnTimerLabel;
    private JLabel chipsPlayedLabel;
    private JPanel infoPanel;
    private JPanel gameStatusPanel;
    private JPanel topGSPanel;
    private JPanel turnPanel;
    private JPanel timersPanel;
    private JPanel chipsPlayedPanel;
    private JPanel topCPPanel;
    private JPanel bottomCPPanel;
    private JTextField messageField;
    private JButton sendButton;
    protected JButton[] columnButtons;
    private BufferedImage redChipImage;
    private BufferedImage blackChipImage;
    private BufferedImage emptyCellImage;
    private ImageIcon redPlayerImage;
    private ImageIcon blackPlayerImage;
    private int[][] board;
    private Controller controller;
    private Model model;
    private JMenu g, n, l, h, c;
    private JMenuItem g1, g2, l1, h1, c1, c2;
    private LanguageManager languageManager;
    private HashMap<String, String> currentPhrases;
    private JTextArea gameLogTextArea;
    String newLanguage = "French";
    String gameMenu;
    String networkMenu;
    String helpMenu;
    String languageMenu;
    String restartMenu;
    String quitMenu;
    String cLanguageMenu;
    String HTPMenu;
    String player1Wins;
    String player2Wins;
    String chipsPlayed;
    String playerTurn;
    String timer;
    String turnTimer;
    String player1Plays;
    String player2Plays;
    String send;
    String drawMessage;
    
    
    public View(Model model) {
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
        topGSPanel = new JPanel(new GridLayout(1,2));
        turnPanel = new JPanel(new BorderLayout());
        timersPanel = new JPanel(new BorderLayout());
        chipsPlayedPanel = new JPanel(new BorderLayout());
        topCPPanel = new JPanel();
        bottomCPPanel = new JPanel();
        
        // setup turnPanel
        turnPanel.setBackground(GAME_STATUS_SECTION_COLOR);
        turnPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        activePlayerLabel = new JLabel(redPlayerImage);
        playerTurnLabel = new JLabel ();
        playerTurnLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        turnPanel.add(playerTurnLabel, BorderLayout.NORTH);
        turnPanel.add(activePlayerLabel, BorderLayout.CENTER);
        
        // setup timers panel
        timersPanel.setBackground(GAME_STATUS_SECTION_COLOR);
        timersPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        gameTimerLabel = new JLabel();
        gameTimerLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        turnTimerLabel = new JLabel();
        turnTimerLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        timersPanel.add(gameTimerLabel, BorderLayout.NORTH);
        timersPanel.add(turnTimerLabel, BorderLayout.CENTER);

        
        // setup top GS panel
        topGSPanel.setPreferredSize(new Dimension(0,100));
        topGSPanel.add(turnPanel);
        topGSPanel.add(timersPanel);
        
        // setup top CP panel
        topCPPanel.setBackground(GAME_STATUS_SECTION_COLOR);
        chipsPlayedLabel = new JLabel(); // Label
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
        chipsPlayedPanel.setPreferredSize(new Dimension(0,100));
        chipsPlayedPanel.add(topCPPanel, BorderLayout.NORTH);
        chipsPlayedPanel.add(bottomCPPanel, BorderLayout.SOUTH);
        redChipsPlayed = 0;
        blackChipsPlayed = 0;


        
        // setup game status panel
        gameStatusPanel.setPreferredSize(new Dimension(0,200));
        gameStatusPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        gameStatusPanel.add(topGSPanel, BorderLayout.NORTH);
        gameStatusPanel.add(chipsPlayedPanel, BorderLayout.SOUTH);
        
        
        
        // setup info panel
        infoPanel.setPreferredSize(new Dimension(425,0));
        infoPanel.add(gameStatusPanel, BorderLayout.NORTH);
        
        
        mainPanel.add(infoPanel, BorderLayout.EAST);
        
        // add title panel
        titlePanel = new JPanel();
        title = new JLabel("Connect 4");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // create and start game timer
        createGameTimer();
        
        // Create board panel for drawing the board
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int row = 0; row < NUM_ROWS; row++) {
                    for (int col = 0; col < NUM_COLS; col++) {
                        int x = col * CELL_SIZE;
                        int y = row * CELL_SIZE;
                        int value = model.getBoardValue(col,row);
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
        setLanguage();

        // Pack and set frame visibility
        pack();
        setLocationRelativeTo(null);
    }
    
    // function to update the game timer
    public void updateGameTimer() {
        
    }
    
    // function to create and start the game timer
    private void createGameTimer() {
    	SwingUtilities.invokeLater(()->{
    		Timer gameTimer = new Timer(1000, controller);
            gameTimer.setActionCommand("updateGameTimer");
            gameTimer.start();
    	});
    	
    }

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
                	button.setActionCommand("makeMove");
                    button.putClientProperty("column", finalCol);
                    button.addActionListener(controller);
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

    public void checkValue(int col, int row) {
        board[row][col] = model.getBoardValue(col, row);
        boardPanel.repaint(); // Repaint the board panel when the board changes
        
        int player = model.getCurrentPlayer();
        if (player == 1) {
        	 String playerMoveMessage = player1Plays + col + "\n";
             appendToGameLog(playerMoveMessage);
             activePlayerLabel.setIcon(redPlayerImage);
             blackChipsPlayed++; 
        } else {
        	String playerMoveMessage = player2Plays + col + "\n";
            appendToGameLog(playerMoveMessage);
            activePlayerLabel.setIcon(blackPlayerImage);
            redChipsPlayed++;
        }
       
        redPlayerChipsLabel.setText(": " + redChipsPlayed);
        blackPlayerChipsLabel.setText(": " + blackChipsPlayed);
        
        int winner = model.checkWinner();
        if (winner != 0 & winner != -1) {
        	String message = (winner == 1) ? player1Wins : player2Wins;
        	appendToGameLog(message);
        	disableColumnButtons();
            // Create a custom dialog
            JDialog dialog = new JDialog(this, true);
            JLabel label = new JLabel(message);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            JButton okButton = new JButton("OK");
            
            // Add ActionListener to the "OK" button
            okButton.addActionListener(controller);
            okButton.setActionCommand("restart");
            
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
            

        } else if (winner == -1) {
        	String message = drawMessage;
        	// Create a custom dialog
            JDialog dialog = new JDialog(this, true);
            JLabel label = new JLabel(message);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            JButton okButton = new JButton("OK");
            
            // Add ActionListener to the "OK" button
            okButton.addActionListener(controller);
            okButton.setActionCommand("restart");
            
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
            
        }
    }
    
    private void appendToGameLog(String message) {
        gameLogTextArea.append(message);
        gameLogTextArea.setCaretPosition(gameLogTextArea.getDocument().getLength());
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    private void initializeBoard() {
        board = new int[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                board[row][col] = 0;
            }
        }
    }
    
    protected void resetGame() {
    	Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.isVisible()) {
                    dialog.dispose();
                }
            }
        }
    	model.resetGame();
        initializeBoard();
        boardPanel.repaint();
        gameLogTextArea.setText("");
        redChipsPlayed = 0;
        blackChipsPlayed = 0;
        redPlayerChipsLabel.setText(": " + redChipsPlayed);
        blackPlayerChipsLabel.setText(": " + blackChipsPlayed);
        enableColumnButtons();
    }
    
    
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
        h1 = new JMenuItem("How To Play");
            
        // Add menu items to menus
        g.add(g1);
        g.add(g2);
        l.add(l1);
        h.add(h1);
        
        // Add menus to menu bar
        menuBar.add(g);
        menuBar.add(n);
        menuBar.add(l);
        menuBar.add(h);

        // Set the menu bar to the frame
        setJMenuBar(menuBar);
        
        //Set up actions
        g1.addActionListener(controller);
        g1.setActionCommand("restart");
        
        g2.addActionListener(controller);
        g2.setActionCommand("quit");
        
        
        l1.addActionListener(controller);
        l1.setActionCommand("changeLanguage");
        
        h1.addActionListener(controller);
        h1.setActionCommand("howToPlay");
    }
    
    protected void setLanguage() {
        // Toggle between English and French
        newLanguage = (newLanguage.equals("French")) ? "English" : "French";
        currentPhrases = languageManager.getPhrases(newLanguage);

        // Update GUI components with text from currentPhrases
        updateLabels();
    }


    
    private void updateLabels() {
    	gameMenu = currentPhrases.getOrDefault("gameLabel", "Game");
        g.setText(gameMenu);
        

        networkMenu = currentPhrases.getOrDefault("networkLabel", "Network");
        n.setText(networkMenu);
        
        languageMenu = currentPhrases.getOrDefault("languageLabel", "Language");
        l.setText(languageMenu);
        
        helpMenu = currentPhrases.getOrDefault("helpLabel", "Help");
        h.setText(helpMenu);
        
        restartMenu = currentPhrases.getOrDefault("restartLabel", "Restart Game");
        g1.setText(restartMenu);
        
        quitMenu = currentPhrases.getOrDefault("quitLabel", "Quit Game");
        g2.setText(quitMenu);
        
        cLanguageMenu = currentPhrases.getOrDefault("cLanguageLabel", "Change Language");
        l1.setText(cLanguageMenu);

        HTPMenu = currentPhrases.getOrDefault("HTPLabel", "How To Play");
        h1.setText(HTPMenu);
        
        this.player1Wins = currentPhrases.getOrDefault("P1Wins", "Player 1 Wins");
        this.player2Wins = currentPhrases.getOrDefault("P2Wins", "Player 2 Wins");
        
        chipsPlayed = currentPhrases.getOrDefault("chipsPlayed", "Chips Played");
        chipsPlayedLabel.setText(chipsPlayed);
        
        playerTurn = currentPhrases.getOrDefault("playerTurn", "Player's Turn");
        playerTurnLabel.setText(playerTurn);
        
        timer = currentPhrases.getOrDefault("timer", "Timer");
        gameTimerLabel.setText(timer+ ": ");
        
        turnTimer = currentPhrases.getOrDefault("turnTimer", "Turn Timer");
        turnTimerLabel.setText(turnTimer + ": ");
        
        this.player1Plays = currentPhrases.getOrDefault("player1Plays", "Player 1 Plays in Column: ");
        this.player2Plays = currentPhrases.getOrDefault("player2Plays", "Player 2 Plays in Column: ");
        
        send = currentPhrases.getOrDefault("send", "Send");
        sendButton.setText(send);
        
        this.drawMessage = currentPhrases.getOrDefault("draw", "Draw");
    }
    
    protected void sendMessage() {
        String message = messageField.getText();
        appendToGameLog(message + "\n");
        messageField.setText("");
    }
    private void setColumnButtonsEnabled(boolean enabled) {
        for (JButton button : columnButtons) {
            button.setEnabled(enabled);
        }
    }
    
    // After a winner is determined or when the game ends
    protected void disableColumnButtons() {
        setColumnButtonsEnabled(false);
    }

    // When the game is reset
    protected void enableColumnButtons() {
        setColumnButtonsEnabled(true);
    }
    
    protected void showHowToPlayDialog() {
        // Create a new dialog
        JDialog howToPlayDialog = new JDialog(this, "How to Play", true);

        // Set layout manager
        howToPlayDialog.setLayout(new BorderLayout());

        // Add instructions text
        JTextArea instructionsTextArea = new JTextArea();
        instructionsTextArea.setText("Instructions:\n1. Each player takes turns dropping a chip into one of the columns.\n2. The chip falls to the lowest empty slot in the selected column.\n3. The first player to connect four chips in a row wins.\n4. The connection can be horizontal, vertical, or diagonal.");
        instructionsTextArea.setEditable(false);
        howToPlayDialog.add(instructionsTextArea, BorderLayout.CENTER);

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> howToPlayDialog.dispose());
        howToPlayDialog.add(closeButton, BorderLayout.SOUTH);

        // Set dialog properties
        howToPlayDialog.setSize(500, 200);
        howToPlayDialog.setLocationRelativeTo(this);
        howToPlayDialog.setVisible(true);
    }
}
