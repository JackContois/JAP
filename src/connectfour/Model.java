package connectfour;

public class Model {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private int player = 1;
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

    public int makeMove(int column) {
        // Find the bottom empty cell in the specified column
        for (int row = NUM_ROWS - 1; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = player;
                this.player = (player == 1) ? 2 : 1;
                return row; // Move successful
            }
        }
        return -1; // Column is full, move failed
    }

    public int getBoardValue(int col, int row) {
		return board[row][col]; 
    }
    
    public void resetGame() {
    	initializeBoard();
    	this.player = 1;
    }
    
    public int checkWinner() {
        // Check horizontally
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col <= NUM_COLS - 4; col++) {
                int chip = board[row][col];
                if (chip != 0 && chip == board[row][col + 1] && chip == board[row][col + 2] && chip == board[row][col + 3]) {
                    return chip; // Return the player who won
                }
            }
        }

        // Check vertically
        for (int col = 0; col < NUM_COLS; col++) {
            for (int row = 0; row <= NUM_ROWS - 4; row++) {
                int chip = board[row][col];
                if (chip != 0 && chip == board[row + 1][col] && chip == board[row + 2][col] && chip == board[row + 3][col]) {
                    return chip; // Return the player who won
                }
            }
        }

        // Check diagonally (positive slope)
        for (int row = 0; row <= NUM_ROWS - 4; row++) {
            for (int col = 0; col <= NUM_COLS - 4; col++) {
                int chip = board[row][col];
                if (chip != 0 && chip == board[row + 1][col + 1] && chip == board[row + 2][col + 2] && chip == board[row + 3][col + 3]) {
                    return chip; // Return the player who won
                }
            }
        }

        // Check diagonally (negative slope)
        for (int row = 3; row < NUM_ROWS; row++) {
            for (int col = 0; col <= NUM_COLS - 4; col++) {
                int chip = board[row][col];
                if (chip != 0 && chip == board[row - 1][col + 1] && chip == board[row - 2][col + 2] && chip == board[row - 3][col + 3]) {
                    return chip; // Return the player who won
                }
            }
        }

        return 0; // No winner yet
    }
    
    protected int getCurrentPlayer() {
    	return player;
    }
}
