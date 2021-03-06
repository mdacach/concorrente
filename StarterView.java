import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

// interface inicial do programa
// start, escolher tamanho, ajuda inicial
public class StarterView {
  JFrame starterFrame;
  JButton startButton;
  JLabel mainText;
  JPanel starterPanel;
  JButton howButton;

  String howMessage = "We are interested in if (or when) a system percolates.\n" +
            "Blocked sites are black. All sites start blocked.\n" +
            "Open them with a mouse click.\n" +
            "When water can flow from top to bottom, the system has percolated.\n";

  public StarterView() {
    starterFrame = new JFrame("Percolation Menu");
    starterFrame.setSize(640, 640);

    starterPanel = new JPanel();
    starterPanel.setLayout(new BoxLayout(starterPanel, BoxLayout.Y_AXIS));

    starterPanel.add(Box.createVerticalGlue());
    mainText = new JLabel("Does a system percolate?");
    mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainText.setAlignmentY(Component.CENTER_ALIGNMENT);
    starterPanel.add(mainText);

    starterPanel.add(Box.createRigidArea(new Dimension(0, 10)));

    startButton = new JButton("start");
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog("Enter the size of the system: (n x n) ", 5);
        int n = Integer.parseInt(input);
        Model model = new Model(n);
        Controller controller = new Controller(model);
        starterFrame.setVisible(false);
      }
    });

    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    startButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    starterPanel.add(startButton);

    starterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    howButton = new JButton("how does it work?");
    howButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(starterPanel, howMessage);
      }
    });
    howButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    howButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    starterPanel.add(howButton);

    starterPanel.add(Box.createVerticalGlue());

    starterFrame.add(starterPanel);
    starterFrame.setVisible(true);
  }
}
