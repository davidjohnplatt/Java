
import java.lang.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;
import oracle.sql.BLOB;

public class serial2 {
   static String genID = 
         "select java_obj_seq.nextval from dual";

   static String writeSQL = 
       "begin insert into java_objects(id,classname,bytes) "+
             " values (?,?,empty_blob()) " +
             " return bytes into ?; end;";

   static String readSQL = 
       "select bytes from java_objects where id = ?";

   public static long write(Connection conn, Object o) 
   throws Exception 
   {
      long id = nextval(conn);
      String className = o.getClass().getName();
      CallableStatement stmt = conn.prepareCall(writeSQL);
      stmt.setLong(1, id);
      stmt.setString(2, className);
      stmt.registerOutParameter(3, java.sql.Types.BLOB);
      stmt.executeUpdate();
      BLOB blob = (BLOB) stmt.getBlob(3);
      OutputStream os = blob.getBinaryOutputStream();
      ObjectOutputStream oop = new ObjectOutputStream(os);
      oop.writeObject(o);
      oop.flush();
      oop.close();
      os.close();
      stmt.close();
      System.out.println("Done serializing " + className);
      return id;
   }

   public static Object read(Connection conn, long id) 
   throws Exception 
   {
      PreparedStatement stmt = conn.prepareStatement(readSQL);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      InputStream is = rs.getBlob(1).getBinaryStream();
      ObjectInputStream oip = new ObjectInputStream(is);
      Object o = oip.readObject();
      String className = o.getClass().getName();
      oip.close();
      is.close();
      stmt.close();
      System.out.println("Done de-serializing " + className);
      return o;
   }

   private static long nextval(Connection conn) 
   throws SQLException 
   {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(genID);
      rs.next();
      long id = rs.getLong(1);
      rs.close();
      stmt.close();
      return id;
   }

   public static void main(String[] argv) 
   throws Exception 
   {
      String cs = "jdbc:oracle:oci8:@lims";
      String user = "david";
      String pass = "david";

      DriverManager.registerDriver(new OracleDriver());
      Connection conn = 
          DriverManager.getConnection(cs, user, pass);
      conn.setAutoCommit(false);

      LinkedList l = new LinkedList();
      l.add("This");
      l.add("is");
      l.add("a");
      l.add("test");
      l.add(new Long(123123123));
      l.add(new java.util.Date());

      long id = write(conn, l);
      conn.commit();

      System.out.println("ID= " + id);
      System.out.println("Object= " + read(conn, id));
      conn.close();
   }
}