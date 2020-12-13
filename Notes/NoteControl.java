import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;



public class NoteControl extends JFrame implements ActionListener{
	
 
  private JMenuBar menuBar = new JMenuBar();
  private JMenu fileMenu, oracleMenu;
  private JMenuItem exitMenuItem,tablespaceMenuItem;
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
  String username;
  String password;
  String query;
  int vendor;
 
  
  public NoteControl () {
    super("Database Query Viewer");
    WindowUtilities.setNativeLookAndFeel();
    
    setJMenuBar(menuBar);
	fileMenu = new JMenu("File");
	menuBar.add(fileMenu);
	oracleMenu = new JMenu("Oracle");
	menuBar.add(oracleMenu);
	tablespaceMenuItem = new JMenuItem("Tablespaces");
	oracleMenu.add(tablespaceMenuItem);
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
   
  }
  
  public void actionPerformed(ActionEvent event) {
  	Object source = event.getSource();
    if (source == exitMenuItem){
      System.exit(0);
    } 
    else  {    	
      readScreen(); 
      String driver = DriverUtilities.getDriver(vendor);
      String url = DriverUtilities.makeURL(host, dbName, vendor);              
      Connection conn = DatabaseUtilities.getConnection(driver,url,username,password);    
      boolean goodQuery = DatabaseUtilities.jTableQueryResults(conn,query,true);
      if (tablePanel != null) {
        contentPane.remove(tablePanel);
      }
      tablePanel = makeTablePanel(host,dbName, vendor,username,password,query);
      contentPane.add(tablePanel, BorderLayout.CENTER);
      pack(); 
    }    
  }

  // Executes a query and places the result in a
  // JTable that is, in turn, inside a JPanel.
  // which is in a separate frame.
  
  private JPanel makeTablePanel(String host,String dbName,int vendor,String username,String password,String query) {
    String driver = DriverUtilities.getDriver(vendor);
    String url = DriverUtilities.makeURL(host, dbName, vendor);              
    Connection conn = DatabaseUtilities.getConnection(driver,url,username,password);    
    boolean goodQuery = DatabaseUtilities.jTableQueryResults(conn,query,true);
    
    JPanel panel = new JPanel(new BorderLayout());
    if (goodQuery) {
      boolean DoNothing;
      }
    else {
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
    portField = new JTextField(4);
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
    sybaseButton = new JRadioButton("Sybase");
    vendorGroup.add(sybaseButton);
    panel.add(sybaseButton);
    mysqlButton = new JRadioButton("MySQL");
    vendorGroup.add(mysqlButton);
    panel.add(mysqlButton);
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
  	String targetQuery;
  	String tbsQuery = "SELECT * FROM dba_tablespaces";
  	if (qname == "OTBSQUERY") {
  		return tbsQuery;
  	} else {
  		return "";
  	}
  	
  	
  }
  
  private void readScreen(){
    host = hostField.getText();
    dbName = dbNameField.getText();
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
