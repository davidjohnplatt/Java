import java.sql.*;
import java.io.*;
import java.util.*;

public class expandfile {
  static BufferedReader input;

  public static void main (String args []) throws SQLException {

    String alterStart = "alter database datafile \'";
    String alterMiddle = "\' RESIZE ";
    String alterLast = "M";
    String alterAll = "";

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

    Statement statement = conn.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

    input = new BufferedReader(new InputStreamReader(System.in));
    try {
      String fileSpec = prompt("Enter file specification            : ");
      String targetSize = prompt("Enter desired file size (Megabtyes) : ");
      alterAll = alterStart + fileSpec + alterMiddle + targetSize + alterLast;
    }
    catch( IOException e ) {
      e.printStackTrace();
      System.err.println("Unable to read property info.");
      return;
    }
    statement = conn.createStatement();
    if( !statement.execute(alterAll) ) {
      System.out.println("Done");
    }
  } //main


  static public String prompt(String prop) throws IOException {
    String tmp = "";
        
    while( tmp.length() < 1 ) {
      System.out.print(prop);
      tmp = input.readLine().trim();
    }
    return tmp;
  }//prompt

} //expandfile
