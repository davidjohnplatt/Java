import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.*;

public class jWrite {
    static Connection     connection = null;
    static BufferedReader input;
    
    static public void main(String args[]) throws SQLException {
        DriverPropertyInfo[] required;
        StringBuffer buffer = new StringBuffer();
        Properties props = new Properties();
        boolean connected = false;
        Driver driver;
        String url;
        int line = 1; // Mark current input line

 input = new BufferedReader(new InputStreamReader(System.in));

 String connectStuff = "";
    String userid = "";
    String password = "";
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("connect.txt"));
      while( ( s = r.readLine())!= null ) {
        StringTokenizer st = new StringTokenizer(s,",");
        connectStuff = st.nextToken();
        userid = st.nextToken();
        password = st.nextToken();
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    connection = DriverManager.getConnection (connectStuff,userid, password);

    Statement stmt = connection.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

        connected = true;
        // Enter into a user input loop
        while( connected ) {
            String tmp, cmd;

            // Print a prompt
            if( line == 1 ) {
                System.out.print("Table > ");
            }
            else {
                System.out.print(line +   "  -> ");
            }
            System.out.flush();
            // Get the next line of input
            try {
                tmp = input.readLine();
            }
            catch( java.io.IOException e ) {
                System.out.print("1");
                e.printStackTrace();
                return;
            }
            // Get rid of extra space in the command
            cmd = tmp.trim();
            if( cmd.equals("go") ) {
                if( !buffer.equals("") ) {
                    try {
                        executeStatement(buffer);
                    }
                    catch( SQLException e ) {
                        System.out.println(e.getMessage());
                    }
                }
                buffer = new StringBuffer();
                line = 1;
                continue;
            }
            // The user wants to quit
            else if( cmd.equals("quit") ) {
                connected = false;
                continue;
            }
            // The user wants to clear the current buffer
            else if( cmd.equals("reset") ) {
                buffer = new StringBuffer();
                line = 1;
                continue;
            }
            // The input that is not a keyword should appended be to the buffer
            else {
                buffer.append("SELECT * FROM " + tmp + " WHERE ROWNUM < 1");
                line++;
                continue;
            }
        }
        try {
            connection.close();
        }
        catch( SQLException e ) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
        System.out.println("Connection closed.");
    }
    
    static public void executeStatement(StringBuffer buff)
    throws SQLException {
        String sql = buff.toString();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            if( statement.execute(sql) ) { // true means the SQL was a SELECT
                processResults(statement.getResultSet());
            }
            else { // no result sets, see how many rows were affected
                int num;
            }
        }
        catch( SQLException e ) {
            throw e;
        }
        finally { // close out the statement
            if( statement != null ) {
                try { statement.close(); }
                catch( SQLException e ) { }
            }
        }
    }

    static public String prompt(String prop) throws IOException {
        String tmp = "";
        
        while( tmp.length() < 1 ) {
            System.out.print(prop);
            tmp = input.readLine().trim();
        }
        return tmp;
    }
    
  static public void processResults(ResultSet results) throws SQLException {
    try {
      ResultSetMetaData meta = results.getMetaData();
      StringBuffer bar = new StringBuffer();
      StringBuffer buffer = new StringBuffer();
      int cols = meta.getColumnCount();
      int row_count = 0;
            
      for(int i=1; i<=cols; i++) {
        String label = meta.getColumnLabel(i);
        System.out.println(label.toLowerCase() + ',');
      }
      
     for (int i=1; i<=cols; i++) {
       int num;
       switch(num = meta.getColumnType(i)) {
         case 1:
           System.out.println("String " + meta.getColumnName(i).toLowerCase() +
                              " = \"\";");
           break;
         case 2:
           System.out.println("Number " + meta.getPrecision(i) + "." + meta.getScale(i));
           break;
         case 12:
           System.out.println("Varchar2 " + meta.getColumnDisplaySize(i));
           break;
         case 93:
           System.out.println("Date ");
           break;
         default:
           System.out.println("Unknown Data Type");
       }
     }
   }
   catch( SQLException e ) {
     throw e;
   }
   finally {
     try { 
       results.close(); 
     }
     catch( SQLException e ) {
     }
   }
 }

    static public void showVersion(DatabaseMetaData meta) {
        try {
            System.out.println("TerminalMonitor v2.0");
            System.out.println("DBMS: " + meta.getDatabaseProductName() +
                               " " + meta.getDatabaseProductVersion());
            System.out.println("JDBC Driver: " + meta.getDriverName() +
                               " " + meta.getDriverVersion());
        }
        catch( SQLException e ) {
            System.out.println("Failed to get version info: " +
                               e.getMessage());
        }
    }
}
