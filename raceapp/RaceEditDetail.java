import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;

public class RaceEditDetail extends javax.swing.JFrame implements ActionListener{
    JPanel jPanel1;
    JLabel jLabel1;
    JTextField jTextField1;
    JLabel jLabel2;
    JTextField jTextField2;
    JLabel jLabel3;
    JTextField jTextField3;
    JLabel jLabel4;
    JTextField jTextField4;
    JLabel jLabel5;
    JTextField jTextField5;
    JLabel jLabel6;
    JTextField jTextField6;
    JLabel jLabel7 ;
    JTextField jTextField7;
    JLabel jLabel8;
    JTextField jTextField8;
    JLabel jLabel9 ;
    JTextField jTextField9 ;
   	JButton updateButton;
    Choice racenoChoice;
    Choice sailorChoice;
    Connection conn;
    
    public RaceEditDetail()
    {
    	super();
        initComponents();
        loadSailors();
        loadRaces();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField(5);
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        sailorChoice = new Choice();
        racenoChoice = new Choice();
        updateButton = new javax.swing.JButton("Update");

        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    	}
    	catch (SQLException e) {
    	}
    	catch (ClassNotFoundException f){
    	}
    	catch (InstantiationException i) {
    	}
    	catch (IllegalAccessException a) {
    	}

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Raceno");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

  
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField1, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(racenoChoice, gridBagConstraints);
        racenoListener controlRaceno = new racenoListener();
        racenoChoice.addItemListener(controlRaceno); 


        jLabel2.setText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

    
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField2, gridBagConstraints);

        jLabel3.setText("Sailor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel3, gridBagConstraints);

     
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField3, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(sailorChoice, gridBagConstraints);
        sailorListener controlSailor = new sailorListener();
        sailorChoice.addItemListener(controlSailor); 



        jLabel4.setText("Boatnumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel4, gridBagConstraints);

      
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField4, gridBagConstraints);

        jLabel5.setText("Boattype");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel5, gridBagConstraints);

      
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField5, gridBagConstraints);

        jLabel6.setText("Boatrating");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel6, gridBagConstraints);

       
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField6, gridBagConstraints);

        jLabel7.setText("Elapsed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel7, gridBagConstraints);

       
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField7, gridBagConstraints);

        jLabel8.setText("Corrected");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel8, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField8, gridBagConstraints);

        jLabel9.setText("Points");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel9, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jTextField9, gridBagConstraints);
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(updateButton, gridBagConstraints);
        updateButton.addActionListener(this);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        show();
    }

	private void clearScreen() {
		jTextField1.setText("");
        jTextField2.setText("");
       	jTextField3.setText("");
      	jTextField4.setText("");
       	jTextField5.setText("");
       	jTextField6.setText("");
       	jTextField7.setText("");
       	jTextField8.setText("");
       	jTextField9.setText("");
	}
	
	private void loadSailors() { 
	   sailorChoice.removeAll();
	   sailorChoice.add("...");
 	   try {
 	   		String query = "SELECT sailor FROM race_detail WHERE raceno = ";
 	   		query = query +  "'" + racenoChoice.getSelectedItem() +"' ORDER BY sailor";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               sailorChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
	}  //loadSailors
	
	private void loadRaces() { 
	   racenoChoice.removeAll();
	   racenoChoice.add("...");
 	   try {
 	   		String query = "SELECT raceno FROM races ORDER BY raceno";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               racenoChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
	}  //loadRaces
	
	private void updateRaceElement() {
		String raceNo = jTextField1.getText();
		String sailor = jTextField3.getText();
    	String updCmd = "UPDATE race_detail SET";
    	updCmd = updCmd + " position = '" + jTextField2.getText() + "',";
    	updCmd = updCmd + " boatnumber = '" + jTextField4.getText() + "',";
    	updCmd = updCmd + " boattype = '" + jTextField5.getText() + "',";
    	updCmd = updCmd + " boatrating = '" + jTextField6.getText() + "',";
    	updCmd = updCmd + " elapsed = '" + jTextField7.getText() + "',";
    	updCmd = updCmd + " corrected = '" + jTextField8.getText() + "',";
    	updCmd = updCmd + " points = '" + jTextField9.getText() + "'";
    	updCmd = updCmd + " WHERE raceno = '" + raceNo + "'";
    	updCmd = updCmd + " AND sailor = '" + sailor +"'";
    
    	System.out.println(updCmd);
    	try {
			Statement stmt = conn.createStatement ();
			stmt.executeUpdate(updCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    }
    
   	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	if (source == updateButton){
    		updateRaceElement();
    		clearScreen();   	
    	} 
	}
	
    public static void main(String[] args) {
        new RaceEditDetail().show(); 
    }
     
    class racenoListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		clearScreen();
      	    loadSailors();
    	}
  	}
  	
  	class sailorListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		
      		String query = "SELECT * FROM race_detail WHERE raceno = ";
      		query = query + "'" + racenoChoice.getSelectedItem() + "'";
      		query = query + " AND sailor = '" + sailorChoice.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
        		jTextField1.setText(rset.getString(1));
               	jTextField2.setText(rset.getString(2));
               	jTextField3.setText(rset.getString(3));
               	jTextField4.setText(rset.getString(4));
               	jTextField5.setText(rset.getString(5));
               	jTextField6.setText(rset.getString(6));
               	jTextField7.setText(rset.getString(7));
               	jTextField8.setText(rset.getString(8));
               	jTextField9.setText(rset.getString(9));
       		}
       		catch (SQLException f){
       			System.out.println(query);
       			System.out.println(f);
       		} 
    	}
  	}
}