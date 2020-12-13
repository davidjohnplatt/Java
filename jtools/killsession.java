import java.sql.*;
import java.io.*;
import java.util.*;

public class killsession {

  public killsession() {
  } //constructor

  public static void main (String args []) throws SQLException {

    if( args.length < 2 ) {
      System.out.println("Error -  SID and SERIAL# must be supplied ");
      return;
    }

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

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

    String Sid = args [0];
    String Serial = args [1];
    String killString = "alter system kill session \'" +  Sid + "," + Serial + "\'";
    try {
      boolean killed = stmt1.execute (killString);
    }
    catch ( SQLException e ) {
      System.out.println("Error " + e.getMessage());
    }
  } //main
} //killSession
