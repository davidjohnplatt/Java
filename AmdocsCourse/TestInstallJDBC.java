import java.sql.*;
import oracle.sqlj.runtime.Oracle;
import sqlj.runtime.ref.DefaultContext;

class TestInstallJDBC {

  public static void main (String args[]) throws SQLException
  {
    Connection conn=null;;
    PreparedStatement ps=null;
    ResultSet rs=null;

    /* if you're using a non-Oracle JDBC Driver, add a call here to
        DriverManager.registerDriver() to register your Driver
    */

    // set the default connection to the URL, user, and password
    // specified in your connect.properties file
    Oracle.connect(TestInstallJDBC.class, "connect.properties");

    conn = DefaultContext.getDefaultContext().getConnection();

    conn.prepareStatement("DELETE FROM SALES").executeUpdate();
    conn.prepareStatement(
        "INSERT INTO SALES(ITEM_NAME) VALUES ('Hello JDBC!')").executeUpdate();

    ps = conn.prepareStatement("SELECT ITEM_NAME FROM SALES");
    rs = ps.executeQuery();

    while (rs.next()) {
      System.out.println(rs.getString(1));
    }
  }
}


