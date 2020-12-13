import java.io.*;
import java.util.*;
import java.sql.*;

public class DumpTable {
	
	public DumpTable() {
		System.out.println("Starting Constructor...");
		DoDump();
	
	}
  
  	private static void DoDump(){
  		System.out.println("Starting DumpTable");
   		String connectStuff = "jdbc:oracle:thin:@tio5:1521:iaf";
    	String userid = "iaf";
    	String password = "iaf";
  		String tableName = "iaf_packages";
  		String outFile = "/tmp//" + tableName + ".sql";
  		Statement stmt;
  		
        try {
     		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection (connectStuff,userid, password);
    		
    	
    		String query = "SELECT * FROM " + tableName; 
    		System.out.println(query);
    		stmt = conn.createStatement();
        	ResultSet rset = stmt.executeQuery (query);
        	ResultSetMetaData meta = rset.getMetaData();
        	int cols = meta.getColumnCount();
        	while (	rset.next()) {
        		w.print("INSERT INTO " + tableName + " VALUES(");
        		for(int i=1; i<=cols; i++) {
        			w.print("'");
                	w.print(rset.getString(i));
                	w.print("'");
                	w.print(",");
            	}
            	w.print(")\n");
        	}
        		
 			w.close();
  		
        }
    	catch (IOException e) {
      		e.printStackTrace();
      	}
    	catch (SQLException e) {
    		System.out.println("Error - SQL Statement " + e);
    		
    	}
    	
    	
	}
	
	public static void main(String [] args) {
		
		System.out.println("Hello. I'm calling to a constructor...");
		
		DumpTable dt = new DumpTable();
		
		if (dt!= null){
			System.out.println("The instance has been created...");
		}
		
  	} //main
  
}