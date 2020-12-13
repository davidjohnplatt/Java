import java.sql.*;
import java.io.*;
import java.util.*;

public class oscar {
  // return a quotation from Oscar Wilde
  public static String quote() {
    return "I can resist everything except temptation.";
  }

  public static void makeRow (String args []) throws SQLException {
    String sql = "INSERT INTO TEMP VALUES(?)";
    try {
        Connection conn = DriverManager.getConnection("jdbc:default:connection:");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        String hString = args [0];
        pstmt.setString(1, hString);
        pstmt.executeUpdate(); 
        pstmt.close();
    } 
    catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  } //makeRow
}
