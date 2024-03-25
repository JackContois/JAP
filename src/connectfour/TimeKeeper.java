package connectfour;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * TimeKeeper is a JLabel subclass used to display and update a timer in the format "MM:SS".
 */
public class TimeKeeper extends JLabel {

    /**
     * Tracks the seconds of the timer.
     */
    private int seconds = 0;

    /**
     * Tracks the minutes of the timer.
     */
    private int minutes = 0;

    /**
     * Constructs a new TimeKeeper object and initializes the timer display to "00:00".
     */
    TimeKeeper() {
        SwingUtilities.invokeLater(() -> {
            this.setText("00:00");
            this.seconds = 0;
            this.minutes = 0;
        });
    }

    /**
     * Updates the timer by incrementing seconds and minutes accordingly.
     * When seconds reach 60, they reset to 0 and minutes are incremented.
     * The updated time is then displayed on the label.
     */
    protected void updateTimer() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }

        String secondsStr = String.format("%02d", seconds);
        String minutesStr = String.format("%02d", minutes);
        SwingUtilities.invokeLater(() -> {
            this.setText(minutesStr + ":" + secondsStr);
        });
    }

    /**
     * Resets the timer to "00:00".
     */
    protected void resetTimer() {
        SwingUtilities.invokeLater(() -> {
            this.setText("00:00");
            this.seconds = 0;
            this.minutes = 0;
        });
    }
}
