package connectfour;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class View extends JFrame {
    private static final int CELL_SIZE = 80;
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel playPanel;
    private JPanel gameStatus;
    private JPanel gameLog;
    private JPanel titlePanel;
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
    
    
    public View() {
        // Initialize the frame
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load images
        loadImages();

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        playPanel = new JPanel(new BorderLayout());
        
        initializeBoard();
        initializeMenu();
        
        //test area
        gameStatus = new JPanel(new BorderLayout());
        gameStatus.add(new JLabel("test"));
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(gameStatus, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("test"));
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
                    controller.handleButtonClick(finalCol);
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
                    resetGame(); // Reset the game
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
    
    private void resetGame() {
        model.resetGame();
        initializeBoard();
        boardPanel.repaint();
    }
    private void initializeMenu() {
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        g = new JMenu("Game");
        n = new JMenu("Network");
        l = new JMenu("Language");
        h = new JMenu("Help");
        c = new JMenu("Chat");

        // Create menu items
        g1 = new JMenuItem("Restart Game");
        g2 = new JMenuItem("Quit Game");
        l1 = new JMenuItem("Change Language");
        h1 = new JMenuItem("How To Play");
        c1 = new JMenuItem("Open Chat");
        c2 = new JMenuItem("Close Chat");

        // Add menu items to menus
        g.add(g1);
        g.add(g2);
        l.add(l1);
        h.add(h1);
        c.add(c1);
        c.add(c2);

        // Add menus to menu bar
        menuBar.add(g);
        menuBar.add(n);
        menuBar.add(l);
        menuBar.add(h);
        menuBar.add(c);

        // Set the menu bar to the frame
        setJMenuBar(menuBar);
    }
}
