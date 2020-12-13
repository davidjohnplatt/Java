import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/** An interactive database query viewer. Connects to
 *  the specified Oracle or Sybase database, executes a query,
 *  and presents the results in a JTable.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 *
 *  Modified by David Platt 2004.  D.J. Platt Consulting Inc.
 *  Dropped a few classes that weren't being used and added a couple more.
 *  Added separate result frame capability, port selection, queryText area 
 *  and ErrorFrame.  
 */

public class QueryViewer extends JFrame implements ActionListener{
	
 
  private JMenuBar menuBar = new JMenuBar();
  private JMenu fileMenu, oracleMenu;
  private JMenuItem exitMenuItem,tablespaceMenuItem,datafileMenuItem;
  private JMenuItem rollback1MenuItem;
  private JTextField hostField, dbNameField, portField, queryField, usernameField;
  private JRadioButton oracleButton, sybaseButton, mysqlButton;
  private JTextArea queryArea;
  private JScrollPane scrollPane;  
  private JPasswordField passwordField;
  private JButton showResultsButton;
  private Container contentPane;
  private JPanel tablePanel;
  String host;
  String dbName;
  String portNumber;
  String username;
  String password;
  String query;
  int vendor;
 
  
  public QueryViewer () {
    super("Database Query Viewer");
    WindowUtilities.setNativeLookAndFeel();
    
    setJMenuBar(menuBar);
	fileMenu = new JMenu("File");
	menuBar.add(fileMenu);
	oracleMenu = new JMenu("Oracle");
	menuBar.add(oracleMenu);
	
	tablespaceMenuItem = new JMenuItem("Tablespaces");
	oracleMenu.add(tablespaceMenuItem);
	tablespaceMenuItem.addActionListener(this);
	
	datafileMenuItem = new JMenuItem("Data Files");
	oracleMenu.add(datafileMenuItem);
	datafileMenuItem.addActionListener(this);
	
	rollback1MenuItem = new JMenuItem("Rollbacks");
	oracleMenu.add(rollback1MenuItem);
	rollback1MenuItem.addActionListener(this);
	
	exitMenuItem = new JMenuItem("Exit");
	fileMenu.add(exitMenuItem);
	exitMenuItem.addActionListener(this);
		
    addWindowListener(new ExitListener());
    contentPane = getContentPane();
    contentPane.add(makeControlPanel(), BorderLayout.NORTH);
    pack();
    setVisible(true);
  }

  /** When the "Show Results" button is pressed or
   *  RETURN is hit while the query textfield has the
   *  keyboard focus, a database lookup is performed,
   *  the results are placed in a JTable, and the window
   *  is resized to accommodate the table.
   *
   *  D.J. Platt - new behaviour is to spawn a separate jTable
   *  to hold results
   */
   
  public static void main(String[] args) {
    new QueryViewer();
  }
  
  public void actionPerformed(ActionEvent event) {
  	Object source = event.getSource();
    if (source == exitMenuItem){
      System.exit(0);
    } 
    else if (source == oracleButton){   
      portField.setText("1521"); 
    }
    else if  (source == sybaseButton){   
      portField.setText("1521"); 
    }
    else if  (source == mysqlButton){   
      portField.setText("3306");
    }
    else if  (source == tablespaceMenuItem){   
      queryArea.setText(doCannedQuery("OTBSQUERY"));
    }
    else if  (source == datafileMenuItem){   
      queryArea.setText(doCannedQuery("FSQUERY"));
    }
    else if  (source == rollback1MenuItem){   
      queryArea.setText(doCannedQuery("RB1QUERY"));
    }
    else if (source == showResultsButton){	
      readScreen(); 
      String driver = DriverUtilities.getDriver(vendor);
      String url = DriverUtilities.makeURL(host, dbName, portNumber, vendor);              
      Connection conn = DatabaseUtilities.getConnection(driver,url,username,password);    
      tablePanel = makeTablePanel(host,dbName, vendor,username,password,query);
      if (tablePanel != null) {
        contentPane.remove(tablePanel);
      }      
      contentPane.add(tablePanel, BorderLayout.CENTER);
      pack(); 
    }    
  }

  // Executes a query and places the result in a
  // JTable that is, in turn, inside a JPanel.
  // which is in a separate frame.
  
  private JPanel makeTablePanel(String host,String dbName,int vendor,String username,String password,String query) {
    String driver = DriverUtilities.getDriver(vendor);
    String url = DriverUtilities.makeURL(host, dbName, portNumber, vendor);              
    Connection conn = DatabaseUtilities.getConnection(driver,url,username,password);    
    boolean goodQuery = DatabaseUtilities.jTableQueryResults(conn,query,true);
    
    JPanel panel = new JPanel(new BorderLayout());
    if (goodQuery) {
      boolean DoNothing;
    } else {
      panel.add(makeErrorLabel());
      return(panel);
    }
    
    return(panel);   
  }

