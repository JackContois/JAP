package connectfour;

public class Controller {
    private Model model;
    private View view;
    private int currentPlayer; // Track the current player

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.currentPlayer = 1; // Start with player 1
    }

    // Getter method for the model
    public Model getModel() {
        return model;
    }

    public void handleButtonClick(int column) {
        if (model != null) {
            model.makeMove(column, currentPlayer); // Call the makeMove method of the model
        }
    }

    // Additional methods for controlling the game can be added here
}
