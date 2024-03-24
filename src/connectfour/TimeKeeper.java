package connectfour;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
/* fix this bloody class*/
public class TimeKeeper extends JLabel{

	private int seconds = 0;
	private int minutes = 0;

	TimeKeeper() {
		SwingUtilities.invokeLater(()->{
			this.setText("00:00");
			this.seconds = 0;
			this.minutes = 0;
		});

	}

	protected void updateTimer() {

		seconds++;
		if (seconds == 60) {
			seconds = 0;
			minutes++;
		}

		String secondsStr = String.format("%02d", seconds);
		String minutesStr = String.format("%02d", minutes);
		SwingUtilities.invokeLater(()->{
			this.setText(minutesStr + ":" + secondsStr);
		});


	}
	protected void resetTimer() {
		SwingUtilities.invokeLater(()->{
			this.setText("00:00");
			this.seconds = 0;
			this.minutes = 0;
		});


	}

}
