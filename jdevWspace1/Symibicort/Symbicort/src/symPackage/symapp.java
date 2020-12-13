package symPackage;
import javax.swing.JApplet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import javax.swing.JList;

public class symapp extends JApplet 
{

  private JList jList1 = new JList();

  public symapp()
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
    symapp applet = new symapp();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(applet, BorderLayout.CENTER);
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
    this.getContentPane().setLayout(null);
    jList1.setBounds(new Rectangle(165, 90, 0, 120));
    this.getContentPane().add(jList1, null);
  }

  static  
  {
    try
    {
      // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch(Exception e)
    {
    }

  }
}