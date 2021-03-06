// mediador entre view e model (MVC)
public class Controller {
  Model model;
  View view;

  public Controller(Model model) {
    this.model = model;
    this.view = new View(this, model);
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
  }

  public void randomlyOpen() {
    model.randomlyOpen();
  }
}
