
// Copyright (c) 2001 Solect Technologies
package sqlmon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import oracle.jdeveloper.layout.*;
//import oracle.dacf.control.swing.*;

/**
 * A Swing-based top level window class.
 * <P>
 * @author Solect
 */
public class monFrame extends JFrame {
  static Connection connection = null;
  DatabaseMetaData meta;
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  JLabel statusBar = new JLabel();
  XYLayout xYLayout1 = new XYLayout();
  JTextArea jTextArea2 = new JTextArea();
  JScrollPane scrollPane = new JScrollPane(jTextArea2);
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  Statement statement = null;

  /**
   * Constructs a new instance.
   */
  public monFrame() {
    super();
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Initializes the state of this instance.
   */
  private void jbInit() throws Exception {
    this.getContentPane().setLayout(xYLayout1);
    this.setSize(new Dimension(600, 500));

    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.setActionCommand("exit");
    menuFileExit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        fileExit_ActionPerformed(e);
     }
    });

    statusBar.setText("");
    xYLayout1.setHeight(414);
//    jTextArea2.setLineWrap(true);
//    jTextArea2.setWrapStyleWord(true);
    jTextArea2.setPreferredSize(new Dimension(800, 800));
    jTextArea2.setEditable(false);
    jTextArea2.setRequestFocusEnabled(false);
    scrollPane.setPreferredSize(new Dimension(200, 200));

    jTextArea1.setMinimumSize(new Dimension(300, 150));

    ButtonHandler bh = new ButtonHandler();
    jButton1.setText("Go");
    jButton1.setActionCommand("go");
    jButton1.addActionListener(bh);
  

    jButton2.setText("Version");   
    jButton2.setActionCommand("version");
    jButton2.addActionListener(bh);

    xYLayout1.setWidth(546);
    menuFile.add(menuFileExit);
    menuBar1.add(menuFile);
    this.setJMenuBar(menuBar1);
    this.setTitle("SQLMonitor");
    this.getContentPane().add(statusBar, new XYConstraints(10, 10, -1, -1));
    this.getContentPane().add(jTextArea1, new XYConstraints(100, 45, 303, 105));
    jTextArea1.setText("select * from iaf_packages");//testing only
    this.getContentPane().add(scrollPane, new XYConstraints(99, 173, 500, 200));
    
    this.getContentPane().add(jButton1, new XYConstraints(438, 47, -1, -1));
    this.getContentPane().add(jButton2, new XYConstraints(439, 94, -1, -1));

    String connectStuff = "jdbc:oracle:thin:@10.17.128.100:1521:iaf";
    String userid = "iaf";
    String password = "iaf";
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    connection = DriverManager.getConnection (connectStuff,userid, password);
  }

  /**
   * Performs the action defined for "File|Exit".
   * @param e
   */
  void fileExit_ActionPerformed(ActionEvent e) {
    System.exit(0);
  }

  public void executeStatement () throws SQLException {
    String sql = jTextArea1.getText(); 
    int rowCount = 0;
//    Statement statement = null;

    try {
      Statement statement = connection.createStatement();
      putText2(sql);
      if( statement.execute(sql) ) { // true means the SQL was a SELECT
        processResults(statement.getResultSet());
      }
      else { // no result sets, see how many rows were affected
        int num = 0;
                
        switch(rowCount) {
        case 0:
          putText2("\nNo rows affected.");        
          break;
                    
        case 1:
          putText2("\n" + num + " row affected.");
          break;
                    
        default:
          putText2("\n" +num + " rows affected.");
          statusBar.setText(num + " rows affected.");
        } //switch
      } //else
    } //try
    catch( SQLException e ) {
      throw e;
    }
    finally { // close out the statement
 
     if( statement != null ) {
        try { 
          statement.close(); 
        }
        catch( SQLException e ) { 
        } //catch
      } //if  
  
    } //finally
  } //executeStatement

   public void processResults(ResultSet results) throws SQLException {
     try {
       ResultSetMetaData meta = results.getMetaData();
       StringBuffer bar = new StringBuffer();
       StringBuffer buffer = new StringBuffer();
       int cols = meta.getColumnCount();
       int row_count = 0;
       int i, width = 0;
       showResults r1 = new showResults(results,meta,row_count); 
       r1.pack();
       r1.setVisible(true);
 //      r1.showPane(results,meta);    
       
       for(i=1; i<=cols; i++) {
         width += meta.getColumnDisplaySize(i);
       }
     
       width += 1 + cols;
       for(i=0; i<width; i++) {
         bar.append(' ');
       }

       bar.append('\n');
       buffer.append(bar.toString() + " ");
// After the first bar goes the column labels
       for(i=1; i<=cols; i++) {
         StringBuffer filler = new StringBuffer();
         String label = meta.getColumnLabel(i);
         int size = meta.getColumnDisplaySize(i);
         int x;
                
// If the label is longer than the column is wide,
// then we truncate the column label
         if( label.length() > size ) {
           label = label.substring(0, size);
         }
// If the label is shorter than the column, pad it with spaces
         if( label.length() < size ) {
           int j;
                    
           x = (size-label.length())/2;
           for(j=0; j<x; j++) {
             filler.append(' ');
           }
           label = filler + label + filler;
           if( label.length() > size ) {
                        label = label.substring(0, size);
                    }
                    else {
                        while( label.length() < size ) {
                            label += " ";
                        }
                    }
                }
                // Add the column header to the buffer
                buffer.append(label + " ");
            }
            // Add the lower bar
            buffer.append("\n" + bar.toString());
            // Format each row in the result set and add it on
/*
            while( results.next() ) {
                row_count++;
                
                buffer.append(' ');
                // Format each column of the row
                for(i=1; i<=cols; i++) {
                    StringBuffer filler = new StringBuffer();
                    Object value = results.getObject(i);
                    int size = meta.getColumnDisplaySize(i);
                    String str;

                    if( results.wasNull() ) {
                        str = "NULL";
                    }
                    else {
                        str = value.toString();
                    }
                    if( str.length() > size ) {
                        str = str.substring(0, size);
                    }
                    if( str.length() < size ) {
                        int j, x;
                        
                        x = (size-str.length())/2;
                        for(j=0; j<x; j++) {
                            filler.append(' ');
                        }
                        str = filler + str + filler;
                        if( str.length() > size ) {
                            str = str.substring(0, size);
                        }
                        else {
                            while( str.length() < size ) {
                                str += " ";
                            }
                        }
                    }
                    buffer.append(str + "|");
                }
                buffer.append("\n");
            }
*/
            // Stick a row count up at the top
            if( row_count == 0 ) {
                buffer = new StringBuffer("\nNo rows selected.\n");
            }
            else if( row_count == 1 ) {
                buffer = new StringBuffer("\n1 row selected.\n" +
                                          buffer.toString() + bar.toString());
            }
            else {
                buffer = new StringBuffer("\n" + row_count + " rows selected.\n" +
                                          buffer.toString() + bar.toString());
            }
            putText2(buffer.toString());
            
        }
        catch( SQLException e ) {
            throw e;
        }
        finally {
            try { results.close(); }
            catch( SQLException e ) { }
        }
    }
  void putText2( String buff ) {
    jTextArea2.append(buff);
  }

  public class ButtonHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object source;
      source = e.getSource();

      if (source == jButton1) {
        jTextArea2.append("Executing\n");
        try {
          executeStatement();
        } //try
        catch( SQLException e2 ) {
          jTextArea2.append("\nError : " + e2.getMessage() + "\n");
        }
      } //if
      else if (source == jButton2) {
        try {
          meta = connection.getMetaData();
          putText2("TerminalMonitor v2.0\n");
          putText2("DBMS: " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion() + "\n");
          putText2("JDBC Driver: " + meta.getDriverName() + " " + meta.getDriverVersion() + "\n");
        } //try
        catch( SQLException e2 ) {
          jTextArea2.append("Error : " + e2.getMessage());
        } //catch
      } //else if
      else {
        jTextArea2.setText("Unknown button");
      } //else
    } //actionPerformed
  } //ButtonHandler
}


