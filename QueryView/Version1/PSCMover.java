import java.sql.*;

/**
 * AWT Sample application
 *
 * @author 
 * @version 1.00 04/02/02
 */
 

public class PSCMover {
    
    public static void main(String[] args) {
        // Create application frame.
       // PSCMoverFrame frame = new PSCMoverFrame();
        
        // Show frame
        //frame.setVisible(true);
        
        QueryViewer q = new QueryViewer();
        q.setVisible(true);
/*    
		// Formulate database connection string according to the database type
		String hostName="bubba";
        String portNumber = "1521"  ;
        String databaseSID = "LIMS"   ;
        String login ="Montreal"     ;
        String password  = "smil12"   ;
        String cn,fn;
        try {
			// Load Oracle driver and register it
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			// Construct the database connection string
            String dbConnectString ="(DESCRIPTION=(ADDRESS=(HOST="+hostName+")"+"(PROTOCOL=tcp)(PORT="+portNumber+"))" + "(CONNECT_DATA=(SID="+databaseSID+")))";
			// Connect to the database
			Connection conn;
            conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+dbConnectString,login,password );
			// Better performance when we don't commit automatically
          	//conn.setAutoCommit(false);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT cust_name FROM customers");
            System.out.println("Cust Name    "+"   cust_name ");
            int i = 1;
            while(rs.next()){ 
            	fn= rs.getString(1);
				System.out.println(fn);   
            }//while
 		} 
 		catch ( Exception ex ) {
                System.err.println( "Error connecting to  database!" );
                System.err.println( "Error Message: "+ex );
        } // try
*/

    }
}
