
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.sql.*;


public class InvoiceMenu extends JFrame implements ActionListener{

	
	Choice invoiceChoice;
	
	JTextField jTextCustomer;	
   	JTextField jTextInvoiceID;
   	JTextField jTextInvoiceDate;
   	JTextField jTextInvoiceStatus;
/*   	
   	JLabel customerLabel;
   	JLabel boatTypeLabel;
   	JLabel boatNumLabel;
*/   	
   	JButton addButton;
   	JButton updateButton;
   	JButton deleteButton;
   	JButton invoiceDetailButton;
   	
   	GridBagLayout gridBag;
   	GridBagConstraints gbc;
   	Container contentPane;
   	Connection conn;
   	String CustomerID;
    	
	
	public InvoiceMenu(String mCustid) {
		super();
//		JPanel panel = new JPanel();
        CustomerID = mCustid;
		contentPane = this.getContentPane();	
        gridBag = new GridBagLayout();
        gbc = new GridBagConstraints();
        contentPane.setLayout(gridBag);

 
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
	
		loadInvoices();
		
//		contentPane.add(panel);
		this.setSize(500,400);
		this.setTitle("Invoices");
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
		jTextCustomer.setText(CustomerID);
		jTextInvoiceID = new JTextField(8);
		jTextInvoiceDate = new JTextField(12);
		jTextInvoiceStatus = new JTextField(8);
        
        JLabel CustIDLabel = new JLabel("CustomerCode");
        JLabel InvoiceIDLabel = new JLabel("Invoice Number");
        JLabel InvoiceDateLabel = new JLabel("Date");
    	JLabel InvoiceStatusLabel = new JLabel(" Status");
    	
    	JLabel dummyLabel1 = new JLabel("           ");
        
        invoiceChoice = new Choice();
        
        addButton = new JButton("  Add ");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        invoiceDetailButton = new JButton("Invoice Detail");
        
        gbc.anchor = GridBagConstraints.WEST;

        
        putWidget(xbase,ybase + 1,invoiceChoice);
        InvoiceMenuListener controlInvoiceType = new InvoiceMenuListener();
        invoiceChoice.addItemListener(controlInvoiceType); 
        
        xbase++;
        gbc.anchor = GridBagConstraints.EAST;
        putWidget(xbase,ybase,CustIDLabel);
        putWidget(xbase,ybase + 1,InvoiceIDLabel);
        putWidget(xbase,ybase + 2,InvoiceDateLabel);
        putWidget(xbase,ybase + 3,InvoiceStatusLabel);
       
        gbc.anchor = GridBagConstraints.WEST;
        xbase++;
        putWidget(xbase,ybase,jTextCustomer);
        putWidget(xbase,ybase + 1,jTextInvoiceID);
        putWidget(xbase,ybase + 2,jTextInvoiceDate);
        putWidget(xbase,ybase + 3,jTextInvoiceStatus);
      
        gbc.anchor = GridBagConstraints.CENTER;
        putWidget(1,9,dummyLabel1);
        putWidget(0,10,addButton);
        addButton.addActionListener(this);
        putWidget(1,10,updateButton);
        updateButton.addActionListener(this);
        putWidget(2,10,deleteButton);
        deleteButton.addActionListener(this);
        putWidget(3,10,invoiceDetailButton);
        invoiceDetailButton.addActionListener(this);
	}
		
	private void putWidget(int x, int y, Component c){
		gbc.gridx = x;
        gbc.gridy = y;
        gridBag.setConstraints(c,gbc);
        contentPane.add(c);
	}
	

	
	private void loadInvoices () {
	   invoiceChoice.removeAll();
	   invoiceChoice.add(".................");
 	   try {
 	   		String query = "SELECT invoice_id FROM invoice_master WHERE cust_id = "+ "'" + CustomerID + "' ORDER BY invoice_date";
//			System.out.println(query);
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
               invoiceChoice.add(rset.getString(1));
       		}
       	
    	}
    	catch (SQLException e) {
      		ErrorFrame err = new ErrorFrame(e.toString());
    	}
    	clearScreen();
	}
    
    
    private void clearScreen() {
    	jTextCustomer.setText(CustomerID);
    	jTextInvoiceID.setText("");
    	jTextInvoiceDate.setText("");
    	jTextInvoiceStatus.setText("");
    }//clearScreen
    
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	Invoice i = new Invoice();
    	if (source == addButton){   		
    		i.addInvoice   (conn,
    		                 jTextCustomer.getText(),
    		                 jTextInvoiceID.getText(),
    		                 jTextInvoiceDate.getText(),
    		                 jTextInvoiceStatus.getText());
    		clearScreen();
    		
    		loadInvoices();   		
    	} else if (source == updateButton){ 
    		i.updateInvoice(conn,
    		                 jTextCustomer.getText(),
    		                 jTextInvoiceID.getText(),
    		                 jTextInvoiceDate.getText(),
    		                 jTextInvoiceStatus.getText());
    		clearScreen();          
    	} else if (source == deleteButton) {
            i.deleteInvoice(conn,jTextCustomer.getText(),jTextInvoiceID.getText());
            clearScreen();
        } else if (source == invoiceDetailButton) {
            InvoiceDetailMenu id = new InvoiceDetailMenu(jTextInvoiceID.getText(),
                                                         jTextCustomer.getText());
           
    	} 
	}

  	
  	class InvoiceMenuListener implements ItemListener {
      	public void itemStateChanged(ItemEvent e) {
      		Object source = e.getSource();
//      		jTextCustomer.setText((String)customerChoice.getSelectedItem());
      		
      		String query = "SELECT * FROM Invoice_master WHERE cust_id = ";
      		query = query  + "'" + CustomerID + "'";
      		query = query  + " AND invoice_id = '" + invoiceChoice.getSelectedItem() + "'";
//      		System.out.println(query);
      		try {
      			Statement stmt = conn.createStatement ();
        		ResultSet rset = stmt.executeQuery (query);
        		rset.next ();
       			jTextInvoiceID.setText(rset.getString(1));
       			jTextInvoiceDate.setText(rset.getString(3));
       			jTextInvoiceStatus.setText(rset.getString(4));
       		}
       		catch (SQLException f){
       			ErrorFrame err = new ErrorFrame(f.toString());
       		} 
    	}
  	}
}//  Invoicemenu

 