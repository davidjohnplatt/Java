import java.sql.*;
import java.io.*;
import java.util.*;

public class free {

  public free() {
  } //constructor
  
 
  public static void main (String args []) throws SQLException {

    String select = "";

    select = select + "SELECT a.tablespace_name name,";
    select = select + " sum(b.bytes)/count( DISTINCT a.file_id||'.'||a.block_id ) bytes,";
    select = select + " sum(b.bytes)/count( DISTINCT a.file_id||'.'||a.block_id ) - ";
    select = select + " sum(a.bytes)/count( DISTINCT b.file_id ) used,";
    select = select + " sum(a.bytes)/count( DISTINCT b.file_id ) free,";
    select = select + " round(100 * ( (sum(b.bytes)/count( DISTINCT a.file_id||'.'||a.block_id )) - ";
    select = select + " (sum(a.bytes)/count( DISTINCT b.file_id ) )) /";
    select = select + " (sum(b.bytes)/count( DISTINCT a.file_id||'.'||a.block_id )),2) pct_used";
    select = select + " FROM sys.dba_free_space a, sys.dba_data_files b";
    select = select + " WHERE a.tablespace_name = b.tablespace_name";
    select = select + " GROUP BY a.tablespace_name, b.tablespace_name";

    String connectStuff = "";
    String userid = "";
    String password = "";
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("connect.txt"));
      while( ( s = r.readLine())!= null ) {
        StringTokenizer st = new StringTokenizer(s,",");
        connectStuff = st.nextToken();
        userid = st.nextToken();
        password = st.nextToken();
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    Connection conn = DriverManager.getConnection (connectStuff,userid, password);

    Statement stmt1 = conn.createStatement ();
    Statement stmt2 = conn.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

    String tableSpace = "";
    ResultSet rset1 = stmt1.executeQuery (select);
    processResults(rset1);
//    while (rset1.next ()) {
//      tableSpace = rset1.getString(1);
//      select2 = select2a + tableSpace + select2b;
//      putString(tableSpace,15);
//      System.out.println(select2);
//      ResultSet rset2 = stmt2.executeQuery (select2);
//      putString(rset1.getString(2),15);
//      putString(rset2.getString(1),15);
//      System.out.println("");
//  }
  } //main

  static void putString(String inStr,int lenStr) {
    int blanks = lenStr - inStr.length() ;
    if (blanks > 0 ) {
      for (int i = 0;i < inStr.length();i++) {
        System.out.print(inStr.substring(i,i+1));
      }
      for (int i = inStr.length(); i <= lenStr; i++) {
        System.out.print(' ');
      }
    }
    else {
      for (int i = 0;i < lenStr;i++) {
        System.out.print(inStr.substring(i,i+1));
      }
      System.out.print(" ");
    }
  } //putString

 static public void processResults(ResultSet results) throws SQLException {
   try {
     ResultSetMetaData meta = results.getMetaData();
     StringBuffer bar = new StringBuffer();
     StringBuffer buffer = new StringBuffer();
     int cols = meta.getColumnCount();
     int row_count = 0;
     int i, width = 0;
            
// Prepare headers for each of the columns
// The display should look like:
//  --------------------------------------
//  |    Column One    |   Column Two    |
//  --------------------------------------
//  |    Row 1 Value   |   Row 1 Value   |
//  --------------------------------------
            
// create the bar that is as long as the total of all columns
    for(i=1; i<=cols; i++) {
      width += meta.getColumnDisplaySize(i);
    }
    width += 1 + cols;
    for(i=0; i<width; i++) {
      bar.append('-');
    }
    bar.append('\n');
    buffer.append(bar.toString() + "|");
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
  buffer.append(label + "|");
 }
// Add the lower bar
  buffer.append("\n" + bar.toString());
// Format each row in the result set and add it on
  while( results.next() ) {
    row_count++;
               
    buffer.append('|');
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
// Stick a row count up at the top
  if( row_count == 0 ) {
    buffer = new StringBuffer("No rows selected.\n");
  }
  else if( row_count == 1 ) {
    buffer = new StringBuffer("1 row selected.\n" +
    buffer.toString() + bar.toString());
  }
  else {
    buffer = new StringBuffer(row_count + " rows selected.\n" +
    buffer.toString() + bar.toString());
  }
  System.out.print(buffer.toString());
  System.out.flush();
}
catch( SQLException e ) {
  throw e;
}
finally {
  try { results.close(); }
    catch( SQLException e ) { }
  }
}

} //free
