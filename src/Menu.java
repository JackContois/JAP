import javax.swing.*;

public class Menu extends JMenuBar {
    // Encapsulating fields
    private JMenu g, n, l, h, c;
    private JMenuItem g1, g2, l1, h1, c1, c2;

    public Menu() {
        initializeMenu();
    }

    private void initializeMenu() {
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
        add(g);
        add(n);
        add(l);
        add(h);
        add(c);
    }

}
