package mypackage1;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;


public class Symbicort extends Applet 
{
  private JComboBox jComboBox1 = new JComboBox();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JTextField jTextField5 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  public String chain;
  public String store;
  
  
    


  public Symbicort()
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
    String connectStuff = "";
    String userid = "";
    String password = "";
    String select1 = "";
    select1 = select1 + "SELECT segment_name rollback,tablespace_name tablespace,";
    select1 = select1 + "  initial_extent,next_extent,max_extents";
    select1 = select1 + "  FROM dba_rollback_segs";
    select1 = select1 + " ORDER BY segment_name";

    Symbicort applet = new Symbicort();
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
    frame.setSize(500, 300);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
    frame.setVisible(true);
    try{
     DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
     
       Connection conn = DriverManager.getConnection (connectStuff,userid, password);
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  

  }
    private void addRecord(String bSailor,String bType,String bNum) {
    	String insCmd = "INSERT INTO sailors";	
		insCmd = insCmd + "(sailor,boattype,boatnumber)"; 
		insCmd = insCmd + "VALUES("; 
		insCmd = insCmd + "'" + bSailor + "'" + ",";
		insCmd = insCmd + "'" + bType + "'" + ","; 
		insCmd = insCmd + "'" + bNum + "'"; 
		insCmd = insCmd +")";
		
//		System.out.println(insCmd);
		try {
			Statement stmt = conn.createStatement ();
			stmt.executeUpdate(insCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    	loadSailors();
    }
    


  private void jbInit() throws Exception
  {
    this.setLayout(null);
    jComboBox1.setBounds(new Rectangle(65, 60, 155, 20));
    jTextField2.setText("jTextField2");
    jTextField2.setBounds(new Rectangle(65, 125, 64, 19));
    jTextField3.setText("jTextField3");
    jTextField3.setBounds(new Rectangle(65, 160, 64, 19));
    jTextField4.setText("jTextField4");
    jTextField4.setBounds(new Rectangle(65, 195, 64, 19));
    jTextField5.setText("jTextField5");
    jTextField5.setBounds(new Rectangle(65, 230, 64, 19));
    jButton1.setText("Insert");
    jButton1.setBounds(new Rectangle(270, 185, 73, 23));
    jButton2.setText("Commit");
    jButton2.setBounds(new Rectangle(270, 235, 73, 23));
    this.add(jButton2, null);
    this.add(jButton1, null);
    this.add(jTextField5, null);
    this.add(jTextField4, null);
    this.add(jTextField3, null);
    this.add(jTextField2, null);
    this.add(jComboBox1, null);
  }
}