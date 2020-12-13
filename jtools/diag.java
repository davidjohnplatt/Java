import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class diag {


  public diag() {
  } //constructor

  public static void main (String args []) throws SQLException {

    String connectStuff = "";
    String userid = "";
    String password = "";
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("/export/home/IAF/lmt/java/connect.txt"));
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
    Connection conn = DriverManager.getConnection (connectStuff,userid, password);

    Statement stmt = conn.createStatement ();

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

    String query1 = "";
    query1 = query1 + "SELECT (1 - SUM(reloads)/SUM(pins)) * 100";
    query1 = query1 + "  FROM v$librarycache";

    ResultSet rset = stmt.executeQuery (query1);
    rset.next();
    double libraryCacheHit = rset.getDouble(1);
    
    if ( libraryCacheHit > 99 ) {
      System.out.println( "Library Cache Hit Ratio        : " + libraryCacheHit + "      OK" );
    }
    else {
      System.out.print( "Library Cache Hit Ratio        : " + libraryCacheHit  );
      System.out.println( "    Increase Shared Pool Size" );
    } 
    
    String query2 = "";
    query2 = query2 + "SELECT  (1 - SUM(getmisses)/SUM(gets)) * 100";
    query2 = query2 + "  FROM v$rowcache";

    rset = stmt.executeQuery (query2);
    rset.next();
    double dictionaryCacheHit = rset.getDouble(1);
    
    if ( dictionaryCacheHit > 99 ) {
      System.out.println( "Dictionary Cache Hit Ratio     : " + dictionaryCacheHit + "      OK" );
    }
    else {
      System.out.println( "Dictionary Cache Hit Ratio     : " + dictionaryCacheHit  );
      System.out.println( "   - Increase Shared Pool Size" );
    } 
    
    String query3 = "";
    query3 = query3 + "SELECT ( 1-(phy.value/ (cur.value + con.value)))*100";
    query3 = query3 + "   FROM v$sysstat cur, v$sysstat con, v$sysstat phy";
    query3 = query3 + "   WHERE cur.name='db block gets'";
    query3 = query3 + "   AND con.name='consistent gets'";
    query3 = query3 + "   AND phy.name='physical reads'";

    rset = stmt.executeQuery (query3);
    rset.next();
    double bufferCacheHit = rset.getDouble(1);
    
    if ( bufferCacheHit > 99 ) {
      System.out.println( "Buffer Cache Hit Ratio         : " + bufferCacheHit + "      OK" );
    }
    else {
      System.out.println( "Buffer Cache Hit Ratio         : " + bufferCacheHit  );
      System.out.println( "   - Increase db_block_buffers paramte" );
    } 
    
    System.out.println("==========================================================");

  } //main

  static void putString(String inStr,int lenStr) {
    int blanks = lenStr - inStr.length() ;
    if (blanks > 0 ) {
      for (int i = 0;i < inStr.length();i++) {
        System.out.print(inStr.substring(i,i+1));
      }
      for (int i = inStr.length(); i <= lenStr; i++) {
        System.out.print(' ');
      }
    }
    else {
      for (int i = 0;i < lenStr;i++) {
        System.out.print(inStr.substring(i,i+1));
      }
      System.out.print(" ");
    }
  } //putString
} //datafiles8


