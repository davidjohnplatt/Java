
 package mypackage;
       
       import javax.swing.JApplet;
       import java.awt.Dimension;
       import java.awt.Toolkit;
       import java.awt.BorderLayout;
       import javax.swing.JFrame;
       import javax.swing.UIManager;
       import javax.swing.JButton;
       import java.awt.Rectangle;
       import javax.swing.JTextArea
       import java.awt.event.ActionListener;
       import java.awt.event.ActionEvent;
       import java.sql.*;
       import javax.swing.JLabel;
       
       public class Applet1 extends JApplet {
       
         private JButton DbConnect = new JButton();
         private JTextArea empValues = new JTextArea();
         private Connection con;
         private JLabel jLabel1 = new JLabel();
       
         public Applet1()  {
         }
       
         public void init()  {
           try {
             jbInit();
           } catch(Exception e) {
             e.printStackTrace();
           }
         }
       
         private void jbInit() throws Exception {
           this.getContentPane().setLayout(null);
           DbConnect.setText("Click to Connect to DB");
           DbConnect.setBounds(new Rectangle(90, 225, 175, 40));
           DbConnect.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e)   {
                 connectToDB(e);
               }
             });
           empValues.setBounds(new Rectangle(40, 50, 310, 150));
           jLabel1.setText("Emp Records :");
           jLabel1.setBounds(new Rectangle(45, 20, 155, 20));
           this.getContentPane().add(jLabel1, null);
           this.getContentPane().add(empValues, null);
           this.getContentPane().add(DbConnect, null);
         }
       
       
         private void connectToDB(ActionEvent e) {
           try {
             DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
             con = DriverManager.getConnection ("jdbc:oracle:thin:@192.168.1.199:1521:rxdata","scott","tiger");
             empValues.setText("Connected to the Database. Fetching Values from DEPT Tables.\n");
             fetchValues();
           } catch (SQLException ex)     {
             System.out.println("Connection Error =  "  + ex.toString());
           }
         }
         
         public void fetchValues() {
          try {
           Statement stmt = con.createStatement();
           StringBuffer allRowValues = new StringBuffer();
           int counter = 1;
           ResultSet rset = stmt.executeQuery("SELECT ENAME, EMPNO FROM EMP");
           while (rset.next())
           {
             allRowValues.append("ROW " + counter +  ":  ENAME = " + rset.getString(1) + " &  ENO = " + rset.getString(2) + "\n");
             counter++;
           }
           empValues.setText(allRowValues.toString());
           rset.close();
           stmt.close();
           con.close();
         } catch (SQLException ex) 
         {
           System.out.println("Error While Fetching Values =  "  +  ex.toString());
         }
         }
       }
