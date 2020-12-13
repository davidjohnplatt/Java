import java.sql.*;
import java.io.*;
import java.util.*;

public class rollbacks {

  public static void main (String args []) throws SQLException {

    String select1 = "";
    select1 = select1 + "SELECT segment_name rollback,tablespace_name tablespace,";
    select1 = select1 + "  initial_extent,next_extent,max_extents";
    select1 = select1 + "  FROM dba_rollback_segs";
    select1 = select1 + " ORDER BY segment_name";

    String select2 = "";
    select2 = select2 + "SELECT n.name,";
    select2 = select2 + "       s.optsize Optimal";
    select2 = select2 + "  FROM v$rollname n,";
    select2 = select2 + "       v$rollstat s";
    select2 = select2 + " WHERE n.usn = s.usn";
    select2 = select2 + " ORDER BY n.name";

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

    Statement stmt = conn.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

    String tableSpace = "";
    ResultSet rset1 = stmt.executeQuery (select1);
    processResults(rset1);
    ResultSet rset2 = stmt.executeQuery (select2);
    processResults(rset2);
  } //main


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
