import java.io.*;
import java.util.*;
import java.sql.*;


 	
public class ExportSheet {
  
  	public static void ExportSheet(String tableName){
  		String outFile = "/tmp//" + tableName + ".sql";
        try {
     		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    		
    		String query = "SELECT * FROM sailors"; 
    		Statement stmt = conn.createStatement();
        	ResultSet rset = stmt.executeQuery (query);
        	
        	while (	rset.next()) {
        		String queryRD
        		
        	}
        		
 			w.close();
  		
        }
    	catch (IOException e) {
      		e.printStackTrace();
      	}
    	catch (SQLException e) {
    		System.out.println("Error - SQL Statement");
    	}
    	catch (ClassNotFoundException f){
    	}
    	catch (InstantiationException i) {
    	}
    	catch (IllegalAccessException a) {
    	}
	}
  
	public static void main(String [] args) {
  		ExportSheet d = new ExportSheet();	
  	} //main
}