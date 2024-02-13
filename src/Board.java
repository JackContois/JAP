import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Board extends JPanel {
    private final int CELL_SIZE = 50;
    private final int NUM_ROWS = 6;
    private final int NUM_COLS = 7;
    private BufferedImage emptyCell;
    private JLabel cell;

    public Board() {
        setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        
        try {
            emptyCell = ImageIO.read(Board.class.getResource("empty.png"));
            if (emptyCell == null) {
                throw new IOException("Failed to read image file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load image: " + e.getMessage());
        }
        
        for (int i = 0; i < NUM_ROWS * NUM_COLS; i++) {
            cell = new JLabel(new ImageIcon(emptyCell));
            cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
            add(cell);
        }
    }

//    public static void createAndShowGUI() {
//        JFrame frame = new JFrame("Connect Four Board");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new Board());
//        frame.pack();
//        frame.setVisible(true);
//    }
    
//    public JLabel getBoard() {
//    	return cell;
//    }
}