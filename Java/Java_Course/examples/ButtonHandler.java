import java.awt.event.*;

public class ButtonHandler implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    Object source;
    source = e.getSource();


//    System.out.println("Source " + source);

    if (source == b) {
    System.out.println("Action occurred");
    System.out.println("Button's command is: "
                       + e.getActionCommand());
    }
    else if (source == b1) {
    System.out.println("Hello Canada");

   }
   else {
    System.out.println("Unknown button");

   }

  }
}
