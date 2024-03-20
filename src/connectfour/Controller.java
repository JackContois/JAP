package connectfour;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void handleButtonClick(int column) {
    	if (model != null) {
            int row = model.makeMove(column); // Call the makeMove method of the model
            if (row != -1) {
            	view.checkValue(column, row);
            }
        }
    }

    // Additional methods for controlling the game can be added here
}
