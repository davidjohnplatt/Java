import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;



public class maintainInspectors implements ActionListener{
    JFrame mainFrame;
    BorderLayout layout;
    GridBagLayout gridBag;
    GridBagConstraints gbc;
    Container contentPane;
    Container cPane;
    JPanel cPanel;
    JPanel ePanel;
    JPanel wPanel;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    
    Choice choice1;
    
    JTextField jText1;   
    JTextField jText2;
    JTextField jText3;
    JTextField jText4;
    
    JButton jButton1;
    JButton jButton2;
    
    Connection connection;
   
      
   	public maintainInspectors() {
   		mainFrame = new JFrame("Maintain Inspectors");
        mainFrame.setSize(200,200);
        contentPane = mainFrame.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);
        
       
// this would set componenets to fill available space - not wanted here  
//      gbc.fill = GridBagConstraints.HORIZONTAL;
    
  	    System.out.println("Starting maintainInspectors");
  	    addWidgets();

// Exit when the window is closed.
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       	mainFrame.setSize(360,360);
		mainFrame.setVisible(true);
		try {
        	Class.forName("com.mysql.jdbc.Driver");
      	}
      	catch(ClassNotFoundException cnfe) {
        	System.err.println(cnfe);
      	}
		Class.forName("com.mysql.jdbc.Driver");
        openConnection();
	}
	
	private void addWidgets(){
        jText1 = new JTextField(20);
        jText2 = new JTextField(20);
        jText3 = new JTextField(12);
        jText4 = new JTextField(12);
       
        choice1 = new Choice();
        
        
        jLabel1 = new JLabel("First Name   ");
        jLabel2 = new JLabel("Inspector    ");
        jLabel3 = new JLabel("Home Phone   ");
        jLabel4 = new JLabel("Mobile Phone ");
        
 
        JLabel dummyLabel = new JLabel(" ");
        
        gbc.anchor = GridBagConstraints.WEST;
         
        gbc.gridx = 1;
        gbc.gridy = 1;
        gridBag.setConstraints(choice1,gbc);
        contentPane.add(choice1);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gridBag.setConstraints(jLabel2,gbc);
        contentPane.add(jLabel2); 
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gridBag.setConstraints(jText2,gbc);	
        contentPane.add(jText2);
 
         
        gbc.gridx = 0;
        gbc.gridy = 5;       
        gridBag.setConstraints(jLabel3,gbc);
        contentPane.add(jLabel3); 
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        gridBag.setConstraints(jText3,gbc);	
        contentPane.add(jText3);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gridBag.setConstraints(jLabel4,gbc);
        contentPane.add(jLabel4); 
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        gridBag.setConstraints(jText4,gbc);	
        contentPane.add(jText4);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gridBag.setConstraints(dummyLabel,gbc);
        contentPane.add(dummyLabel); 
        
        gbc.anchor = GridBagConstraints.EAST;
        
        jButton1 = new JButton("Add");
        jButton1.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gridBag.setConstraints(jButton1,gbc);
        contentPane.add(jButton1);
        
        jButton2 = new JButton("Show");
        jButton2.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gridBag.setConstraints(jButton2,gbc);
        contentPane.add(jButton2);
      
	}
	
	public void openConnection()  {
    try {
    	//if there is a connection close it
      	if(connection != null) {                               
        	connection.close();
        }


      	// Now open the new connection
      	connection = DriverManager.getConnection("jdbc:mysql://localhost/inspections");
     	// status.setText("Database connection established");
 
     

    }
    catch(SQLException sqle){
 //     		status.setText(sqle.getMessage());            
        	System.err.println("Exception occurred:\nMessage: " + sqle.getMessage());
        	System.err.println("SQL state: " + sqle.getSQLState());
        
    }
 
  	}
	

  	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	if (source == jButton1){
    		System.out.println("Button1 pushed");   	
    	} else if (source == jButton2){
    		System.out.println("Button2 pushed"); 
    	} else if (source == jButton1) {

    	} 
	}
	
	public void fillChoice(){
		
	}
	
	
	public static void main(String[] args) {
		maintainInspectors m = new maintainInspectors();
		
		
		
	}


  }