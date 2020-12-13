package sqlmon;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;



public class showResults extends JFrame {
 
  JFrame frame = new JFrame("Results");
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  BorderLayout layout = new BorderLayout();
  String [] columnNames;
  String [] columnValues;
  Object[][] data;
  int columns;
  HashTable hashResult = new HashTable();

  public showResults () {
  }

  public showResults (ResultSet results,ResultSetMetaData meta,int rowCount) {
    super();
    this.getContentPane().setLayout(layout);
    this.setSize(new Dimension(600, 500));
    
    try {
      int rows = 20;
      int rowsReturned = 0;

      columns = meta.getColumnCount();
//      System.out.println("testpoint - 1");

      columnNames  = new String[columns];
      columnValues = new String[columns];
      data = new Object [rows][columns];
          
      for(int i=1; i<=columns; i++) {
        columnNames [i-1] =  meta.getColumnLabel(i);
      }    

//      for (int i=0;i<columns;i++) {
//        System.out.println(i + " " + columnNames [i]);
//      } 

//      System.out.println("testpoint - 2");
      String ns = "";

      while (results.next()) {
        for(int j=1; j<=columns; j++) {
//        data [rowCount][j-1] = results.getObject(j); 
          columnValues [j-1] = results.getString(j);           
        }
        Integer n = new Integer(rowCount);
        hashResult.put(n,columnValues);
        System.out.println(n + "  " + columnValues [0]);
        rowCount++;
      }
      Integer o = new Integer(0);
      System.out.println("Hashtable Size " + hashResult.size());
      String b [] = new String [columns];
      b = (String [])hashResult.get(o);
      System.out.println("b = " + b [0]);

      for (int i=0; i<rowCount; i++) {
        Integer m = new Integer(i);
        System.out.println("m = " + m);
        String a [] = new String [columns];
        a = (String [])hashResult.get(m);
        System.out.println(a [0]);
        for(int j=0; j<columns; j++) {
//        System.out.println("i = " + i +" j = " + j );
          data [i][j] = a [j];
        }
      }
        
 
    }
    catch( SQLException e2 ) {
      System.out.println("Error : " + e2.getMessage());
    } //catch
//  System.out.println("testpoint - 4");
    final JTable table = new JTable(data,columnNames);
//    System.out.println("testpoint - 5");
    table.setPreferredScrollableViewportSize(new Dimension(600,300));
    JScrollPane scrollPane = new JScrollPane(table);
    this.getContentPane().add(scrollPane, BorderLayout.CENTER);
  } //constructor
  


  void fileExit_ActionPerformed(ActionEvent e) {
    System.exit(0);
  }
/*
  public void showPane(ResultSet results,ResultSetMetaData meta) throws SQLException {
    int cols = meta.getColumnCount();
 //   showResults frame = new showResults();
      
     for(int i=1; i<=4; i++) {
      columnNames [i] =  meta.getColumnLabel(i);
  } 

    frame.pack();
    frame.setVisible(true);
    
  } //showPane
*/
 public static void main(String[] args) {
 //       showResults frame = new showResults();
 //       frame.pack();
   //     frame.setVisible(true);
    }
} //showResults