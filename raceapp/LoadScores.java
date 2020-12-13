import java.lang.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class LoadScores {
  public double sheet [] [] = new double [100] [100];
  Connection conn;
  
  public LoadScores(){
    int i = 0;
    int j = 0;
    int k = 0;
    int lineLength = 0;
    String s;
    
       try {
  
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    	conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    	}
    	catch (SQLException e) {
    	}
    	catch (ClassNotFoundException f){
    	}
    	catch (InstantiationException ie) {
    	}
    	catch (IllegalAccessException a) {
    	}
    
    try {
      BufferedReader r = new BufferedReader(new FileReader("scoreSheet.dat"));
      String insCmd = "INSERT INTO scores VALUES (";
	
      while( (s = r.readLine())!= null ) {
        i++;
        while (k < s.length()) {
          j++;
          sheet [i] [j] =  Double.parseDouble(s.substring( k, k + 5 ));
          String thisCmd = insCmd + i + "," + j + ',' + Double.parseDouble(s.substring( k, k + 5 )) + ')';
 		  System.out.println(thisCmd + " " + k);
 		  Statement stmt = conn.createStatement ();
		  stmt.executeUpdate(thisCmd);
          k = k + 6;
        }
        k = 0;
        j = 0;
      
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }	
    catch (SQLException e) {
     	e.printStackTrace();
    }
  } //LoadScores constructor
  
  public static void main (String [] args) {
  	  LoadScores l = new LoadScores();
  }
  

}//LoadScores
