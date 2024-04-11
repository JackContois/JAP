package connectfour;

/**
 * The Model class represents the game logic and data in the Connect Four game.
 * It manages the game board, player turns, moves, and win conditions.
 */
public class Model {
	/** The number of rows on the game board. */
    private static final int NUM_ROWS = 6;

    /** The number of columns on the game board. */
    private static final int NUM_COLS = 7;

    /** The current player (1 or 2) making the move. */
    private int player = 1;

    /** The 2D array representing the game board. */
    private int[][] board;
    
    private int thisPlayer = 1;

    /**
     * Constructs a new Model object and initializes the game board.
     */
    public Model() {
        initializeBoard();
    }

    /**
     * Initializes the game board, setting all cells to empty.
     */
    private void initializeBoard() {
        board = new int[NUM_ROWS][NUM_COLS];
        // Initialize all cells to empty
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                board[row][col] = 0;
            }
        }
    }

    /**
     * Attempts to make a move in the specified column for the current player.
     * Finds the bottom empty cell in the column and updates the board.
     * @param column The column in which to make the move.
     * @return The row where the move was made, or -1 if the column is full.
     */
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

    /**
     * Gets the value of the cell at the specified column and row on the board.
     * @param col The column index.
     * @param row The row index.
     * @return The value of the cell at the specified column and row.
     */
    public int getBoardValue(int col, int row) {
		return board[row][col]; 
    }
    
    /**
     * Resets the game by initializing the board and setting the current player to 1.
     */
    public void resetGame() {
    	initializeBoard();
    	this.player = 1;
    }
    
    /**
     * Checks for a winner by scanning the board for four consecutive chips in a row,
     * column, or diagonal.
     * @return 0 if no winner yet, 1 or 2 if a player wins, -1 for a draw.
     */
    public int checkWinner() {
    	
    	boolean isBoardFull = true;
        for (int col = 0; col < NUM_COLS; col++) {
            if (board[0][col] == 0) {
                isBoardFull = false;
                break;
            }
        }
        
        if (isBoardFull) {
            return -1;
        }
        
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
    
    /**
     * Gets the current player making the move.
     * @return The current player (1 or 2).
     */
    protected int getCurrentPlayer() {
    	return player;
    }
    
    public void setThisPlayer(int thisPlayer) {
    	this.thisPlayer = thisPlayer;
    }
    
    protected int getThisPlayer() {
    	return thisPlayer;
    }
}
