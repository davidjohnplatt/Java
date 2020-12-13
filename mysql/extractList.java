/*
 * @(#)Mysql.java 1.0 02/08/19
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
package myprojects.mysql;

import java.sql.*;
import java.io.*;
import java.util.*;

public class extractList {


  public extractList () {
  } //constructor

  public static void main (String args []) throws SQLException {

    String select1 = "SELECT * FROM ins_subsystem_details";
   

    String connectStuff = "";
    String userid = "";
    String password = "";
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("/Myhome/Java/jtools/connect.mysql"));
      while( ( s = r.readLine())!= null ) {
        StringTokenizer st = new StringTokenizer(s,",");
        connectStuff = st.nextToken();
//      userid = st.nextToken();
//      password = st.nextToken();
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection conn = DriverManager.getConnection (connectStuff,userid,password);

    Statement stmt = conn.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");
    System.out.println("Code          Description");
    System.out.println("==========================================================");
	
	try {
	  DataOutputStream df1 = new DataOutputStream (new FileOutputStream("/tmp/Systems.txt"));
      ResultSet rset = stmt.executeQuery (select1);
      while (rset.next ()) {
        putString(rset.getString(1),15);
        putString(rset.getString(2),30);
        putString(rset.getString(3),30);

        df1.writeBytes(rset.getString(1) + ",");
        df1.writeBytes(rset.getString(2) + ",");
        df1.writeBytes(rset.getString(3));
        df1.writeBytes("\n");
        System.out.println("");
      }
  	}
    catch (IOException e) {
      e.printStackTrace();
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
  
    static String padString(String inStr,int lenStr) {
    	int blanks = lenStr - inStr.length() ;
    	if (blanks > 0 ) {
      		for (int i = inStr.length();i < lenStr;i++) {
         	inStr = inStr + " ";
      		}
    	}
    	else {
      		inStr = inStr.substring(0,lenStr); 
    	}
    	return inStr;
  	} //padString
} //extractList