import java.awt.*;

public class MyTwoListener {
  private Frame f;
  private TextField tf;

  public MyTwoListener() {
    f = new Frame("My Two listeners example");
    tf = new TextField(30);
  }

  public void launchFrame() {
    Label label = new Label("Click and drag the mouse");
    // Add components to the frame
    f.add(label, BorderLayout.NORTH);
    f.add(tf, BorderLayout.SOUTH);
    // Add this object as a listener
    EventHandle eh = new EventHandle(tf);

    f.addMouseMotionListener(eh);
    f.addMouseListener(eh);
    // Size the frame and make it visible
    f.setSize(300, 200);
    f.setVisible(true);
  }

  public static void main(String args[]) {
    MyTwoListener two = new MyTwoListener();
    two.launchFrame();
  }
}
