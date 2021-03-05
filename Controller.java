public class Controller {
    Model model;
    View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
        //System.out.println("criei squares");
        view.createView();
    }

    public void open(int id) {
        model.open(id);
    }

    // reset the grid
    public void reset(int n) {
        this.model = new Model(n);
        this.view = new View(this, model);
        view.createView();
        //System.out.println("resetou bro");
    }
}
