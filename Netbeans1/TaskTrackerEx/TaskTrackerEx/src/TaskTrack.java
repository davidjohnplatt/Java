/*
 * TaskTrack.java
 *
 * Created on December 4, 2006, 6:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.awt.*;
import java.applet.*;
// import an extra class for the ActionListener
import java.awt.event.*;
import java.sql.*;
import oracle.jdbc.driver.OracleDriver;
/**
 *
 * @author David
 */
public class TaskTrack extends Applet 
{
     Button okButton;
     Button wrongButton;
     TextField nameField;
     CheckboxGroup radioGroup;
     Checkbox radio1;
     Checkbox radio2;
     Checkbox radio3;
     Connection conn; 

 
          
    /** Creates a new instance of TaskTrack */
    public TaskTrack() {
        
    }
    
    public void init() 
    {
  // Now we will use the FlowLayout
          setLayout(new FlowLayout());
          okButton = new Button("Action!");
          wrongButton = new Button("Don't click!");
          nameField = new TextField("Type here Something",35);
          radioGroup = new CheckboxGroup();
          radio1 = new Checkbox("Red", radioGroup,false);
          radio2 = new Checkbox("Blue", radioGroup,true);
          radio3 = new Checkbox("Green", radioGroup,false);
          String connString="jdbc:oracle:thin:@prodHost:1521:XE";
//          String connString = "jdbc:oracle:thin:@(description=(address_list=(address=(protocol=tcp)(port=1521)(host=localhost)))";
/*          OracleDataSource ods = new OracleDataSource();
          ods.setURL(connString);
          ods.setUser("scott");
          ods.setPassword("tiger");
//          Connection conn = ods.getConnection();*/
          add(okButton);
          add(wrongButton);
          add(nameField);
          add(radio1);
          add(radio2);
          add(radio3);
          CapitalizerAction  ca = new CapitalizerAction(nameField, nameField);
          okButton.addActionListener(ca);
//          Class.forName ("oracle.jdbc.driver.OracleDriver");
/*      try {
     conn =  DriverManager.getConnection ("jdbc:oracle:thin:scott/tiger@www-aurora.us.oracle.com:1521:rdbms3");
        }
      catch{
*/          
      }
     private void connectToDB(ActionEvent e) {
        try {
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
           nameField.setText("Connected to the Database.   " );
        } catch (SQLException ex) {
             System.out.println("Connection Error =  "  + ex.toString());
        }
      }

           
     
    
            
         public void fetchValues() {
          try {
           Statement stmt = conn.createStatement();
           StringBuffer allRowValues = new StringBuffer();
           int counter = 1;
           ResultSet rset = stmt.executeQuery("SELECT ENAME, EMPNO FROM EMP");
           while (rset.next())
           {
             allRowValues.append("ROW " + counter +  ":ENAME = " + rset.getString(1) + " &  ENO = " + rset.getString(2) + "\n");
             counter++;
           }
           nameField.setText(allRowValues.toString());
           rset.close();
           stmt.close();
           conn.close();
         } catch (SQLException ex) 
         {
           System.out.println("Error While Fetching Values =  "  +  ex.toString());
         }
         }
    
  class CapitalizerAction implements ActionListener {
      private TextField in;
      private TextField out;

      public CapitalizerAction(TextField in, TextField out) {
        this.in = in;
        this.out = out;
      }
      
      public void actionPerformed(ActionEvent ae) {
        String s = in.getText();
        out.setText(s.toUpperCase());

  }
  }
    
}


