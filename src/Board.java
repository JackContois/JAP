import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Board extends JPanel {
    private static final int CELL_SIZE = 80;
    static final int NUM_ROWS = 6;
    static final int NUM_COLS = 7;
    private BufferedImage emptyCell;
    private CellState[][] board;

    public Board() {
        try {
            emptyCell = ImageIO.read(Board.class.getResource("empty.png"));
            if (emptyCell == null) {
                throw new IOException("Failed to read image file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load image: " + e.getMessage());
        }
        
        // Initialize the board array
        board = new CellState[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                board[row][col] = CellState.EMPTY;
            }
        }

        // Set a preferred size for the board panel
        setPreferredSize(new Dimension(CELL_SIZE * NUM_COLS, CELL_SIZE * NUM_ROWS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                if (board[row][col] == CellState.EMPTY) {
                    g.drawImage(emptyCell, x, y, CELL_SIZE, CELL_SIZE, null);
                } else {
                    // Draw other cell states (e.g., player pieces) here
                }
            }
        }
    }

//    // Method to update the board with a move
//    public void makeMove(int row, int col, CellState player) {
//        board[row][col] = player;
//        repaint(); // Repaint the board after making the move
//    }
    
    // Enumeration representing the possible states of a cell
    public enum CellState {
        EMPTY,
        PLAYER_ONE,
        PLAYER_TWO
        // Add more states if needed
    }
}
