import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import javax.swing.table.*;


public class JTableDisplayResults extends JFrame {

  public JTableDisplayResults() {
  }
                       
  public void makeTable(ResultSet rs) {
        
    JTable table = new JTable();
     
    // get TableModel definition to which the resultset has to be displayed 
    DefaultTableModel aModel = new DefaultTableModel();
        
    try {
       
      //get the metadata and column count from the resultset
      ResultSetMetaData rsmd = rs.getMetaData();
      int colCount = rsmd.getColumnCount();
        
      //Allocate an array to hold the column names
      String[] tableColumnNames = new String[colCount];
        
      //get the column names from the resultset
      for (int i=0; i< colCount; i++)
        tableColumnNames[i] = rsmd.getColumnLabel(i+1);
        
      //set the column headers
      aModel.setColumnIdentifiers(tableColumnNames);        
        
      // Loop through the ResultSet and transfer in the Model
      while(rs.next()){
        
        //Allocate a new row
        Object[] objects = new Object[colCount];
        
        //Add the column values to the row
        for(int i=0;i<colCount;i++){
          objects[i]=rs.getObject(i+1);
        }
        
        //add the row to the table model
        aModel.addRow(objects);
      }

      //place the table model into the table for displaying
      table.setModel(aModel);
         
      }catch(SQLException sqle) {
         System.err.println("Error connecting: " + sqle);
      } 
        

      //  Create the scroll pane and add the table to it. 
      JScrollPane scrollPane = new JScrollPane(table);

      //Add the scroll pane to this window.
      getContentPane().add(scrollPane,BorderLayout.CENTER);

      this.setSize(700, 360);
      this.setTitle("Query Result");
      this.setVisible(true);
    
   }
  
}
