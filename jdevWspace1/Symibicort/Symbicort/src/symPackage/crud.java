import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.Rectangle;
import javax.swing.JComboBox;

public class crud extends Applet 
{
  private JComboBox jComboBox1 = new JComboBox();

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
    jComboBox1.setBounds(new Rectangle(115, 65, 170, 25));
    this.add(jComboBox1, null);
  }
}