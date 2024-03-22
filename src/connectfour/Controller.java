package connectfour;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void makeMoveButton(int column) {
    	if (model != null) {
            int row = model.makeMove(column); //make large swithc case for all possible button clicks
            if (row != -1) {
            	view.checkValue(column, row);
            }
        }
    }
    
    //This method handles all the buttons with codes (option)
    // 0: reset game and timers
    // 1: change language
    public void handeButtonClick(int option) {
    	switch(option) {
    	case 0:
    		view.resetGame();
    		view.resetTurnTimer();
    		break;
    	case 1:
    		view.setLanguage();
    		break;
    	case 2:
    		view.resetTurnTimer();
    		break;
    	}
    }

}
