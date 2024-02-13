import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Connect Four Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create Board and Menu instances
            Board board = new Board();
            Menu menu = new Menu();

            // Create a panel to hold both the board and menu
            JPanel gridPanel = new JPanel();
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(board, BorderLayout.CENTER);

            // Add the main panel to the frame's content pane
            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
            frame.setJMenuBar(menu);

            // Pack and display the frame
            frame.pack();
            frame.setVisible(true);
        });
    }
}
