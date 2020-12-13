
import java.sql.*;
import java.io.*;
import java.util.*;

public class connectDb {
	String connectStuff = "";
   	String userid = "";
   	String password = "";

  	public connectDb() {
    	String s;
    	try {
      		BufferedReader r = new BufferedReader(new FileReader("/Myhome/Java/jtools/connect.mysql"));
      		while( ( s = r.readLine())!= null ) {
        		StringTokenizer st = new StringTokenizer(s,",");
        		connectStuff = st.nextToken();
//      		userid = st.nextToken();
//      		password = st.nextToken();
      		}
      		r.close();
    	}
    	catch (IOException e) {
      		e.printStackTrace();
    	}
    } //constructor
    
    public Connection getConnection() throws SQLException{
    	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    	Connection conn = DriverManager.getConnection (connectStuff,userid,password);
    	return conn;
    }
}

  

