import java.sql.*;
import java.io.*;
import java.util.*;

public class OracleMonitor {
    
    public static void main(String[] args) {
    	
    	String userid = "";
        String password = "";
        String s;
        boolean kontinue;
        String connectStuff = "";
        
        kontinue = true;
        while (kontinue) {
	        try {
	          BufferedReader r = new BufferedReader(new FileReader("c:/mydocs/connect.txt"));
	          while( ( s = r.readLine())!= null ) {
	             StringTokenizer st = new StringTokenizer(s,",");
	             connectStuff = st.nextToken();
	             userid = st.nextToken();
	             password = st.nextToken();
//               System.out.println(connectStuff);
	             OracleUtil ou = new OracleUtil(connectStuff,userid,password);
	             ou.printList();
	          }
	          r.close();
	          }
	       catch (IOException e) {
	          e.printStackTrace();
	       }
	       catch (SQLException e){
	       	 System.out.println("Error Connecting to " + connectStuff);
	       }
	       
	       try {
	         Thread.currentThread().sleep(100000);
	       }
	       catch (InterruptedException e) {
	         e.printStackTrace();
	       }

       }//end while
    }
}
