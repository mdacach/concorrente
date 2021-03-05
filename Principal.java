import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class Principal {
    private static JFrame starterFrame;
    private static JButton start;
    private static JLabel mainText;

    public static void main(String[] args) {
        starterFrame = new JFrame("Percolation Menu");
        starterFrame.setSize(640, 640);

        mainText = new JLabel("Does a system percolate?");
        starterFrame.add(mainText);

        start = new JButton("start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Digite o tamanho: ", 5);
                int n = Integer.parseInt(input);
                //int n = 5;
                Model model = new Model(n);
                Controller controller = new Controller(model);
                starterFrame.setVisible(false);
                //mainFrame.add(grid, BorderLayout.CENTER);
                //mainFrame.setVisible(true);
            }
        });
        starterFrame.add(start);

        starterFrame.setVisible(true);
    }
}
