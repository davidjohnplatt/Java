import java.sql.*;

class jdbcproc {

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
                String dbConnectString =
                    "(DESCRIPTION=(ADDRESS=(HOST="+hostName+")"+
                    "(PROTOCOL=tcp)(PORT="+portNumber+"))"+
                    "(CONNECT_DATA=(SID="+databaseSID+")))";
                // Connect to the database
                Connection conn;
                conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+dbConnectString,login,password );
                String p1, p2, p3, p4, p5;
p1 = "USER_ITEMS";  //binding
p2= "USERID";
p3="WELCOME_EMAIL";
p4="aaaa";
p5="GET";

CallableStatement csmt = conn.prepareCall( " {call SendWelcomeEmail(?,?,?,?,?)}");
     //  where XXXXXXXX  is name of procedure
int x=4;
//Identify type of output parameter( IN OUT)
// csmt.registerOutParameter(4, java.sql.Types.FLOAT)    //if  parameter 4 is IN OUT of type
System.out.println("csmt constructed");
//Set values of parameters
//csmt.setString(5,"GET");
csmt.setString(1, p1);  // setInt() , setFloat() etc
csmt.setString(2, p2);
csmt.setString(3, p3);
csmt.setString(4, p4);
csmt.setString(5, p5);
csmt.registerOutParameter(4,Types.CHAR);
//After values are set execute procedure
System.out.println("values set");
csmt.execute();
System.out.println("execute done");
//Print returned value as

System.out.println("Result of Procedure"+ csmt.getString(4));


// Better performance when we don't commit automatically
                //conn.setAutoCommit(false);
            } catch ( Exception ex ) {
               // System.err.println( "Error connecting to  database!" );
                System.err.println( "Error Message: "+ex );
            } // try

    } // main
}

