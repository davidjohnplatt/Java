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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.sql.*;


public class CallBack extends Applet 
{
  private JComboBox jComboBox1 = new JComboBox();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JComboBox jComboBox2 = new JComboBox();
  private JLabel jLabel7 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();

  public CallBack()
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

  private void jbInit() throws Exception
  {
    this.setLayout(null);
    this.setSize(new Dimension(505, 324));
    jComboBox1.setBounds(new Rectangle(100, 55, 160, 20));
    jTextField1.setBounds(new Rectangle(100, 90, 140, 20));
    jTextField2.setBounds(new Rectangle(100, 130, 140, 20));
    jTextField3.setBounds(new Rectangle(100, 170, 140, 20));
    jTextField4.setBounds(new Rectangle(100, 210, 145, 20));
    jLabel1.setText("Chain:");
    jLabel1.setBounds(new Rectangle(15, 55, 70, 20));
    jLabel2.setText("Store#");
    jLabel2.setBounds(new Rectangle(15, 90, 75, 20));
    jLabel3.setText("Presription#");
    jLabel3.setBounds(new Rectangle(15, 130, 75, 20));
    jLabel4.setText("Transaction#");
    jLabel4.setBounds(new Rectangle(15, 170, 70, 20));
    jLabel5.setText(" Date");
    jLabel5.setBounds(new Rectangle(15, 215, 75, 20));
    jLabel6.setText("Molecule");
    jLabel6.setBounds(new Rectangle(20, 255, 70, 20));
    jComboBox2.setBounds(new Rectangle(100, 260, 150, 20));
    jLabel7.setText("Callback Entry");
    jLabel7.setBounds(new Rectangle(75, 15, 200, 30));
    jLabel7.setFont(new Font("Tahoma", 1, 25));
    jButton1.setText("Insert");
    jButton1.setBounds(new Rectangle(360, 160, 73, 23));
    jButton2.setText("Commit");
    jButton2.setBounds(new Rectangle(360, 195, 73, 23));
    this.add(jButton2, null);
    this.add(jButton1, null);
    this.add(jLabel7, null);
    this.add(jComboBox2, null);
    this.add(jLabel6, null);
    this.add(jLabel5, null);
    this.add(jLabel4, null);
    this.add(jLabel3, null);
    this.add(jLabel2, null);
    this.add(jLabel1, null);
    this.add(jTextField4, null);
    this.add(jTextField3, null);
    this.add(jTextField2, null);
    this.add(jTextField1, null);
    this.add(jComboBox1, null);
  }

  private void jbMain() throws Exception
        try{
       	  Class.forName("com.mysql.jdbc.Driver").newInstance();
    	  conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    	}
    	catch (SQLException e) {
    	}
    	catch (ClassNotFoundException f){
    		System.out.println(f);
    	}
    	catch (InstantiationException i) {
    	}
    	catch (IllegalAccessException a) {
    	}
  }
  
}
