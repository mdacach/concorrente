import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class View implements Observer {
    Model model;
    Controller controller; // controller

    Color blue = new Color(0, 153, 255);

    ArrayList<Square> squares;
    int sizeGrid;

    JFrame mainFrame;
    JPanel gridPanel;
    JPanel openSitesPanel;
    JLabel openSitesLabel;
    JButton helpButton;
    JLabel percolatesLabel;
    JButton resetButton;

    String helpMessage = "Click on tiles to open them.\n" +
                         "White tiles are open, Blue tiles have water flowing through them.\n" +
                         "When water flows through the bottom row tiles, the system percolates."; 

    private boolean alreadyPercolated;

    public View(Controller controller, Model model) {
        this.alreadyPercolated = false;
        this.controller = controller;
        this.model = model;
        this.sizeGrid = model.getSize();
        //System.out.println("criei squares");
        this.squares = new ArrayList<>();
        model.registerObserver((Observer) this);
    }

    public void update() {
        openSitesLabel.setText("Open sites = " + model.getOpenSites());
        for (Square square : squares) {
            int id = square.id;
            if (model.isFull(id)) {
                square.setBackground(blue);
            } else if (model.isOpen(id)) {
                square.setBackground(Color.WHITE);
            }
        }
        if (model.percolates() && !alreadyPercolated) {
            JOptionPane.showMessageDialog(null, "Percolates!!");
            percolatesLabel.setVisible(true);
            alreadyPercolated = true;
        }
    };

    public void createView() {
        mainFrame = new JFrame("Percolation");
        mainFrame.setSize(800, 800);

        openSitesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        openSitesLabel = new JLabel("Open sites: 0");
        openSitesPanel.add(openSitesLabel);

        percolatesLabel = new JLabel("System percolates!");
        percolatesLabel.setVisible(false);
        openSitesPanel.add(percolatesLabel);

        helpButton = new JButton("help");
        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mainFrame, helpMessage);
            }
        });
        openSitesPanel.add(helpButton);

        resetButton = new JButton("reset");
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.setVisible(false);
                controller.reset(model.getSize());
            }
        });
        openSitesPanel.add(resetButton);

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(openSitesPanel, BorderLayout.NORTH);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(sizeGrid, sizeGrid, 1, 1));

        int counter = 0;
        //System.out.println("adicionar os squares");
        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                Square square = new Square(counter);
                squares.add(square);
                gridPanel.add(square);
                counter++;
            }
        }

        mainFrame.add(gridPanel);
        //System.out.println("settando visible");
        mainFrame.setVisible(true);
    }

    private class Square extends JLabel {
        private int id;
        public Square(int id) {
            this.id = id;
            Dimension size = new Dimension(HEIGHT/sizeGrid, WIDTH/sizeGrid);
            setPreferredSize(size);
            setOpaque(true);
            setBackground(Color.BLACK);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controller.open(id);
                }
            });
        }
    }
}
