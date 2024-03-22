package connectfour;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
/* fix this bloody class*/
public class Timers extends JLabel{

	Timer gameTimer;
	Timer turnTimer;
	private int gameSeconds = 0;
	private int gameMinutes = 0;
	private int turnSeconds = 0;
	private int turnMinutes = 0;
	
	Timers(int type) {
		if(type == 1)
		startGameTimer();
		else if(type == 2)
		startTurnTimer();
	}
	
	public int getGameSeconds() {
		return gameSeconds;
	}


	public void setGameSeconds(int gameSeconds) {
		this.gameSeconds = gameSeconds;
	}


	public int getGameMinutes() {
		return gameMinutes;
	}


	public void setGameMinutes(int gameMinutes) {
		this.gameMinutes = gameMinutes;
	}


	public int getTurnSeconds() {
		return turnSeconds;
	}


	public void setTurnSeconds(int turnSeconds) {
		this.turnSeconds = turnSeconds;
	}


	public int getTurnMinutes() {
		return turnMinutes;
	}


	public void setTurnMinutes(int turnMinutes) {
		this.turnMinutes = turnMinutes;
	}

	
	


	protected void startGameTimer() {
		gameTimer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSeconds++;
				if (gameSeconds == 60) {
					gameSeconds = 0;
					gameMinutes++;
				}

				String secondsStr = String.format("%02d", gameSeconds);
				String minutesStr = String.format("%02d", gameMinutes);

				setText(minutesStr + ":" + secondsStr);
			}
		});
		gameTimer.start();
	}
	
	protected void startTurnTimer() {
		turnTimer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				turnSeconds++;
				if (turnSeconds == 60) {
					turnSeconds = 0;
					turnSeconds++;
				}

				String secondsStr = String.format("%02d", turnSeconds);
				String minutesStr = String.format("%02d", turnMinutes);

				setText(minutesStr + ":" + secondsStr);
			}
		});
		turnTimer.start();
	}
}