  // The panel that contains the textfields, check boxes,
  // and button.
  
  private JPanel makeControlPanel() {
    JPanel panel = new JPanel(new GridLayout(0, 1));
    panel.add(makeHostPanel());
    panel.add(makeRadioButtonPanel());
    panel.add(makeUsernamePanel());
    panel.add(makeQueryPanel());
    panel.add(makeButtonPanel());
    panel.setBorder
      (BorderFactory.createTitledBorder("Query Data"));
    return(panel);
  }

  // The panel that has the host and db name textfield and
  // the driver radio buttons. Placed in control panel.
  //
  // replace query JTextField with JTextArea  moved radio 
  // button to separate panel
  
  private JPanel makeHostPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Host:"));
    hostField = new JTextField(15);
    panel.add(hostField);
    panel.add(new JLabel("  DB Name:"));
    dbNameField = new JTextField(15);
    panel.add(dbNameField);
    panel.add(new JLabel("  Port:"));
    portField = new JTextField("1521");
    panel.add(portField);
    return(panel);
  }
  
  // The panel that has the driver radio buttons. 
  // Placed in control panel.
  //

  private JPanel makeRadioButtonPanel() {
 	JPanel panel = new JPanel();
    panel.add(new JLabel("  Driver:"));
    ButtonGroup vendorGroup = new ButtonGroup();
    oracleButton = new JRadioButton("Oracle", true);
    vendorGroup.add(oracleButton);
    panel.add(oracleButton);
    oracleButton.addActionListener(this);
    sybaseButton = new JRadioButton("Sybase");
    vendorGroup.add(sybaseButton);
    panel.add(sybaseButton);
    sybaseButton.addActionListener(this);
    mysqlButton = new JRadioButton("MySQL");
    vendorGroup.add(mysqlButton);
    panel.add(mysqlButton);
    mysqlButton.addActionListener(this);
    return(panel);
  }
  // The panel that has the username and password textfields.
  // Placed in control panel.
  
  private JPanel makeUsernamePanel() {
    JPanel panel = new JPanel();
    usernameField = new JTextField(10);
    passwordField = new JPasswordField(10);
    panel.add(new JLabel("Username: "));
    panel.add(usernameField);
    panel.add(new JLabel("    Password:"));
    panel.add(passwordField);
    return(panel);
  }
  

  // The panel that has textfield for entering queries.
  // Placed in control panel.
  
  private JPanel makeQueryPanel() {
    JPanel panel = new JPanel();
    queryArea = new JTextArea();
    queryArea.setMinimumSize(new Dimension(500, 100));
    panel.add(new JLabel("Query:"));
    scrollPane = new JScrollPane(queryArea);
    scrollPane.setPreferredSize(new Dimension(500, 100));
    panel.add(scrollPane);
    return(panel);
  }

  // The panel that has the "Show Results" button.
  // Placed in control panel.
  
  private JPanel makeButtonPanel() {
    JPanel panel = new JPanel();
    showResultsButton = new JButton("Show Results");
    showResultsButton.addActionListener(this);
    panel.add(showResultsButton);
    return(panel);
  }

  // Shows warning when bad query sent.
  //
  // This still happens but in addition a more instructive error
  // frame pops up.
  
  private JLabel makeErrorLabel() {
    JLabel label = new JLabel("No Results", JLabel.CENTER);
    label.setFont(new Font("Serif", Font.BOLD, 36));
    return(label);
  }
  
  private String doCannedQuery(String qname) {
//  	String targetQuery;
  	String tbsQuery = "SELECT * FROM dba_tablespaces";
  	String fsQuery = "SELECT * FROM dba_data_files";
  	String rb1Query = "SELECT * FROM dba_rollback_segs";
  	if (qname == "OTBSQUERY") {
  	  return tbsQuery;
  	}
    else if (qname == "FSQUERY") {
      return fsQuery;   
  	} 
  	else if (qname == "RB1QUERY") {
      return rb1Query;   
  	} 
  	else {
  		return "";
  	}
  	
  	
  }
  
  private void readScreen(){
    host = hostField.getText();
    dbName = dbNameField.getText();
    portNumber = portField.getText();
    username = usernameField.getText();
    password = String.valueOf(passwordField.getPassword());
    query = queryArea.getText();
    
    if (oracleButton.isSelected()) {
      vendor = DriverUtilities.ORACLE;
    } 
    else if (sybaseButton.isSelected()){
      vendor = DriverUtilities.SYBASE;
    } 
    else  {
      vendor = DriverUtilities.MYSQL;
    }
      
  }
  
  
}
