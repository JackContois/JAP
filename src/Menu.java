import javax.swing.*;

import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {
    // Encapsulating fields
    private JMenuBar mb;
    private JMenu g, n, l, h, c;
    private JMenuItem g1, g2, l1, h1, c1, c2;

    public Menu() {
        initializeMenu();
        setupFrame();
    }

    private void initializeMenu() {
        // create a menubar
        mb = new JMenuBar();

        // create menus
        g = new JMenu("Game");
        n = new JMenu("Network");
        l = new JMenu("Language");
        h = new JMenu("Help");
        c = new JMenu("Chat");

        // create menu items
        g1 = new JMenuItem("Restart Game");
        g2 = new JMenuItem("Quit Game");
        l1 = new JMenuItem("Change Language");
        h1 = new JMenuItem("How To Play");
        c1 = new JMenuItem("Open Chat");
        c2 = new JMenuItem("Close Chat");

        // add menu items to menus
        g.add(g1);
        g.add(g2);
        l.add(l1);
        h.add(h1);
        c.add(c1);
        c.add(c2);

        // add menus to menu bar
        mb.add(g);
        mb.add(n);
        mb.add(l);
        mb.add(h);
        mb.add(c);
    }

    private void setupFrame() {
        // add menubar to frame
        setJMenuBar(mb);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add window listener to handle frame closure
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Additional cleanup code if needed
                super.windowClosing(e);
            }
        });

        // Make the frame visible
        setVisible(true);
    }
    
    public JMenuBar getMB() {
    	return mb;
    }

}
