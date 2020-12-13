import java.sql.*;
import java.io.*;
import java.util.*;

public class mysql {


  public mysql () {
  } //constructor

  public static void main (String args []) throws SQLException {

    String select1 = "SELECT * FROM ins_subsystems";
   

    String connectStuff = "";
    String userid = "";
    String password = "";
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("/export/home/IAF/lmt/java/connect.txt"));
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

    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection conn = DriverManager.getConnection (connectStuff,userid, password);

    Statement stmt = conn.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");
    System.out.println("Tablespace      Datafile                                                          Size");
    System.out.println("======================================================================================");

    ResultSet rset = stmt.executeQuery (select1);
    while (rset.next ()) {
      putString(rset.getString(1),15);
      putString(rset.getString(2),65);
      putString(rset.getString(3),10);
      System.out.println("");
    }

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
} //datafiles8


