
import java.sql.*;
import oracle.jdbc.*;

class jdbc2 {

public static void main(String [] ar)
{


        // Find out what database (from the object ID) this object is connecting to
        //StringTokenizer st = new StringTokenizer( objID, "_" );
       // String dbType = st.nextToken();

        // Formulate database connection string according to the database type
           String hostName="10.16.70.31";
           String portNumber = "1521"  ;
           String databaseSID = "iaf"   ;
           String login ="iaf"     ;
           String password  = "iaf"   ;

            try {
                // Load Oracle driver and register it
                DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
                // Construct the database connection string
                String dbConnectString =
                    "(DESCRIPTION=(ADDRESS=(HOST="+hostName+")"+
                    "(PROTOCOL=tcp)(PORT="+portNumber+"))"+
                    "(CONNECT_DATA=(SID="+databaseSID+")))";
                // Connect to the database
                Connection conn;
                conn = DriverManager.getConnection( "jdbc:oracle:oci8:@"+dbConnectString,login,password );

                // Better performance when we don't commit automatically
                conn.setAutoCommit(false);
            } catch ( Exception ex ) {
                System.err.println( "Error connecting to "+dbType+" database!" );
                System.err.println( "Error Message: "+ex );
            } // try

    } // main
}
