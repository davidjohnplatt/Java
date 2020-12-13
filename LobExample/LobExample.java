/* 
 * This sample demonstrate basic LOB support.
 */

import java.sql.*;
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

//needed for new CLOB and BLOB classes
import oracle.sql.*;

private static String fileLocation;

public class LobExample
{
  public static void main (String[] args )
       throws Exception
  {
    if (args.length == 0)
    {
      System.out.println("\n\n  Usage demo.BlobOracle <file location>");
      System.exit(0);
    }
    fileLocation = args[0];
    // Register the Oracle JDBC driver
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

    // Connect to the database
    // You can put a database name after the @ sign in the connection URL.
    Connection conn =
      DriverManager.getConnection ("jdbc:oracle:oci8:@", "david", "david");

    // It's faster when auto commit is off
    conn.setAutoCommit (false);

    // Create a Statement
    Statement stmt = conn.createStatement ();

    try
    {
      stmt.execute ("drop table basic_lob_table");
    }
    catch (SQLException e)
    {
      // An exception could be raised here if the table did not exist already.
    }

    // Create a table containing a BLOB and a CLOB
    stmt.execute ("create table basic_lob_table (x varchar2 (30), b blob, c clob)");
    
    // Populate the table
    stmt.execute ("insert into basic_lob_table values ('one', '010101010101010101010101010101', 'onetwothreefour')");
    stmt.execute ("insert into basic_lob_table values ('two', '0202020202020202020202020202', 'twothreefourfivesix')");
    
    System.out.println ("Dumping lobs");

    // Select the lobs   
    ResultSet rset = stmt.executeQuery ("select * from basic_lob_table");
    while (rset.next ())
    {
      // Get the lobs
      BLOB blob = ((OracleResultSet)rset).getBLOB (2);
      CLOB clob = ((OracleResultSet)rset).getCLOB (3);

      // Print the lob contents
      dumpBlob (conn, blob);

      dumpClob (conn, clob);

      // Change the lob contents
      fillClob (conn, clob, 2000);
      fillBlob (conn, blob, 4000);
    }

    System.out.println ("Dumping lobs again");

    rset = stmt.executeQuery ("select * from basic_lob_table");
    while (rset.next ())
    {
      // Get the lobs
      BLOB blob = ((OracleResultSet)rset).getBLOB (2);
      CLOB clob = ((OracleResultSet)rset).getCLOB (3);

      // Print the lobs contents
      dumpBlob (conn, blob);
      dumpClob (conn, clob);
    }
    // Close all resources
    rset.close();
    stmt.close();
    conn.close(); 
  }

  // Utility function to dump Clob contents
  static void dumpClob (Connection conn, CLOB clob)
    throws Exception
  {
    // get character stream to retrieve clob data
    Reader instream = clob.getCharacterStream();

    // create temporary buffer for read
    char[] buffer = new char[10];

    // length of characters read
    int length = 0;

    // fetch data  
    while ((length = instream.read(buffer)) != -1)
    {
      System.out.print("Read " + length + " chars: ");

      for (int i=0; i<length; i++)
        System.out.print(buffer[i]);
      System.out.println();
    }

    // Close input stream
    instream.close();
  }

  // Utility function to dump Blob contents
  static void dumpBlob (Connection conn, BLOB blob)
    throws Exception
  {
    // Get binary output stream to retrieve blob data
    InputStream instream = blob.getBinaryStream();

    // Create temporary buffer for read
    byte[] buffer = new byte[10];

    // length of bytes read
    int length = 0;

    // Fetch data  
    while ((length = instream.read(buffer)) != -1)
    {
      System.out.print("Read " + length + " bytes: ");

      for (int i=0; i<length; i++)
        System.out.print(buffer[i]+" ");
      System.out.println();
    }

    // Close input stream
    instream.close();
  }

  // Utility function to put data in a Clob
  static void fillClob (Connection conn, CLOB clob, long length)
    throws Exception
  {
    Writer outstream = clob.getCharacterOutputStream();

    int i = 0;
    int chunk = 10;

    while (i < length)
    {
      outstream.write(i + "hello world", 0, chunk);

      i += chunk;
      if (length - i < chunk)
      chunk = (int) length - i;
    }
    outstream.close();
  }

  // Utility function to put data in a Blob
  static void fillBlob (Connection conn, BLOB blob, long length)
    throws Exception
  {
    OutputStream outstream = blob.getBinaryOutputStream();

    int i = 0;
    int chunk = 10;

    byte [] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    while (i < length)
    {
      data [0] = (byte)i;
      outstream.write(data, 0, chunk);

      i += chunk;
      if (length - i < chunk)
      chunk = (int) length - i;
    }
    outstream.close();
  }

 private static void insertBLOB() throws SQLException, Exception
 {
    BLOB blob;
    File file ;
    FileInputStream is;
    OutputStream os;

      long ts1 = System.currentTimeMillis();
    

      //Create a statement.
      PreparedStatement pstmt = connection.prepareStatement("insert into basic_lob_table (x, b) values (?,empty_blob())");
      file = new File(fileLocation);
      String fileName = file.getName();
      //Set the file name and execute the query
      pstmt.setString(1, fileName);
      pstmt.execute();

      //Take back the record for update (we will insert the blob)
      //supposely the file name is the PK
      pstmt = connection.prepareStatement("select file_content from basic_lob_table where x = ? for update");
      pstmt.setString(1, fileName);
      
      //Execute the query, and we must have one record so take it
      ResultSet rset = pstmt.executeQuery();
      rset.next();

      //Use the OracleDriver resultset, we take the blob locator
     blob = ((OracleResultSet)rset).getBLOB("file_content");

     is = new FileInputStream(file); //Create a stream from the file
     // JDBC 2.0
     //os = blob.getBinaryOutputStream(); //get the output stream from the Blob to insert it
     // JDBC 3.0
     os = blob.setBinaryStream(0); //get the output stream from the Blob to insert it
     
      //Read the file by chuncks and insert them in the Blob. The chunk size come from the blob
      byte[] chunk = new byte[blob.getChunkSize()];
      int i=-1;
      System.out.println("Inserting the Blob");
      while((i = is.read(chunk))!=-1)
      {
        os.write(chunk,0,i); //Write the chunk
        System.out.print('.'); // print progression
      }

      // When done close the streams
      is.close();
      os.close();

      //Close the statement and commit
      pstmt.close();
      long ts2 = System.currentTimeMillis();

      connection.commit();
      connection.close();
      
      System.out.println("\n"+ (ts2 - ts1) +" ms" );      
      

  }
}
