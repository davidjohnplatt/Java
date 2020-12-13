

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.sql.*;


public class Race extends JFrame implements ActionListener{
	String raceNo;
	String raceDate;
	String committee;
	Choice raceChoice;
	JTextField jTextRaceNo;
   	JTextField jTextRaceDate;
   	JTextField jTextCommittee;
   	JLabel racenoLabel;
   	JLabel raceDateLabel;
   	JLabel committeeLabel;
   	JButton addButton;
   	JButton updateButton;
   	JButton deleteButton;
   	GridBagLayout gridBag;
   	GridBagConstraints gbc;
   	Container contentPane;
   	Connection conn;
    	
	
	public Race() {
		super();
		contentPane = this.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);
        raceChoice = new Choice();
 
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
		
		
		addWidgets();
		loadRaces();
//		contentPane.add(panel);
		this.setSize(400,200);
		this.setTitle("Race");
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	private void addWidgets() {
		
		jTextRaceNo = new JTextField(4);
        jTextRaceDate = new JTextField(10);
        jTextCommittee = new JTextField(15);
        JLabel raceNoLabel = new JLabel("Race");
    	JLabel raceDateLabel = new JLabel("Date");
    	JLabel committeeLabel = new JLabel("Race Committee");
    	JLabel dummyLabel1 = new JLabel("           ");
        raceChoice = new Choice();
        addButton = new JButton("  Add ");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        
        gbc.anchor = GridBagConstraints.WEST;
        putWidget(0,0,raceChoice);
        RaceNoListener controlRaceNo = new RaceNoListener();
        raceChoice.addItemListener(controlRaceNo); 
        
        putWidget(2,0,jTextRaceNo);
        putWidget(2,1,jTextRaceDate);
        putWidget(2,2,jTextCommittee);
        
        gbc.anchor = GridBagConstraints.EAST;
        putWidget(1,0,raceNoLabel);
        putWidget(1,1,raceDateLabel);
        putWidget(1,2,committeeLabel);
        
        gbc.anchor = GridBagConstraints.CENTER;
        putWidget(1,3,dummyLabel1);
        putWidget(0,4,addButton);
        addButton.addActionListener(this);
        putWidget(1,4,updateButton);
        updateButton.addActionListener(this);
        putWidget(2,4,deleteButton);
        deleteButton.addActionListener(this);
	}
		
	private void putWidget(int x, int y, Component c){
		gbc.gridx = x;
        gbc.gridy = y;
        gridBag.setConstraints(c,gbc);
        contentPane.add(c);
	}
	
	private void loadRaces() { 
	   raceChoice.removeAll();
	   raceChoice.add("...");
 	   try {
 	   		String query = "SELECT * FROM races ORDER BY raceno";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               raceChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
	}  //loadRaces
	
    private void addRace(String bRaceNo,String bRaceDate,String bCommittee) {
    	String insCmd = "INSERT INTO races";	
		insCmd = insCmd + "(raceno,racedate,racecomittee)"; 
		insCmd = insCmd + "values("; 
		insCmd = insCmd + "'" + bRaceNo + "'" + ",";
		insCmd = insCmd + "'" + bRaceDate + "'" + ",";
		insCmd = insCmd + "'" + bCommittee + "'"; 
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
    	loadRaces();
    }
    
    private void updateRace(String bRaceNo,String bRaceDate,String bCommittee) {
    	String updCmd = "UPDATE races SET racedate = '" + bRaceDate + "',";
    	updCmd = updCmd + "racecomittee = '" + bCommittee + "'";
    	updCmd = updCmd + " WHERE raceno = '" + bRaceNo + "'";
//    	System.out.println(updCmd);
    	try {
			Statement stmt = conn.createStatement ();
			stmt.executeUpdate(updCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    }
    
    private void deleteRace(String bRaceNo) {
    	String delCmd = "DELETE FROM races WHERE raceno  = '" + bRaceNo + "'";
    	try {
			Statement stmt = conn.createStatement ();
			stmt.executeUpdate(delCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    }
    
    private void clearScreen() {
    	jTextRaceNo.setText("");
    	jTextRaceDate.setText("");
    	jTextCommittee.setText("");
    }
    
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	if (source == addButton){
    		addRace(jTextRaceNo.getText(),jTextRaceDate.getText(),jTextCommittee.getText());
    		clearScreen();   		
    	} else if (source == updateButton){ 
    		updateRace(jTextRaceNo.getText(),jTextRaceDate.getText(),jTextCommittee.getText()); 
    		clearScreen();          
    	} else if (source == deleteButton) {
            deleteRace(jTextRaceNo.getText());
            clearScreen();
    	} 
	}
	
	public static void main (String [] args) {
		Race r = new Race();
	}
	
	class RaceNoListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		jTextRaceNo.setText((String)raceChoice.getSelectedItem());
      		
      		String query = "SELECT * FROM races WHERE raceno = ";
      		query = query  + "'" + raceChoice.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
        		jTextRaceNo.setText(rset.getString(1));
       			jTextRaceDate.setText(rset.getString(2));
       			jTextCommittee.setText(rset.getString(3));
       		}
       		catch (SQLException f){
       			ErrorFrame err = new ErrorFrame(f.toString());
       		} 
    	}
  	}
}//  Boat