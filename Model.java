import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.event.*;

public class Model {
  private final int size;
  private final boolean[] grid;
  private int openSites;
  ArrayList<Observer> observers; UnionFind connected; UnionFind filled;
  private int virtualTop;
  private int virtualBottom;

  public Model(int n) {
    size = n;
    grid = new boolean[n*n];
    openSites = 0;
    observers = new ArrayList<>();
    connected = new UnionFind(n*n + 2); // extra two is virtual top and bottom
    filled = new UnionFind(n*n + 1); // only connect with the top virtual
    // id n*n is top virtual
    // id n*n + 1 is bottom virtual

    virtualTop = n * n;
    virtualBottom = n * n + 1;
    // for all bottom row
    // n = 4
    //     16
    // 0, 1, 2, 3
    // 4, 5, 6, 7
    // 8, 9, 10, 11
    // 12, 13, 14, 15
    //     17
    for (int i = 0; i < n; i++) {
      connected.merge((n-1) * n + i, virtualBottom);
    }
    
    // bottom row and virtual bottom will be connected when opened
  }

  public void randomlyOpen() {
    Random random = new Random();
    Timer timer = new Timer(200, null);
    timer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (percolates()) timer.stop();
        int id = random.nextInt(size * size);
        System.out.println("open " + id);
        open(id);
      }
    });
    timer.start();
  }

  public int getSize() {
    return size;
  }

  public int getOpenSites() {
    return openSites;
  }

  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  private boolean inBounds(int id) {
    return (id >= 0 && id < size*size);
  }

  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  public void open(int id) {
    // ja esta aberto
    if (grid[id]) return;

    grid[id] = true;
    openSites++;

    // special case for first row
    // connect with virtual top
    if (id < size) {
      connected.merge(id, virtualTop);
      filled.merge(id, virtualTop);
    }

    // for each neighbor, connect it with this guy (if open)
    ArrayList<Integer> neighbors = new ArrayList<>();
    neighbors.add(id - size); // top
    neighbors.add(id + size); // bottom
    if (id % size != 0) neighbors.add(id - 1); // left
    if (id % size != size - 1) neighbors.add(id + 1); // right
    for (int x : neighbors) {
      if (inBounds(x) && isOpen(x)) {
        connected.merge(x, id);
        filled.merge(x, id);
      }
    }

    notifyObservers();
    // conectar com os vizinhos
  }

  public boolean isFull(int id) {
    if (!isOpen(id)) return false;
    return filled.same(id, virtualTop);
  }

  public boolean percolates() {
    return connected.same(virtualBottom, virtualTop);
  }

  public boolean isOpen(int id) {
    return grid[id];
  }
}
