import java.sql.*;
import java.io.*;
import java.util.*;

public class jPopulate{

  public jPopulate() {
  } //constructor


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

  static int getLosId (Connection conn) {
    int LosId = 0;
    String sql = "SELECT MAX(losid) FROM iaf_los";

    try {
      Statement stmt = conn.createStatement ();
      ResultSet rset = stmt.executeQuery (sql);
      while (rset.next ()) {
        LosId = rset.getInt(1);
      }
    }
    catch ( SQLException e ) {
      System.out.println("Error in getLosId " + e.getMessage());
    }
    return LosId;
  } //getLosId

  static int getAccountServices (Connection conn) {
    int Sid = 0;
    String sql = "SELECT MAX(sid) FROM iaf_services";

    try {
      Statement stmt = conn.createStatement ();
      ResultSet rset = stmt.executeQuery (sql);
      while (rset.next ()) {
        Sid = rset.getInt(1);
      }
    }
    catch ( SQLException e ) {
      System.out.println("Error in getAccountServices" + e.getMessage());
    }
    return Sid;
  } //getAccountServices

  static String getAccountPop (Connection conn) {
    String Pop = "";
    String sql = "SELECT name FROM iaf_pops";

    try {
      Statement stmt = conn.createStatement ();
      ResultSet rset = stmt.executeQuery (sql);
      while (rset.next ()) {
        Pop = rset.getString(1);
      }
    }
    catch ( SQLException e ) {
      System.out.println("Error in getAccountPop" + e.getMessage());
    }
    return Pop;
  } //getAccountPop

  static boolean newAccount (Connection conn,int offset) {
    boolean oKay = true;
    String sql = "INSERT INTO iaf_accounts ( firstname," +
                 "secondname, lastname, company, street1, street2," +
                 "city, prov, postal, country, home, work," +
                 "fax, credit, language, region, com_res, category," +
                 "status, billcard, subdate, termdate, svcmajor, svcminor," +
                 "terms, sin, dlicense, billday, notes, detailed_bill," +
                 "promotion, promotion_offering, tax_location,custnum," +
                 "parent_custnum) VALUES(" +
                 "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    Integer custnum = new Integer("2010000");
    String firstname = "Firstname" ;
    String secondname = "Secondname";
    String lastname = "Lastname";
    String company = "Company";
    String street1 = "123 Any Street";
    String street2 = "";
    String city = "Yourtown";
    String prov = "ON";
    String postal = "M9W 5L1";
    String country = "Canada";
    String home = "416-555-1212";
    String work = "416-555-1212";
    String fax = "416-555-1212";
    String credit = "Unknown";
    String language = "E";
    String region = "LOCAL";
    String com_res = "R";
    String category = "LOCAL";
    String status = "A";
    String billcard = "";
    java.sql.Date subdate = new java.sql.Date(2002,12,31);
    String termdate = "";
    int svcmajor = 0;
    int svcminor = 0;
    String terms = "NET1";
    String sin = "";
    String dlicense = "";
    int billday = 1;
    String notes = "";
    String detailed_bill = "N";
    String promotion = "";
    String promotion_offering = "";
    int tax_location = 0;
    Integer parent_custnum;

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,firstname);
        pstmt.setString(2,secondname);
        pstmt.setString(3,lastname);
        pstmt.setString(4,company);
        pstmt.setString(5,street1);
        pstmt.setString(6,street2);
        pstmt.setString(7,city);
        pstmt.setString(8,prov);
        pstmt.setString(9,postal);
        pstmt.setString(10,country);
        pstmt.setString(11,home);
        pstmt.setString(12,work);
        pstmt.setString(13,fax);
        pstmt.setString(14,credit);
        pstmt.setString(15,language);
        pstmt.setString(16,region);
        pstmt.setString(17,com_res);
        pstmt.setString(18,category);
        pstmt.setString(19,status);
        pstmt.setString(20,billcard);
        pstmt.setDate(21,subdate);
        pstmt.setString(22,termdate);
        pstmt.setInt(23,svcmajor);
        pstmt.setInt(24,svcminor);
        pstmt.setString(25,terms);
        pstmt.setString(26,sin);
        pstmt.setString(27,dlicense);
        pstmt.setInt(28,billday);
        pstmt.setString(29,notes);
        pstmt.setString(30,detailed_bill);
        pstmt.setString(31,promotion);
        pstmt.setString(32,promotion_offering);
        pstmt.setInt(33,tax_location);
        pstmt.setInt(34,custnum);
        pstmt.setInt(35,parent_custnum);
        pstmt.executeUpdate(); 
        pstmt.close();
    }
    catch ( SQLException e ) {
      System.out.println("Error in newAccount" + e.getMessage());
    }
    return oKay;
  } //newAccount

  public static void main (String args []) throws SQLException {

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

    System.out.println("==========================================================");
    System.out.println("Host Connect Info :  " +  connectStuff);
    System.out.println("==========================================================");

    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    Connection conn = DriverManager.getConnection (connectStuff,userid, password);

    System.out.println("LOS           : " + getLosId(conn));
    System.out.println("SID           : " + getAccountServices(conn));
    System.out.println("POP           : " + getAccountPop(conn));
    boolean OKay = newAccount(conn,1);

  } //main
} //popTestData
