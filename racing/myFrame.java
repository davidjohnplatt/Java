import java.awt.*;

/**
 * An AWT-based top level window class.
 * <P>
 * @author D.J. Platt
 */
public class myFrame extends Frame {
  GridLayout gridLayout1 = new GridLayout(5,10);
  Choice choice1 = new Choice();
  Choice choice2 = new Choice();
  Button button1 = new Button();
  Button button2 = new Button();
  Button button3 = new Button();
  TextField textField1 = new TextField();
  TextField textField2 = new TextField();
  TextField textField3 = new TextField();

  /**
   * Constructs a new instance.
   */
  public myFrame() {
    super();
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Initializes the state of this instance.
   */
  private void jbInit() throws Exception {
    this.setLayout(gridLayout1);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Racing Results");
    choice1.setBounds(new Rectangle(12, 52, 109, 21));
    choice2.setBounds(new Rectangle(123, 53, 100, 21));
    button1.setBounds(new Rectangle(53, 201, 55, 23));
    button1.setLabel("Save");
    button2.setBounds(new Rectangle(132, 203, 55, 23));
    button2.setLabel("New Boat");
    button3.setBounds(new Rectangle(132, 203, 55, 23));
    button3.setLabel("Exit");
    textField1.setBounds(new Rectangle(230, 53, 41, 23));
    textField1.setText("textField1");
    textField2.setBounds(new Rectangle(279, 53, 37, 23));
    textField2.setText("textField2");
    textField3.setBounds(new Rectangle(328, 53, 38, 23));
    textField3.setText("textField3");
    this.setBackground(SystemColor.control);
    this.add(choice1, null);
    this.add(choice2, null);
    this.add(button1, null);
    this.add(button2, null);
    this.add(button3, null);
    this.add(textField1, null);
    this.add(textField2, null);
    this.add(textField3, null);
  }

  public static void main(String args[]) {
//    myFrame raceFrame = new myFrame();
//    raceFrame.setVisible(true);
  }
/*
  class Listen extends WindowAdapter {

    public void windowClosing(WindowEvent e){
       e.getWindow().dispose();
       System.exit(0);
    }

  }
*/
/*
  class Listen1 implements ActionListener {
    myFrame apmem;

    public Listen1( myFrame ap) {
      apmem = ap;
    }

    public void actionPerformed(ActionEvent e) {
      apmem.fd.setVisible(true);
      apmem.tf.setText(apmem.fd.getFile());
    }
  }
*/
}
