
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.sql.*;


public class Sailor extends JFrame implements ActionListener{
	String sailor;
	String boatType;
	String boatNum;
	
	Choice sailorChoice;
	
	JTextField jTextSailor;
   	JTextField jTextBoatType;
   	JTextField jTextBoatNum;
   	
   	JLabel sailorLabel;
   	JLabel boatTypeLabel;
   	JLabel boatNumLabel;
   	
   	JButton addButton;
   	JButton updateButton;
   	JButton deleteButton;
   	
   	GridBagLayout gridBag;
   	GridBagConstraints gbc;
   	Container contentPane;
   	Connection conn;
    	
	
	public Sailor() {
		super();
//		JPanel panel = new JPanel();
		contentPane = this.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);
        sailorChoice = new Choice();
 
		try {
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
		
		
		addWidgets();
		loadSailors();
//		contentPane.add(panel);
		this.setSize(500,200);
		this.setTitle("Sailor");
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	private void addWidgets() {
		int xbase = 0;
		int ybase = 0;
		
		jTextSailor = new JTextField(15);
		jTextBoatType = new JTextField(15);
        jTextBoatNum = new JTextField(7);
        
        JLabel sailorLabel = new JLabel("Sailor");
        JLabel boatTypeLabel = new JLabel("Boat Type");
    	JLabel boatNumLabel = new JLabel("Boat Number");
    	
    	JLabel dummyLabel1 = new JLabel("           ");
        sailorChoice = new Choice();
        
        addButton = new JButton("  Add ");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        
        gbc.anchor = GridBagConstraints.WEST;
        putWidget(xbase,ybase,sailorChoice);
        SailorListener controlBoatType = new SailorListener();
        sailorChoice.addItemListener(controlBoatType); 
        
        xbase++;
        gbc.anchor = GridBagConstraints.EAST;
        putWidget(xbase,ybase,sailorLabel);
        putWidget(xbase,ybase + 1,boatTypeLabel);
        putWidget(xbase,ybase + 2,boatNumLabel);
        
        gbc.anchor = GridBagConstraints.WEST;
        xbase++;
        putWidget(xbase,ybase,jTextSailor);
        putWidget(xbase,ybase + 1,jTextBoatType);
        putWidget(xbase,ybase + 2,jTextBoatNum);
        
        
        
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
	
	private void loadSailors() { 
	   sailorChoice.removeAll();
	   sailorChoice.add("...");
 	   try {
 	   		String query = "SELECT * FROM sailors ORDER BY sailor";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               sailorChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
	}  //loadBoats
	
    private void addSailor(String bSailor,String bType,String bNum) {
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
    
    private void updateSailor(String bType,String bRating, String bNum) {
    	String updCmd = "UPDATE sailors SET boattype = '" + bRating + "'";
    	updCmd = updCmd + ",boatnumber  = '" + bNum + "'";
    	updCmd = updCmd + " WHERE sailor = '" + bType + "'";
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
    
    private void deleteSailor(String bSailor) {
    	String delCmd = "DELETE FROM sailors WHERE sailor= '" + bSailor + "'";
    	try {
			Statement stmt = conn.createStatement ();
			stmt.executeUpdate(delCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    	loadSailors();
    }
    
    private void clearScreen() {
    	jTextSailor.setText("");
    	jTextBoatType.setText("");
    	jTextBoatNum.setText("");
    }
    
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	if (source == addButton){
    		addSailor(jTextSailor.getText(),jTextBoatType.getText(),jTextBoatNum.getText());
    		clearScreen();   		
    	} else if (source == updateButton){ 
    		updateSailor(jTextSailor.getText(),jTextBoatType.getText(),jTextBoatNum.getText()); 
    		clearScreen();          
    	} else if (source == deleteButton) {
            deleteSailor(jTextBoatType.getText());
            clearScreen();
    	} 
	}
	
	public static void main (String [] args) {
		Sailor s = new Sailor();
	}
	
	class SailorListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		jTextSailor.setText((String)sailorChoice.getSelectedItem());
      		
      		String query = "SELECT * FROM sailors WHERE sailor = ";
      		query = query  + "'" + sailorChoice.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
       			jTextBoatType.setText(rset.getString(2));
       			jTextBoatNum.setText(rset.getString(3));
       		}
       		catch (SQLException f){
       			ErrorFrame err = new ErrorFrame(f.toString());
       		} 
    	}
  	}
}//  Boat

 