import java.awt.*;
import java.awt.event.*;


public class MyTestButton {
  private Frame f;
  private Button b;
  private Button b1;

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




  public MyTestButton() {
    f = new Frame("Test");
    b = new Button("Press Me!");
    b.setActionCommand("ButtonPressed");
    b1 = new Button("Press Me Too!");
    b1.setActionCommand("ButtonPressed");
  }

  public void launchFrame() {
    ButtonHandler bh = new ButtonHandler();
    b.addActionListener(bh);
    b1.addActionListener(bh);
    f.add(b,BorderLayout.CENTER);
    f.add(b1,BorderLayout.EAST);
    f.pack();
    f.setVisible(true);
  }

  public static void main(String args[]) {
    MyTestButton guiApp = new MyTestButton();
    guiApp.launchFrame();
  }
}
