package connectfour;

public class Model {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private int[][] board;

    public Model() {
        initializeBoard();
    }

    private void initializeBoard() {
        board = new int[NUM_ROWS][NUM_COLS];
        // Initialize all cells to empty
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                board[row][col] = 0;
            }
        }
    }

    public boolean makeMove(int column, int player) {
        // Find the bottom empty cell in the specified column
        for (int row = NUM_ROWS - 1; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = player;
                return true; // Move successful
            }
        }
        return false; // Column is full, move failed
    }

    public int[][] getBoard() {
        return board;
    }
}
