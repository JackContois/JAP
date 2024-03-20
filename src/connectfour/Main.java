package connectfour;


public class Main {
    public static void main(String[] args) {
            Model model = new Model();
            View view = new View();
            Controller controller = new Controller(model, view);
            view.setController(controller);
            view.setModel(model);
            view.setVisible(true);
            view.setResizable(false);
    }
}
