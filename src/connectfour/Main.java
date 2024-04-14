package connectfour;

/**
 * This is the main class where all the class are called to create the game.
 * @author gemmx
 *
 */
public class Main {
	/**
	 * This is the main method
	 * @param args is the arguments taken in
	 */
    public static void main(String[] args) {
            Model model = new Model();
            View view = new View(model);
            Controller controller = new Controller(model, view);
            Network network = new Network(controller);
            
            view.setController(controller);
            view.setModel(model);
            view.setVisible(true);
            view.setResizable(false);
            
    }
}
