import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import java.sql.*;

class DatabaseBrowse extends JFrame implements ActionListener, TreeSelectionListener
{
  public static void main(String[] args)
  {
     DatabaseBrowse theApp = new DatabaseBrowse();     // Create application object
  }

  public DatabaseBrowse()
  {
    super("Database Browser");
    
    setBounds(0, 0, 400, 300);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    addWindowListener(new WindowHandler());

    // Create labels for input fields
    JLabel dbURLLabel  = new JLabel( "Database URL: ");
    dbURLLabel.setDisplayedMnemonic('D');          // Set accelerator
    dbURLLabel.setLabelFor(database);              // Connect label to field
    database.setFocusAccelerator('D');             // Identify accelerator to field

    JLabel userIDLabel = new JLabel( "User ID:", JLabel.RIGHT);
    userIDLabel.setDisplayedMnemonic('U');         // Set accelerator
    userIDLabel.setLabelFor(userIDInput);          // Connect label to field
    userIDInput.setFocusAccelerator('U');          // Identify accelerator to field

    userIDLabel.setPreferredSize(dbURLLabel.getPreferredSize()); // Set same size
    JLabel passwordLabel = new JLabel("Password: ");
    passwordLabel.setDisplayedMnemonic('P');       // Set accelerator
    passwordLabel.setLabelFor(passwordInput);      // Connect label to field
    passwordInput.setFocusAccelerator('P');        // Identify accelerator to field


    // Box for database URL input
    Box dbPane = Box.createHorizontalBox();    
    dbPane.add(dbURLLabel);
    dbPane.add(database);

    // Box for user ID and password input fields
    Box loginPane = Box.createHorizontalBox();
    loginPane.add(userIDLabel);
    loginPane.add(userIDInput);
    loginPane.add(passwordLabel);
    loginPane.add(passwordInput);
    
    Box inputPane = Box.createVerticalBox();
    inputPane.add(dbPane);
    inputPane.add(loginPane);
    getContentPane().add(inputPane, BorderLayout.NORTH);

    // Add message area
    status.setText("Enter a database URL and/or press Enter");
    status.setEditable(false);                    // No user input
    status.setLineWrap(true);                     // Lines wrap
    status.setWrapStyleWord(true);                // on word boundaries
    status.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    getContentPane().add(status, BorderLayout.SOUTH);

    // Create tree to go in left split pane
    dbNode = new DefaultMutableTreeNode("No database");
    dbTreeModel = new DefaultTreeModel(dbNode);
    dbTree = new JTree(dbTreeModel);
    treePane = new JScrollPane(dbTree);
    treePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));


    // Create table to go in right split pane
    tableModel = new ResultsModel();
    JTable table = new JTable(tableModel);
    table.setPreferredSize(new Dimension(800,400));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
    tablePane = new JScrollPane(table);
    tablePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));
  

    JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          true,             // Continuous relayout
                                          treePane,         // Left pane content
                                          tablePane);       // Right pane content
    getContentPane().add(splitpane, BorderLayout.CENTER);
    splitpane.setDividerLocation(150);                 // Left pane 150 pixels wide
     
    // Add event listeners
    database.addActionListener(this);
    userIDInput.addActionListener(this);
    passwordInput.addActionListener(this);
    dbTree.addTreeSelectionListener(this);


    pack();
  
    setVisible(true);                            // Set window visible
    show();                                      // Display the window
    database.requestFocus();                     // Focus to the url input field
    
    for(int i = 0 ; i < drivers.length ; i++)
      try
      {
        Class.forName(drivers[i]);
      }
      catch(ClassNotFoundException cnfe)
      {
        System.err.println(cnfe);
        status.setText("Driver load failed: " + cnfe.getMessage());
      }

  }

  private String userID = "";
  private String password = "";
  private String url = "jdbc:mysql://localhost/inspections";

  Connection connection;
  Statement statement;


  private JTextField database = new JTextField(url);
  private JTextField userIDInput = new JTextField(userID);
  private JPasswordField passwordInput = new JPasswordField(password);
  private JTextArea status = new JTextArea(3,30);

  private DefaultMutableTreeNode dbNode;      // Root node for the database tree
  private DefaultTreeModel dbTreeModel;       // Model for the database metadata
  private JTree dbTree;                       // Tree to display the metadata
  private JScrollPane treePane;               // Scroll pane holding the tree

  ResultsModel tableModel;                    // Model for table
  private JTable table;                       // Table holding table data
  JScrollPane tablePane;                      // Scroll pane holding the table

  private String[] drivers = {
                               "sun.jdbc.odbc.JdbcOdbcDriver",       // ODBC bridge
                               "com.mysql.jdbc.Driver"   // mSQL driver
                             };


  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();            // Get source of the event
    if(source == database    ||               // If its URL input,
       source == userIDInput ||               // or userID input,
       source == passwordInput)               // or password input,
    {                                         // we will try for a connection
      url = database.getText();               // Get database URL
      userID = userIDInput.getText();               // Get user ID

      char[] pw = passwordInput.getPassword();      // Get password
      if(pw != null)
        password = new String(pw);

     if(url == null || url.length()==0)
      {
        status.setText("Please specify a database URL ");
        return;
      }
      openConnection();
      password = null;                             // For security
    }
  }

  public void openConnection()
  {
    try
    {
      if(connection != null)                 // If there is a connection
      {                                      // close it
        connection.close();

        // Reset the table data 
        tableModel.setResultSet(null);
        tablePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        // Reset the tree displaying metadata
        dbNode = new DefaultMutableTreeNode("No database");
        dbTreeModel.setRoot(dbNode);
        dbTree.setRootVisible(true);
        treePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        dbTreeModel.reload();
      }

      // Now open the new connection
      connection = DriverManager.getConnection(url, userID, password);
      status.setText("Database connection established");
      statement = connection.createStatement();       // Create statement for query

      dbNode = new DefaultMutableTreeNode(url);       // Root node is URL
      dbTreeModel.setRoot(dbNode);                    // Set root in model
      setupTree(connection.getMetaData());            // Set up tree with metadata

      treePane.setBorder(BorderFactory.createTitledBorder(
                         BorderFactory.createLineBorder(Color.darkGray),
                         url,
                         TitledBorder.CENTER,
                         TitledBorder.DEFAULT_POSITION));
      dbTree.setRootVisible(false);                   // Now show the root node
      dbTreeModel.reload();                           // Get the tree redisplayed

    }
    catch(SQLException sqle)
    {
      status.setText(sqle.getMessage());              // Display first message
      do                                              // loop through exceptions
      {
        System.err.println("Exception occurred:\nMessage: " + sqle.getMessage());
        System.err.println("SQL state: " + sqle.getSQLState());
        System.err.println("Vendor code: " + sqle.getErrorCode() +
                           "\n----------------");
      }
      while((sqle = sqle.getNextException()) != null);
    }
  }

  private void setupTree(DatabaseMetaData metadata) throws SQLException
  {
    String[] tableTypes = { "TABLE"};                   // We want only tables
    ResultSet tables = metadata.getTables(              // Get the tables info
                                          null,
                                          null,
                                          null,
                                          tableTypes);

      String tableName;                           // Stores a table name
      DefaultMutableTreeNode tableNode;           // Stores a tree node for a table
      while(tables.next())                              // For each table
      {
        tableName = tables.getString("TABLE_NAME");     // get the table name
        tableNode = new DefaultMutableTreeNode(tableName);
        dbNode.add(tableNode);                          // Add the node to the tree

        // Get all the columns for the current table
        ResultSet columnNames = metadata.getColumns(null, null, tableName, null);

        // Add nodes for the columns as children of the table node
        while(columnNames.next())
          tableNode.add(
                 new DefaultMutableTreeNode(columnNames.getString("COLUMN_NAME")));
      }
  }

  public void valueChanged(TreeSelectionEvent e)
  {
    TreePath[] paths = dbTree.getSelectionPaths();
    if(paths == null)
      return;

    boolean tableSelected = false;             // Set true if a table is selected
    String column;                             // Stores a column name from a path
    String table;                              // Stores a table name from a path
    String columnsParam = null;                // Column names in SQL SELECT
    String tableParam = null;                  // Table name in SQL SELECT
    String message = null;                     // Message for status area
    for(int  j = 0; j < paths.length ; j++)
    {
      switch(paths[j].getPathCount())
      {
        case 2:                                // We have a table selected
          tableParam = (String)
                          (((DefaultMutableTreeNode)
                                  (paths[j].getPathComponent(1))).getUserObject());
          columnsParam = "*";               // Select all columns
          tableSelected = true;             // Set flag for a table selected
          message = "Complete " + tableParam + " table displayed";
          break;

        case 3:                               // Column selected
          table = (String)
                      (((DefaultMutableTreeNode)
                                  (paths[j].getPathComponent(1))).getUserObject());
          if(tableParam == null)
            tableParam = table;

          else if(tableParam != table)
            break;
          column = (String)
                       (((DefaultMutableTreeNode)
                                  (paths[j].getPathComponent(2))).getUserObject());
          if(columnsParam == null)           // If no previous columns
            columnsParam = column;           // add the column
          else                               // otherwise
            columnsParam += "," + column;    // we need a comma too
          message = columnsParam + " displayed from " + tableParam + " table.";
          break;
      }
      if(tableSelected)                      // If a table was selected
        break;                               // we are done
    }
    try
    {
      // Display the columns and change the scroll pane border
      tableModel.setResultSet(statement.executeQuery("SELECT " + columnsParam+" FROM " + tableParam));
      tablePane.setBorder(BorderFactory.createTitledBorder(
                         BorderFactory.createLineBorder(Color.darkGray),
                         tableParam,
                         TitledBorder.CENTER,
                         TitledBorder.DEFAULT_POSITION));
    }
    catch(SQLException sqle)
    {
      message = "Selection event Error\n" + sqle.getMessage();
      System.err.println(message);
    }
    if(message != null)
    status.setText(message);
  }

  
  
  // Inner class defining handler for window events
  class WindowHandler extends WindowAdapter
  {
    // Handler for window closing event
    public void windowClosing(WindowEvent e)
    {
      dispose();                                 // Release the window resources
      System.exit(0);                            // End the application
    }
  }
}
