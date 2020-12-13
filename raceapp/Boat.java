

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.sql.*;


public class Boat extends JFrame implements ActionListener{
	String boatType;
	double boatRating;
	Choice boatChoice;
	JTextField jTextBoatType;
   	JTextField jTextBoatRating;
   	JLabel boatTypeLabel;
   	JLabel boatRatingLabel;
   	JButton addButton;
   	JButton updateButton;
   	JButton deleteButton;
   	GridBagLayout gridBag;
   	GridBagConstraints gbc;
   	Container contentPane;
   	Connection conn;
    	
	
	public Boat() {
		super();
//		JPanel panel = new JPanel();
		contentPane = this.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);
        boatChoice = new Choice();
 
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
    		System.out.println(i);
    	}
    	catch (IllegalAccessException a) {
    		System.out.println(a);
    	}
		
		
		addWidgets();
		loadBoats();
//		contentPane.add(panel);
		this.setSize(400,200);
		this.setTitle("Boat");
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	private void addWidgets() {
		
		jTextBoatType = new JTextField(15);
        jTextBoatRating = new JTextField(3);
        JLabel boatTypeLabel = new JLabel("Boat");
    	JLabel boatRatingLabel = new JLabel("Rating");
    	JLabel dummyLabel1 = new JLabel("           ");
        boatChoice = new Choice();
        addButton = new JButton("  Add ");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        
        gbc.anchor = GridBagConstraints.WEST;
        putWidget(0,0,boatChoice);
        BoatTypeListener controlBoatType = new BoatTypeListener();
        boatChoice.addItemListener(controlBoatType); 
        
        putWidget(2,1,jTextBoatRating);
        putWidget(2,0,jTextBoatType);
        
        gbc.anchor = GridBagConstraints.EAST;
        putWidget(1,0,boatTypeLabel);
        putWidget(1,1,boatRatingLabel);
        
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
	
	private void loadBoats() { 
	   boatChoice.removeAll();
 	   try {
 	   		String query = "SELECT * FROM boats ORDER BY boattype";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               boatChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
	}  //loadBoats
	
    private void addBoat(String bType,String bRating) {
    	String insCmd = "INSERT INTO boats ";	
		insCmd = insCmd + "(boattype,boatrating)"; 
		insCmd = insCmd + "values("; 
		insCmd = insCmd + "'" + bType + "'" + ",";
		insCmd = insCmd + "'" + bRating + "'"; 
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
    	loadBoats();
    }
    
    private void updateBoat(String bType,String bRating) {
    	String updCmd = "UPDATE boats SET boatrating = '" + bRating + "'";
    	updCmd = updCmd + " WHERE boattype = '" + bType + "'";
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
    
    private void deleteBoat(String bType) {
    	String delCmd = "DELETE FROM boats WHERE boattype = '" + bType + "'";
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
    	jTextBoatType.setText("");
    	jTextBoatRating.setText("");
    }
    
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	if (source == addButton){
    		addBoat(jTextBoatType.getText(),jTextBoatRating.getText());
    		clearScreen();   		
    	} else if (source == updateButton){ 
    		updateBoat(jTextBoatType.getText(),jTextBoatRating.getText()); 
    		clearScreen();          
    	} else if (source == deleteButton) {
            deleteBoat(jTextBoatType.getText());
            clearScreen();
    	} 
	}
	
	public static void main (String [] args) {
		Boat b = new Boat();
	}
	
	class BoatTypeListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		jTextBoatType.setText((String)boatChoice.getSelectedItem());
      		
      		String query = "SELECT * FROM boats WHERE boattype = ";
      		query = query  + "'" + boatChoice.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
       			jTextBoatRating.setText(rset.getString(2));
       		}
       		catch (SQLException f){
       			ErrorFrame err = new ErrorFrame(f.toString());
       		} 
    	}
  	}
}//  Boat

 