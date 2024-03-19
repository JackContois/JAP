import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Board extends JPanel {
    private static final int CELL_SIZE = 80;
    static final int NUM_ROWS = 6;
    static final int NUM_COLS = 7;
    static int turn = 1;
    private BufferedImage emptyCell;
    CellState[][] board;
    private BufferedImage redCell;
    private BufferedImage blackCell;
    private BufferedImage arrows;

    public Board() {
        try {
        	emptyCell = ImageIO.read(Board.class.getResource("/resources/empty.png"));
            redCell = ImageIO.read(Board.class.getResource("/resources/red.png"));
            blackCell = ImageIO.read(Board.class.getResource("/resources/black.png"));
            if (emptyCell == null) {
                throw new IOException("Failed to read image file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load image: " + e.getMessage());
        }
        board = new CellState[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                board[row][col] = CellState.EMPTY;
            }
        }

        setPreferredSize(new Dimension(CELL_SIZE * NUM_COLS, CELL_SIZE * NUM_ROWS));

    	}



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //board[5][6] = CellState.PLAYER_ONE; //Placed pieces
       // board[5][5] = CellState.PLAYER_TWO; //Placed pieces
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                if (board[row][col] == CellState.EMPTY) {
                    g.drawImage(emptyCell, x, y, CELL_SIZE, CELL_SIZE, null);
                }else if (board[row][col] == CellState.PLAYER_ONE) {
                	g.drawImage(redCell, x, y, CELL_SIZE, CELL_SIZE, null);
                } else {
                	g.drawImage(blackCell, x, y, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }
    }


    public enum CellState {
        EMPTY,
        PLAYER_ONE,
        PLAYER_TWO
    }
}