import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

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
            addColumnButtonsToMainPanel(mainPanel); // Add column buttons to the main panel
            mainPanel.add(board, BorderLayout.CENTER);

            // Create a panel for the game log
            GameLog logText = new GameLog(13, 40);
            JScrollPane scrollPane = new JScrollPane(logText);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JPanel logPanel = new JPanel(new BorderLayout());
            logPanel.add(scrollPane, BorderLayout.SOUTH);
            
            GameStatus statusBox = new GameStatus();
            logPanel.add(statusBox, BorderLayout.CENTER);

            // Add the main panel and log panel to the frame's content pane
            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
            frame.getContentPane().add(logPanel, BorderLayout.EAST);
            frame.setJMenuBar(menu);

            frame.setResizable(false);

            // Pack and display the frame
            frame.pack();
            frame.setVisible(true);

            // Example of updating the game log
            logText.updateLog("Welcome to Connect Four!");
        });
    }

    private static void addColumnButtonsToMainPanel(JPanel mainPanel) {
        JPanel buttonPanel = new JPanel(new GridLayout(1, Board.NUM_COLS));
        JButton[] columnButtons = new JButton[Board.NUM_COLS];
        try {
            for (int col = 0; col < Board.NUM_COLS; col++) {
                Image img = ImageIO.read(Board.class.getResource("arrow.png"));
                ImageIcon icon = new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
                JButton button = new JButton(icon);
                final int column = col;
               // button.addActionListener(e -> makeMove(column));
                buttonPanel.add(button);
                columnButtons[col] = button;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load image: " + e.getMessage());
        }
        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Add button panel to the top of the main panel
    }

//    private static void makeMove(int column) {
//        //This is where we are going to move the pieces
//    }
}
