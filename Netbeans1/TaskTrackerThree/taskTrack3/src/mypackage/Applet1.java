package mypackage;
       
       import javax.swing.JApplet;
       import java.awt.*;
  //   import java.awt.Dimension;
  //   import java.awt.Toolkit;
  //   import java.awt.BorderLayout;
  //   import java.awt.Rectangle;
       import java.awt.event.ActionListener;
       import java.awt.event.ActionEvent;
       import javax.swing.*;
 //    import javax.swing.JFrame;
 //    import javax.swing.JPanel;
  //   import javax.swing.UIManager;
  //   import javax.swing.JButton
  //   import javax.swing.JTextArea;
  //   import javax.swing.JLabel;
       import java.sql.*;
  
       
       public class Applet1 extends JApplet {
       
         private JButton DbConnect = new JButton();
         private JButton DbCommit = new JButton();
         private JTextArea empValues = new JTextArea();
         private Connection con;
         private JLabel jLabel1 = new JLabel();
         private JLabel jLabel2 = new JLabel();
         private JLabel jLabel3 = new JLabel();
         private JLabel jLabel4 = new JLabel();
         private JLabel jLabel5 = new JLabel();
         private JTextField mTask = new JTextField();
         private JTextField mTaskName = new JTextField();
         private JTextField mDBstatus = new JTextField();
         private JTextField mRequestor = new JTextField();
         private Choice projectChoice = new Choice();
       
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

           jLabel1.setText("Project :");
           jLabel1.setBounds(new Rectangle(10, 10, 200, 50));
           projectChoice.setBounds(new Rectangle(120,25,300,20));
           jLabel2.setBounds(new Rectangle(10, 45, 200, 50));
           jLabel2.setText("Task    :");
           mTask.setBounds(new Rectangle(120,60,60,20));
           jLabel3.setBounds(new Rectangle(10, 80, 200, 50));
           jLabel3.setText("Task Name:");
           mTaskName.setBounds(new Rectangle(120,95,200,20));
           mDBstatus.setBounds(new Rectangle(120,400,400,20));
           jLabel4.setBounds(new Rectangle(10, 115, 200, 50));
           jLabel4.setText("Requestor :");
           mRequestor.setBounds(new Rectangle(120, 130, 100, 20));
           jLabel5.setBounds(new Rectangle(10, 155, 200, 50));
           jLabel5.setText("Task Description :");
           empValues.setBounds(new Rectangle(120, 175, 310, 150));
           
           DbConnect.setText(" Connect");
           DbConnect.setBounds(new Rectangle(120, 350, 100, 40));
           DbConnect.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e)   {
                 connectToDB(e);
               }
             }
           );
           
           DbCommit.setText(" Commit");
           DbCommit.setBounds(new Rectangle(230, 350, 100, 40));
           DbCommit.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e)   {
                 insertWhiteboardDetail();
               }
             }
           );
           
           this.getContentPane().add(jLabel1, null);
           this.getContentPane().add(jLabel2, null);
           this.getContentPane().add(jLabel3, null);
           this.getContentPane().add(jLabel4, null);
           this.getContentPane().add(jLabel5, null);
           this.getContentPane().add(empValues, null);
           this.getContentPane().add(DbConnect, null);
           this.getContentPane().add(mTask, null);
           this.getContentPane().add(mDBstatus, null);
           this.getContentPane().add(mRequestor, null);
           this.getContentPane().add(projectChoice, null);
           this.getContentPane().add(projectChoice, null);
           this.getContentPane().add(mTaskName, null);
           this.getContentPane().add(DbCommit, null);
         }
       
       
private void connectToDB(ActionEvent e) {
   try {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","david","justus");
        getmaxSequence();
        loadTasks();
    } catch (SQLException ex)     {
        mDBstatus.setText("Connection Error =  "  + ex.toString());
    }
}
         
public void fetchValues() {
   try {
       Statement stmt = con.createStatement();
       StringBuffer allRowValues = new StringBuffer();
       int counter = 1;
       ResultSet rset = stmt.executeQuery("SELECT EXPENSE_DATE,EXPENSE_AMT FROM EXPENSES");
       while (rset.next()) {
          allRowValues.append("ROW " + counter +  ": ENAME = " + rset.getString(1) + " &  ENO = " + rset.getString(2) + "\n");
          counter++;
       }
       empValues.setText(allRowValues.toString());
       rset.close();
       stmt.close();
 //    con.close();
    } catch (SQLException ex) {
          mDBstatus.setText("Error While Fetching Values =  "  +  ex.toString());
          
    }
}
         
public void getmaxSequence(){
   try {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT MAX(wb_task) + 1 FROM whiteboard_detail");
       while (rset.next()){
          mTask.setText( rset.getString(1));
       }
   } catch (SQLException ex) {
       mDBstatus.setText("Error While Fetching Values =  "  +  ex.toString());
   }
}
         
private void loadTasks() { 
    projectChoice.removeAll();
    try {
 	String query = "SELECT * FROM whiteboard_master  WHERE wb_completion_date IS NULL ORDER BY wb_project";
        Statement stmt = con.createStatement ();
        ResultSet rset = stmt.executeQuery (query);
        while (rset.next ()) {
            projectChoice.add(rset.getString(1) + "     " + rset.getString(4));
        }   	
    }
    catch (SQLException e) {
      	ErrorFrame err = new ErrorFrame(e.toString());
    }
}  //loadTasks

private void insertWhiteboardDetail() { 
    try {
 	String query = "INSERT INTO whiteboard_detail ";
        query = query + "(wb_project,wb_task,wb_task_name,wb_task_description,wb_requestor,wb_request_date) ";
        query = query + "VALUES ";
        query = query + "('" + projectChoice.getSelectedItem().substring(0,4) + "',";
        query = query + "'" + mTask.getText() + "',";
        query = query + "'" + mTaskName.getText()+ "',";
        query = query + "'" + mRequestor.getText()+ "',";
        query = query + "'" + empValues.getText()+ "',";
        query = query + "SYSDATE)";
        mDBstatus.setText(query);
//        mDBstatus.setText(projectChoice.getSelectedItem().substring(1,4));
        Statement stmt = con.createStatement ();
        ResultSet rset = stmt.executeQuery (query);
        mDBstatus.setText("Task added");
        projectChoice.removeAll();
        mTask.setText("");
        mTaskName.setText("");
        mRequestor.setText("");
        empValues.setText("");
        con.close();
    }
    catch (SQLException e) {
      	ErrorFrame err = new ErrorFrame(e.toString());
        
    }
}  //loadTasks

public class ErrorFrame extends JFrame {
	
	
	public ErrorFrame() {
	}
	
	public ErrorFrame(String inStr) {
		super();
		JPanel panel = new JPanel();
		Container contentPane = this.getContentPane();
		JLabel J = new JLabel(inStr);
		panel.add(J);
		contentPane.add(panel);
		this.setSize(inStr.length() * 8, 75);
		this.setTitle("Error");
		this.setVisible(true);
/*		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});*/
	}

}//  ErrorFrame
         
}
       
