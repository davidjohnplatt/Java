import java.sql.*;
import java.io.*;
import java.util.*;

public class datafiles {


  public datafiles() {
  } //constructor

  public static void main (String args []) throws SQLException {

    String select1;
    select1 =           "SELECT";
    select1 = select1 + "  tablespace_name,";
    select1 = select1 + "  file_name,";
    select1 = select1 + "  bytes / 1024 / 1024 megabytes";
    select1 = select1 + "  FROM dba_data_files";

    String select2;
    select2 =           "SELECT";
    select2 = select2 + "  tablespace_name,";
    select2 = select2 + "  file_name,";
    select2 = select2 + "  bytes / 1024 / 1024 megabytes";
    select2 = select2 + "  FROM dba_temp_files";
    select2 = select2 + " ORDER BY 1";

    String connectStuff = "";
    String userid = "";
    String password = "";
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("c:/mydocs/java/jtools/connect.txt"));
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
    System.out.println("Tablespace      Datafile                                                          Size");
    System.out.println("===============================================================================");

    ResultSet rset = stmt.executeQuery (select1);
    while (rset.next ()) {
      putString(rset.getString(1),15);
      putString(rset.getString(2),45);
      putString(rset.getString(3),10);
      System.out.println("");
    }

    try {
      ResultSet rset2 = stmt.executeQuery (select2);
    while (rset2.next ()){
      putString(rset2.getString(1),15);
      putString(rset2.getString(2),65);
      putString(rset2.getString(3),10);
      System.out.println("");
    }
    }
    catch (SQLException e){
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


