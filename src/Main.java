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
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(board, BorderLayout.CENTER);

            // Create a panel for the game log
            GameLog logText = new GameLog(13, 40);
            JScrollPane scrollPane = new JScrollPane(logText);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JPanel logPanel = new JPanel(new BorderLayout());
            logPanel.add(scrollPane, BorderLayout.SOUTH);
            
            
            GameStatus statusBox = new GameStatus();
            //JScrollPane scrollPane2 = new JScrollPane(statusBox);
            //GameLog gameStatus = new GameLog(13, 40);
            //JScrollPane scrollPane2 = new JScrollPane(gameStatus);
            //JPanel logPanel2 = new JPanel(new BorderLayout());
            logPanel.add(statusBox, BorderLayout.CENTER);
            
            // Add the main panel and log panel to the frame's content pane
            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
            frame.getContentPane().add(logPanel, BorderLayout.EAST);
           // frame.getContentPane().add(logPanel2, BorderLayout.NORTH);
            frame.setJMenuBar(menu);

            frame.setResizable(false);
            
            // Pack and display the frame
            frame.pack();
            frame.setVisible(true);

            // Example of updating the game log
            logText.updateLog("Welcome to Connect Four!");
        });
    }
}
