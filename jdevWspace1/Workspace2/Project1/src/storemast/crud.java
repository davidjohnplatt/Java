package storemast;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class crud extends Applet implements ListSelectionListener  
{
  private Choice choice1 = new Choice();
  private Button button1 = new Button();
  private Button button2 = new Button();
  private JList jList1 = new JList();
  private JPasswordField jPasswordField1 = new JPasswordField();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();

  public crud()
  {
  }

  public void init()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    crud applet = new crud();
    Frame frame = new Frame();
    frame.addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
        {
          System.exit(0);
        }
      });
    frame.add(applet, BorderLayout.CENTER);
    frame.setTitle("Applet Frame");
    applet.init();
    applet.start();
    frame.setSize(300, 300);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  private void jbInit() throws Exception
  {
    this.setLayout(null);
    choice1.setBounds(new Rectangle(190, 70, 160, 20));
    button1.setLabel("button1");
    button1.setBounds(new Rectangle(65, 235, 71, 23));
    button2.setLabel("button2");
    button2.setBounds(new Rectangle(185, 235, 71, 23));
    jList1.setBounds(new Rectangle(195, 100, 160, 15));
    jPasswordField1.setText("jPasswordField1");
    jPasswordField1.setBounds(new Rectangle(190, 40, 116, 22));
    jTextField1.setText("jTextField1");
    jTextField1.setBounds(new Rectangle(190, 0, 64, 19));
    jTextField2.setText("jTextField2");
    jTextField2.setBounds(new Rectangle(145, 135, 64, 19));
    jTextField3.setText("jTextField3");
    jTextField3.setBounds(new Rectangle(150, 170, 64, 19));
    jLabel1.setText("jLabel1");
    jLabel1.setBounds(new Rectangle(80, 5, 34, 14));
    jLabel2.setText("jLabel2");
    jLabel2.setBounds(new Rectangle(80, 40, 34, 14));
    jLabel3.setText("jLabel3");
    jLabel3.setBounds(new Rectangle(90, 75, 34, 14));
    jLabel4.setText("jLabel4");
    jLabel4.setBounds(new Rectangle(95, 105, 34, 14));
    this.add(jLabel4, null);
    this.add(jLabel3, null);
    this.add(jLabel2, null);
    this.add(jLabel1, null);
    this.add(jTextField3, null);
    this.add(jTextField2, null);
    this.add(jTextField1, null);
    this.add(jPasswordField1, null);
    this.add(jList1, null);
    this.add(button2, null);
    this.add(button1, null);
    this.add(choice1, null);
  }

  public void valueChanged(ListSelectionEvent e)
  {
  }




}