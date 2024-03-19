package connectfour;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class View extends JFrame {
    private static final int CELL_SIZE = 80;
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private JPanel mainPanel;
    private JPanel boardPanel;
    protected JButton[] columnButtons;
    private BufferedImage redChipImage;
    private BufferedImage blackChipImage;
    private BufferedImage emptyCellImage;
    private int[][] board;
    private Controller controller;

    public View() {
        // Initialize the frame
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load images
        loadImages();

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        initializeBoard();
        
        // Create board panel for drawing the board
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int row = 0; row < NUM_ROWS; row++) {
                    for (int col = 0; col < NUM_COLS; col++) {
                        int x = col * CELL_SIZE;
                        int y = row * CELL_SIZE;
                        if (board[row][col] == 0) {
                            g.drawImage(emptyCellImage, x, y, CELL_SIZE, CELL_SIZE, null);
                        } else if (board[row][col] == 1) {
                            g.drawImage(redChipImage, x, y, CELL_SIZE, CELL_SIZE, null);
                        } else if (board[row][col] == 2) {
                            g.drawImage(blackChipImage, x, y, CELL_SIZE, CELL_SIZE, null);
                        }
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        };
        boardPanel.setPreferredSize(new Dimension(CELL_SIZE * NUM_COLS, CELL_SIZE * NUM_ROWS));
        mainPanel.add(boardPanel, BorderLayout.CENTER);

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
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
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
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
    }

    public void setBoard(int[][] board) {
        this.board = board;
        boardPanel.repaint(); // Repaint the board panel when the board changes
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    private void initializeBoard() {
        board = new int[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                board[row][col] = 0;
            }
        }
    }
}
