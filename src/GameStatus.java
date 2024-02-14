import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameStatus extends JPanel {

	static final Color BACKGROUND_COLOR = new Color(209,209,209);
	public GameStatus() {
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		JPanel bottom = new JPanel();
		
		// set the stats of the panel
		setLayout(new BorderLayout());
		// setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(13,40));
		
		// setting the layout managers of the inner panels, and setting the sizes of top and bottom panels
		top.setLayout(new GridLayout(1,3));
		middle.setLayout(new GridLayout(1,3));
		bottom.setLayout(new GridLayout(1,3));
		
		// setting the size of the top and the bottom panels
		top.setPreferredSize(new Dimension(0,80));
		bottom.setPreferredSize(new Dimension(0,80));
		
		// coloring the background of the inner panels
		top.setBackground(BACKGROUND_COLOR);
		middle.setBackground(BACKGROUND_COLOR);
		bottom.setBackground(BACKGROUND_COLOR);
		
		
		
		// adding components to the top panel
		JPanel topMiddlePanel = new JPanel();
		topMiddlePanel.setLayout(new BorderLayout());
		topMiddlePanel.add(new JLabel("Game Timer"),BorderLayout.NORTH);
		topMiddlePanel.add(new JLabel("0:31"),BorderLayout.SOUTH);
		top.add(new JLabel("Game Status"));
		top.add(topMiddlePanel);
		top.add(new JLabel("Round 2"));	
		
		// adding components to the middle panel
		middle.add(new JLabel("Players Turn"));
		middle.add(new JLabel("Player 2"));
		
		// adding components to the bottom panel
		JLabel playerColors = new JLabel("Player one: Black");
		playerColors.setOpaque(true);
		playerColors.setBackground(new Color(174,174,174));
		bottom.add(playerColors);
		bottom.add(new JLabel("Player 2"));
		
		
		// assigning the positions of the sub-panels within the GameStatus Panel
		add(top,BorderLayout.NORTH);
		add(middle,BorderLayout.CENTER);
		add(bottom,BorderLayout.SOUTH);
		
		//add(new JLabel("label"),BorderLayout.NORTH);
		
		
	}
}