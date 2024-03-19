package connectfour;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            View view = new View();
            Controller controller = new Controller(model, view);
            view.setController(controller);
            view.setVisible(true);
            view.setResizable(false);
        });
    }
}
