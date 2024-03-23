package connectfour;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
/* fix this bloody class*/
public class Timers extends JLabel{

	Timer timer;
	private int seconds = 0;
	private int minutes = 0;

	Timers() {
		startTimer();
	}


	public int getSeconds() {
		return seconds;
	}


	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}


	public int getMinutes() {
		return minutes;
	}


	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	protected void createTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconds++;
				if (seconds == 60) {
					seconds = 0;
					minutes++;
				}
				
				String secondsStr = String.format("%02d", seconds);
				String minutesStr = String.format("%02d", minutes);
				
				setText(minutesStr + ":" + secondsStr);
				timer.start();
			}
		});
	}
	protected void startTimer() {

		
	}

}
