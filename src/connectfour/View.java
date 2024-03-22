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
    int option = 0;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel playPanel;
    private GameStatus gameStatus;
    private JPanel gameLog;
    private JPanel titlePanel;
    private JLabel title;
    private JPanel infoPanel;
    protected JButton[] columnButtons;
    private BufferedImage redChipImage;
    private BufferedImage blackChipImage;
    private BufferedImage emptyCellImage;
    private int[][] board;
    private Controller controller;
    private Model model;
    private JMenu g, n, l, h, c;
    private JMenuItem g1, g2, l1, h1, c1, c2;
    private LanguageManager languageManager;
    private HashMap<String, String> currentPhrases;
    String newLanguage = "French";
    String gameMenu;
    String networkMenu;
    String helpMenu;
    String languageMenu;
    String restartMenu;
    String quitMenu;
    String cLanguageMenu;
    String HTPMenu;
    
    
    public View() {
        // Initialize the frame
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeBoard();
        initializeMenu();
        
        // Setting up language
        languageManager = new LanguageManager();
        setLanguage();
        
        
        // Load images
        loadImages();

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        playPanel = new JPanel(new BorderLayout());
        
        
        
        //test area
        gameStatus = new GameStatus();
        gameStatus.setPreferredSize(new Dimension(425,275));
        // gameStatus.add(new JLabel("test"));
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(gameStatus, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        
        // title
        titlePanel = new JPanel();
        title = new JLabel("Connect 4");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
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

        // Add buttons
        addButtons();

        // Pack and set frame visibility
        pack();
        setLocationRelativeTo(null);
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
                button.addActionListener(e -> {
                    controller.makeMoveButton(finalCol);
                    controller.handeButtonClick(2);
                });
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
        
        int winner = model.checkWinner();
        if (winner != 0) {
            String message = "Player " + winner + " wins!";
            
            // Create a custom dialog
            JDialog dialog = new JDialog(this, "Game Over", true);
            JLabel label = new JLabel(message);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            JButton okButton = new JButton("OK");
            
            // Add ActionListener to the "OK" button
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose(); // Close the dialog
                    resetGame(); // reset the game board
                    
                }
            });
            
            // Set layout manager for the dialog
            dialog.setLayout(new BorderLayout());
            
            // Add components to the dialog
            dialog.add(label, BorderLayout.CENTER);
            dialog.add(okButton, BorderLayout.SOUTH);
            
            // Set the size of the dialog
            dialog.setSize(new Dimension(300, 150)); // Set the desired size here
            
            // Pack and set dialog visibility
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        }
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
        model.resetGame();
        gameStatus.resetGameTimer();
        resetTurnTimer();
        initializeBoard();
        boardPanel.repaint();
    }
    
    protected void resetTurnTimer() {
    	gameStatus.resetTurnTimer();
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
        g2 = new JMenuItem("Quit Game");
        l1 = new JMenuItem("Change Language");
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
        g1.addActionListener(e -> {
        	this.option = 0;
           controller.handeButtonClick(option);
        });
        
        
        l1.addActionListener(e -> {
        	this.option = 1;
            controller.handeButtonClick(option);
        });
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
    }
}
