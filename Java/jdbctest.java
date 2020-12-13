import java.sql.*;

class jdbctest {

public static void main(String [] ar)
{


        // Find out what database (from the object ID) this object is connecting to
        //StringTokenizer st = new StringTokenizer( objID, "_" );
       // String dbType = st.nextToken();

        // Formulate database connection string according to the database type
           String hostName="10.16.82.104";
           String portNumber = "1521"  ;
           String databaseSID = "iaf"   ;
           String login ="iaf"     ;
           String password  = "iaf"   ;
           String cn,fn;
            try {
                // Load Oracle driver and register it
                DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
                // Construct the database connection string
                System.out.println("Driver Manager registered");
                 String dbConnectString =
                    "(DESCRIPTION=(ADDRESS=(HOST="+hostName+")"+
                    "(PROTOCOL=tcp)(PORT="+portNumber+"))"+
                    "(CONNECT_DATA=(SID="+databaseSID+")))";
                // Connect to the database
                Connection conn;
                conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+dbConnectString,login,password );
                 Statement stat = conn.createStatement();
                 System.out.println("connection established");
                 System.out.flush();

                ResultSet rs = stat.executeQuery("select FIRSTNAME,CUSTNUM from IAF_ACCOUNTS");
System.out.println("First Name    "+"   CustNum ");
                        int i = 1;
                        while(rs.next())
                   { fn= rs.getString(1);
                     cn = rs.getString(2);
                     System.out.println(fn+"      "+cn);
                    if (i>10)
                      break;
                     i++;
                    }
                // Better performance when we don't commit automatically
                //conn.setAutoCommit(false);
            } catch ( Exception ex ) {
                System.err.println( "Error connecting to  database!" );
                System.err.println( "Error Message: "+ex + ex.getMessage() );
            } // try

    } // main
}
