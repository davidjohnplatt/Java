

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.sql.*;


public class CustomerMenu extends JFrame implements ActionListener{
	
	Choice customerChoice;
	JTextField jTextCustomer;
	JTextField jTextCustomerName;
   	JTextField jTextAddress1;
   	JTextField jTextAddress2;
   	JTextField jTextCity;
   	JTextField jTextProvince;
   	JTextField jTextCountry;
   	JTextField jTextPostalCode;
   	JTextField jTextBoatNum;
   	
   	JLabel customerLabel;
   	JLabel boatTypeLabel;
   	JLabel boatNumLabel;
   	
   	JButton addButton;
   	JButton updateButton;
   	JButton deleteButton;
   	JButton invoiceButton;
   	
   	GridBagLayout gridBag;
   	GridBagConstraints gbc;
   	Container contentPane;
   	Connection conn;
    	
	
	public CustomerMenu() {
		super();
//		JPanel panel = new JPanel();
		contentPane = this.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);
        customerChoice = new Choice();
 
		try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		conn = DriverManager.getConnection ("jdbc:mysql://localhost/invoices");
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
		loadCustomers();
//		contentPane.add(panel);
		this.setSize(700,500);
		this.setTitle("Customer");
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
		
		jTextCustomer = new JTextField(12);
		jTextCustomerName = new JTextField(30);
		jTextAddress1 = new JTextField(40);
		jTextAddress2 = new JTextField(40);
		jTextCity = new JTextField(20);
		jTextProvince = new JTextField(15);
		jTextCountry = new JTextField(20);
		jTextPostalCode = new JTextField(7);
        
        JLabel CustIDLabel = new JLabel("CustomerCode");
        JLabel CustNameLabel = new JLabel("Customer");
        JLabel Address1Label = new JLabel("Address 1");
    	JLabel Address2Label = new JLabel("Address 2");
    	JLabel CityLabel = new JLabel("City");
    	JLabel ProvinceLabel = new JLabel("Province");
    	JLabel CountryLabel = new JLabel("Country");
    	JLabel PostCodeLabel = new JLabel("Postal Code");
    	
    	JLabel dummyLabel1 = new JLabel("           ");
        customerChoice = new Choice();
        
        addButton = new JButton("  Add ");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        invoiceButton = new JButton("Invoices");
        
        gbc.anchor = GridBagConstraints.WEST;
        putWidget(xbase,ybase,customerChoice);
        CustomerMenuListener controlBoatType = new CustomerMenuListener();
        customerChoice.addItemListener(controlBoatType); 
        
        xbase++;
        gbc.anchor = GridBagConstraints.EAST;
        putWidget(xbase,ybase,CustIDLabel);
        putWidget(xbase,ybase + 1,CustNameLabel);
        putWidget(xbase,ybase + 2,Address1Label);
        putWidget(xbase,ybase + 3,Address2Label);
        putWidget(xbase,ybase + 4,CityLabel);
        putWidget(xbase,ybase + 5,ProvinceLabel);
        putWidget(xbase,ybase + 6,CountryLabel);
        putWidget(xbase,ybase + 7,PostCodeLabel);
         
        gbc.anchor = GridBagConstraints.WEST;
        xbase++;
        putWidget(xbase,ybase,jTextCustomer);
        putWidget(xbase,ybase + 1,jTextCustomerName);
        putWidget(xbase,ybase + 2,jTextAddress1);
        putWidget(xbase,ybase + 3,jTextAddress2);
        putWidget(xbase,ybase + 4,jTextCity);
        putWidget(xbase,ybase + 5,jTextProvince);
        putWidget(xbase,ybase + 6,jTextCountry);
        putWidget(xbase,ybase + 7,jTextPostalCode);

        gbc.anchor = GridBagConstraints.CENTER;
        putWidget(1,9,dummyLabel1);
        putWidget(0,10,addButton);
        addButton.addActionListener(this);
        putWidget(1,10,updateButton);
        updateButton.addActionListener(this);
        putWidget(2,10,deleteButton);
        deleteButton.addActionListener(this);
        putWidget(3,10,invoiceButton);
        invoiceButton.addActionListener(this);
	}
		
	private void putWidget(int x, int y, Component c){
		gbc.gridx = x;
        gbc.gridy = y;
        gridBag.setConstraints(c,gbc);
        contentPane.add(c);
	}
	
	private void loadCustomers() { 
	   customerChoice.removeAll();
	   customerChoice.add("...");
 	   try {
 	   		String query = "SELECT custid FROM customers ORDER BY custid";
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               customerChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
    	clearScreen();
	}  //loadCustomers
    
    
    private void clearScreen() {
    	jTextCustomer.setText("");
    	jTextCustomerName.setText("");
    	jTextAddress1.setText("");
    	jTextAddress2.setText("");
    	jTextCity.setText("");
    	jTextProvince.setText("");
    	jTextCountry.setText("");
    	jTextPostalCode.setText("");
    }//clearScreen
    
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	Customer c = new Customer();
    	if (source == addButton){   		
    		c.addCustomer   (conn,
    		                 jTextCustomer.getText(),
    		                 jTextCustomerName.getText(),
    		                 jTextAddress1.getText(),
    		                 jTextAddress2.getText(),
    		                 jTextCity.getText(),
    		                 jTextProvince.getText(),
    		                 jTextCountry.getText(),
    		                 jTextPostalCode.getText());
    		clearScreen();
    		loadCustomers();   		
    	} else if (source == updateButton){ 
    		c.updateCustomer(conn,
    		                 jTextCustomer.getText(),
    		                 jTextCustomerName.getText(),
    		                 jTextAddress1.getText(),
    		                 jTextAddress2.getText(),
    		                 jTextCity.getText(),
    		                 jTextProvince.getText(),
    		                 jTextCountry.getText(),
    		                 jTextPostalCode.getText()); 
    		clearScreen();          
    	} else if (source == deleteButton) {
            c.deleteCustomer(conn,jTextCustomer.getText());
            clearScreen();
            loadCustomers();
    	} else if (source == invoiceButton) {
            InvoiceMenu i = new InvoiceMenu(jTextCustomer.getText());
    	} 
    	
	}
	
	public static void main (String [] args) {
		CustomerMenu s = new CustomerMenu();
	}
	
	class CustomerMenuListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
      		jTextCustomer.setText((String)customerChoice.getSelectedItem());
      		
      		String query = "SELECT * FROM customers WHERE custid = ";
      		query = query  + "'" + customerChoice.getSelectedItem() + "'";
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
       			jTextCustomer.setText(rset.getString(1));
       			jTextCustomerName.setText(rset.getString(2));
       			jTextAddress1.setText(rset.getString(3));
       			jTextAddress2.setText(rset.getString(4));
       			jTextCity.setText(rset.getString(5));
       			jTextProvince.setText(rset.getString(6));
       			jTextCountry.setText(rset.getString(7));
       			jTextPostalCode.setText(rset.getString(8));
       		}
       		catch (SQLException f){
       			ErrorFrame err = new ErrorFrame(f.toString());
       		} 
    	}
  	}
}//  CustomerMenu

 