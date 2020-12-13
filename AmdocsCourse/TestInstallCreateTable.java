import java.sql.*;
import oracle.sqlj.runtime.Oracle;
import sqlj.runtime.ref.DefaultContext;

class TestInstallCreateTable {

  public static void main (String args[]) throws SQLException
  {
    Connection conn=null;;
    PreparedStatement ps=null;

    /* if you're using a non-Oracle JDBC Driver, add a call here to
        DriverManager.registerDriver() to register your Driver
    */

    // set the default connection to the URL, user, and password
    // specified in your connect.properties file
    Oracle.connect(TestInstallCreateTable.class, "connect.properties");

    conn = DefaultContext.getDefaultContext().getConnection();

    try {
      ps = conn.prepareStatement("DROP TABLE SALES");
      ps.executeUpdate();
    } catch (SQLException e) {
      // it'll throw an error of the table doesn't exist in many JDBC drivers
        ;
    }

    try {
      ps = conn.prepareStatement(
        "CREATE TABLE SALES (" +
                "ITEM_NUMBER NUMBER, " +
                "ITEM_NAME CHAR(30), " +
                "SALES_DATE DATE, " +
                "COST NUMBER, " +
                "SALES_REP_NUMBER NUMBER, " +
                "SALES_REP_NAME CHAR(20))");

      ps.executeUpdate();

      System.out.println("SALES table created");
    } catch (SQLException se) {
        System.out.println("oops! can't create the sales table.  error is:");
        se.printStackTrace();
    }
  }
}
