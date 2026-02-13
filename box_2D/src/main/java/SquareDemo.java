import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;


public class SquareDemo {
    public static void main(String[] args) {
        // Ensure GUI creation happens on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Square Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0, 800, 600);
        SquarePanel panel = new SquarePanel(50,50);
        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}