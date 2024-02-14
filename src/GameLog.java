import javax.swing.JTextArea;

public class GameLog extends JTextArea {
    public GameLog(int rows, int columns) {
        super(rows, columns);
        setEditable(false);
    }

    public void updateLog(String message) {
        append(message + "\n");
    }
}
