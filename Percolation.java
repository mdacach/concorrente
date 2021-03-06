import java.util.ArrayList;
public class Percolation {
  private final int size;
  private final boolean[] grid;
  private int openSites;
  ArrayList<Observer> observers;

  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  public Percolation(int n) {
    size = n;
    grid = new boolean[n*n];
    openSites = 0;
  }

  public void open(int id) {
    // ja esta aberto
    if (grid[id]) return;

    grid[id] = true;
    openSites++;
    // conectar com os vizinhos
  }

  public boolean isOpen(int id) {
    return grid[id];
  }
}
