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
            int row = model.makeMove(column); //make large swithc case for all possible button clicks
            if (row != -1) {
            	view.checkValue(column, row);
            }
        }
    }

}
