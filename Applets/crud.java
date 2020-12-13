package symPackage;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.Rectangle;

public class crud extends Applet 
{
  private JList jList1 = new JList();
  private JList jList2 = new JList();

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
    jList1.setBounds(new Rectangle(75, 50, 110, 20));
    jList2.setBounds(new Rectangle(235, 170, 0, 0));
    this.add(jList2, null);
    this.add(jList1, null);
  }
}