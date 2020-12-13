import java.io.*;
import java.util.*;
import java.sql.*;

public class DbUtility {
	
 	
 	public static class DumpTable {
  
  	public static void DumpTable(String tableName){
  		String connectStuff = "jdbc:oracle:thin:@london12:1521";
    	String userid = "iaf";
    	String password = "iaf";
  		String outFile = "/tmp//" + tableName + ".sql";
        try {
     		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

//        	Class.forName("com.mysql.jdbc.Driver").newInstance();
//    		Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace	
   

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection (connectStuff,userid, password);
    		
    		String query = "SELECT * FROM " + tableName; 
    		Statement stmt = conn.createStatement();
        	ResultSet rset = stmt.executeQuery (query);
        	ResultSetMetaData meta = rset.getMetaData();
        	int cols = meta.getColumnCount();
        	while (	rset.next()) {
        		for(int i=1; i<=cols; i++) {
                	w.print(rset.getString(i) + ",");
            	}
        	}
        		
 			w.close();
  		
        }
    	catch (IOException e) {
      		e.printStackTrace();
      	}
    	catch (SQLException e) {
    		System.out.println("Error - SQL Statement");
    	}
    
	}
  

  
}
	public static void main(String [] args) {
  		DumpTable d = new DumpTable("iaf_packages");	
  	} //main
  
}