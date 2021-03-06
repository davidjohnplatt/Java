import java.io.*;
import java.util.*;
import java.sql.*;

public class Utility {
 	
 	public static class DumpTable {
  
  		public static void dumpTable(String tableName){
  			String outFile = "/tmp//" + tableName + ".sql";
        	try {
     			PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

     		   	Class.forName("com.mysql.jdbc.Driver").newInstance();
    			Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    		
    			String query = "SELECT * FROM " + tableName; 
    			Statement stmt = conn.createStatement();
        		ResultSet rset = stmt.executeQuery (query);
        		ResultSetMetaData meta = rset.getMetaData();
        		int cols = meta.getColumnCount();
        		while (	rset.next()) {
        			w.print("INSERT INTO " + tableName + " VALUES(");
        			for(int i=1; i<=cols; i++) {
        				if ( i == 1) {
                			w.print("'" + rset.getString(i) + "'");
                		}
                		else {
                			w.print("," + "'" + rset.getString(i) + "'");
            			}
            			
            		}
            		w.print(");\n");
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
  			Utility.DumpTable.dumpTable("race_detail");
 
        	
  		} //main
  
	}// DumpTable
	
	public static void main(String [] args){
		System.out.println("Dumping Table");
		DumpTable.dumpTable("boats");
	}
  
}// Utility