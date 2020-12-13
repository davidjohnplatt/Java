
import java.awt.FlowLayout;
import javax.swing.*;

public class window {

    public static void main(String[] args) {
 
 
        JFrame frame = new JFrame("JFrame Example");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("This is a label!");
        JButton button = new JButton();
        button.setText("Press me");
//      JFrame.add(new TextField("Text To Display"));
        JTextField field = new JTextField(20);
        field.setSize(500, 500);
        field.setVisible(true);

 
        panel.add(label);
        panel.add(button);
        panel.add(field);
 
        frame.add(panel);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
 
    }
 
}

