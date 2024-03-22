package connectfour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener{
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "restart":
                view.resetGame();
                view.resetTurnTimer();
                break;
            case "quit":
            	System.exit(0);
            	break;
            case "changeLanguage":
                view.setLanguage();
                break;
            case "makeMove":
                JButton button = (JButton) e.getSource();
                int column = getColumnFromButton(button);
                int row = model.makeMove(column);
                if (row != -1) {
                	view.checkValue(column, row);
                }
                view.resetTurnTimer();
                break;
        }
    }
    
    // Method to get the column of the button pressed for makeMove method in model
    private int getColumnFromButton(JButton button) {
        return (int) button.getClientProperty("column");
    }

}
