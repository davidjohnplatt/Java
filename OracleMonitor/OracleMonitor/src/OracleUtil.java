
import java.sql.*;
import java.io.*;
import java.util.*;

public class OracleUtil {
  static String connectStuff,userid,password;

  public OracleUtil() {
  } //constructor

  public OracleUtil(String pconnectStuff,String puserid, String ppassword) {
    connectStuff = pconnectStuff;
    userid = puserid;
    password = ppassword;
  } //constructor
  
  public static void printList()  throws SQLException {

    String select1;
    select1 =           "SELECT COUNT(*) FROM v$session";
   
    try {  
    	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    	Connection conn = DriverManager.getConnection (connectStuff,userid, password);
    	Statement stmt = conn.createStatement ();

    	ResultSet rset = stmt.executeQuery (select1);
    	while (rset.next ()) {
      		putString(connectStuff,41);
      		putString(rset.getString(1),10);
      		System.out.println("");
    	}
    }
    catch (SQLException e){
    	System.out.println("Error Connecting to " + connectStuff);
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
}
