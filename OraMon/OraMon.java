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
 */

public class OraMon extends JFrame implements ActionListener{
	
 

  private JTextField hostField, dbNameField, queryField, portField, usernameField;
  private JTextField queryDescField,queryDateField,queryAuthorField;
  private JTextArea queryArea;
  private JScrollPane scrollPane;                 
  private JRadioButton oracleButton, sybaseButton,mysqlButton;
  private JPasswordField passwordField;
  private JButton showResultsButton;
  private Container contentPane;
  private JPanel tablePanel;
  private Choice queryChoice;
  private Connection conn;
  private Connection MySQLconn;
  
  
  
  public OraMon () {
    super("OraMon");
//    WindowUtilities.setNativeLookAndFeel();
    addWindowListener(new ExitListener());
    contentPane = getContentPane();
    contentPane.add(makeControlPanel(), BorderLayout.NORTH);
    pack();
    setVisible(true);
  }
  
   public static void main(String[] args) {
    OraMon o = new OraMon();
    int MSvendor = DriverUtilities.MYSQL;
    String MSdriver = DriverUtilities.getDriver(MSvendor);
    String MSurl = DriverUtilities.makeURL("localhost","oramon","3306",MSvendor);
    MySQLconn = DatabaseUtilities.getConnection(MSdriver,MSurl,"","");    
  }

  
  public void actionPerformed(ActionEvent event) {
    String host = hostField.getText();
    String dbName = dbNameField.getText();
    String username = usernameField.getText();
    String port = portField.getText();
    String password = String.valueOf(passwordField.getPassword());
    String query = queryArea.getText();

    int vendor = DriverUtilities.ORACLE;
 
    if (tablePanel != null) {
      contentPane.remove(tablePanel);
    }
    tablePanel = makeTablePanel(host, dbName, vendor, username, password, port, query);
    contentPane.add(tablePanel, BorderLayout.CENTER);
    pack();
  }

  // Executes a query and places the result in a
  // JTable that is, in turn, inside a JPanel.
  
  private JPanel makeTablePanel(String host,
                                String dbName,
                                int vendor,
                                String username,
                                String password,
                                String port,
                                String query) {
    String driver = DriverUtilities.getDriver(vendor);
    String url = DriverUtilities.makeURL(host, dbName, port, vendor);
    conn = DatabaseUtilities.getConnection(driver,url,username,password);    
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
    panel.add(makeUsernamePanel());
    panel.add(makeStoredQueryPanel());
    panel.add(makeQueryPanel());
    panel.add(makeButtonPanel());
    panel.setBorder(BorderFactory.createTitledBorder("Query Data"));
    loadQueryChoice();
    return(panel);
  }

  // The panel that has the host and db name textfield and
  // the driver radio buttons. Placed in control panel.
  
  private JPanel makeHostPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Host:"));
    hostField = new JTextField(15);
    panel.add(hostField);
    panel.add(new JLabel("    DB Name:"));
    dbNameField = new JTextField(15);
    panel.add(dbNameField);
    panel.add(new JLabel("    Port:"));
    portField = new JTextField("1521");
    panel.add(portField);
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
  
  private JPanel makeStoredQueryPanel() {
    JPanel panel = new JPanel();
    queryChoice = new Choice();
    queryDescField = new JTextField(); 
    queryDateField = new JTextField(10);
    queryAuthorField = new JTextField(20);   
    panel.add(new JLabel("Query:"));
    panel.add(queryChoice);
    panel.add(queryAuthorField);
    panel.add(queryDateField);   
    return(panel);
  }

  // The panel that has textfield for entering queries.
  // Placed in control panel.
  
  private JPanel makeQueryPanel() {
    JPanel panel = new JPanel();
    queryArea = new JTextArea();
    queryArea.setMinimumSize(new Dimension(300, 100));
    panel.add(new JLabel("Query:"));
    scrollPane = new JScrollPane(queryArea);
    scrollPane.setPreferredSize(new Dimension(300, 100));
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
  
  private JLabel makeErrorLabel() {
    JLabel label = new JLabel("No Results", JLabel.CENTER);
    label.setFont(new Font("Serif", Font.BOLD, 36));
    return(label);
  }
  
   
  private void clearScreen() {
    queryArea.setText("");	
  }//clearScreen

  
  private void loadQueryChoice() { 
    queryChoice.removeAll();
    queryChoice.add("...");
    try {
 	  String query = "SELECT query_name FROM query_master ORDER BY query_name";
	  Statement stmt = conn.createStatement ();
      ResultSet rset = stmt.executeQuery (query);
      while (rset.next ()) {
        queryChoice.add(rset.getString(1));
      }       	
    }
    catch (SQLException e) {
      ErrorFrame err = new ErrorFrame(e.toString());
    }
    clearScreen();
  }//loadQueryChoice

  
  class queryChoiceListener implements ItemListener {
   	public void itemStateChanged(ItemEvent e) {
    	Object source = e.getSource();
    	String makeQuery = "";
    	String query = "SELECT * FROM query_detail WHERE query_name = ORDER BY query_line";
    	query = query  + "'" + queryChoice.getSelectedItem() + "'";
    	try {
          Statement stmt = conn.createStatement ();
          ResultSet rset = stmt.executeQuery (query);
          while (rset.next ()) {
            makeQuery = makeQuery + (rset.getString(3));
       	  }
       	  queryArea.setText(makeQuery);
       	}
       	catch (SQLException f){
       	  ErrorFrame err = new ErrorFrame(f.toString());
       	} 
    }
  }

}
