package connectfour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The Controller class handles user interactions and updates the model and view accordingly.
 */
public class Controller implements ActionListener{
	/**
	 * this is the creation of the model to use its methods
	 */
    private Model model;
    /**
	 * this is the creation of the view to use its methods
	 */
    private View view;

    /**
     * Constructs a Controller object with the specified model and view.
     * @param model The model to be associated with this controller.
     * @param view The view to be associated with this controller.
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Handles action events triggered by user interactions.
     * @param e The ActionEvent object representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "restart":
                view.resetGame();
                view.resetGameTimer();
                view.resetTurnTimer();
                view.stopTimerThread();
                view.startTimerThread();
                break;
            case "quit":
                System.exit(0);
                break;
            case "changeLanguageEnglish":
                view.setLanguage("English");
                break;
            case "changeLanguageFrench":
                view.setLanguage("French");
                break;
            case "makeMove":
                JButton button = (JButton) e.getSource();
                int column = getColumnFromButton(button);
                int row = model.makeMove(column);
                if (row != -1) {
                    int winner = view.checkValue(column, row);
                    if(winner==0) {
                        view.resetTurnTimer();
                    }
                }
                break;
            case "updateTimer":
                view.updateGameTimer();
                view.updateTurnTimer();
                break;
            case "send":
                view.sendMessage();
                break;
            case "howToPlay":
                view.showHowToPlayDialog();
                break;
            case "deleteDialog":
                view.deleteDialog();
                break;
        }
    }
    
    /**
     * Retrieves the column index associated with the specified button.
     * @param button The JButton object representing the column button.
     * @return The column index.
     */
    private int getColumnFromButton(JButton button) {
        return (int) button.getClientProperty("column");
    }
}
