import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class jProc {
  // return a quotation from Oscar Wilde
  public static String quote() {
    return "I can resist everything except temptation.";
  }

  public static void makeRow (String inStr) throws SQLException {
    String sql = "INSERT INTO TEMP VALUES(?)";
    try {
        Connection conn = DriverManager.getConnection("jdbc:default:connection:");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, inStr);
        pstmt.executeUpdate(); 
        pstmt.close();
    } 
    catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  } //makeRow
  
  public static void parseURL (String inStr){
    String nameURL = "http://10.16.81.57";
    String str;
    try {

      URL myURL = new URL(nameURL);
      BufferedReader r = new BufferedReader(new InputStreamReader(myURL.openStream()));
     
      while ((str = r.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(str,"<>");
        while (st.hasMoreTokens()) {
//        System.out.print(st.nextToken() + " ");
          try {
            makeRow(st.nextToken());
          } 
          catch (SQLException e) {
            System.err.println(e.getMessage());
          }
        }
      }
    }
    catch (IOException e){
        e.printStackTrace();
    }
  }
}
