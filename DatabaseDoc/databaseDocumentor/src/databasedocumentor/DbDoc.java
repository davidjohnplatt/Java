/*
 * dbdoc.java
 *
 * Created on March 29, 2007, 4:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package databasedocumentor;
import java.sql.*;
import java.io.*;
import java.util.*;


/**
 *
 * @author David
 */
public class DbDoc {
    
    /** Creates a new instance of Main */
    public DbDoc() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
      String Owner_ = "DBA_ADMIN";
      String fileName_ = Owner_ + ".html";
      writeHTMLhead(fileName_);
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName_, true));
    
      String connectStuff = "";
      String userid = "";
      String password = "";
      Connection conn = getConnection(connectStuff,userid,password);
      Statement getTablesStmt = conn.createStatement ();
      Statement oneTableStmt = conn.createStatement ();
      ResultSet allTablesRset = getTablesStmt.executeQuery (getTables(Owner_));
      while (allTablesRset.next ()) {
        String tableName_ = allTablesRset.getString(1);
        System.out.println(tableName_);
        out.write(getH2String(tableName_));
        out.write(startHTMLtable());
        ResultSet oneTableRset = oneTableStmt.executeQuery (getOneTable(Owner_,tableName_));
        while (oneTableRset.next ()) {
            out.write(startHTMLrow());
            String columnName_ = oneTableRset.getString(3);
            String dataType_ = oneTableRset.getString(4);
            int dataLength_ = oneTableRset.getInt(7);
            int dataPrecision_ = oneTableRset.getInt(8);
            int dataScale_ = oneTableRset.getInt(9);
            String nullable_ = oneTableRset.getString(10);
            String dataDefault_= oneTableRset.getString(13);
            
            if (dataType_.equals("NUMBER")) {
                 if (dataPrecision_ > 0) {
                    dataType_ = dataType_ + "(" + dataPrecision_;
                     if (dataScale_ > 0) {
                         dataType_ = dataType_ + "," + dataScale_ + ")";
                     }//if
                     else {
                        dataType_ = dataType_ + "}";
                     }//else
                }//if
            }
            else {
                 dataType_ = dataType_ + "(" + dataLength_ + ")";             
            }//else
            
            if (nullable_.equals("Y")) {
                nullable_ = " ";                        
            }//if
            else {
                nullable_ = "NOT NULL";
            }//else

//            System.out.println("       " + columnName_);
            out.write(makeHTMLcell(columnName_ ));
            out.write(makeHTMLcell(dataType_ ));
            out.write(makeHTMLcell(nullable_ ));
            out.write(makeHTMLcell(dataDefault_ ));
            out.write(stoptHTMLrow());
        }//while
        out.write(stopHTMLtable());
      }//while
      writeHTMLtail(fileName_);
      System.out.println(getOneTable("QP_ROGERS_ADMIN","PROFILES"));
      System.out.println(getTables(Owner_));
      out.close();
    }
    
     
    public static Connection getConnection (String cs,String user,String pass) throws Exception{
      String s;
      try {
        BufferedReader r = new BufferedReader(new FileReader("connect.oracle"));
        while( ( s = r.readLine())!= null ) {
          StringTokenizer st = new StringTokenizer(s,",");
          cs = st.nextToken();
          user = st.nextToken();
          pass = st.nextToken();
        }
        r.close();
      }
      catch (IOException e) {
        System.out.println("Error opening password file");
        e.printStackTrace();     
      }
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      Connection conn = DriverManager.getConnection (cs,user, pass);
      return conn;     
    }
    
    public static String getOneTable(String Owner,String tableName) {
        String strSQL;
        strSQL = "SELECT * ";
        strSQL += " FROM dba_tab_columns";
        strSQL += " WHERE table_name  = ";
        strSQL += "'" + tableName + "'";
        strSQL += "  AND  owner = ";
        strSQL += "'" + Owner + "'";
        return strSQL;
    }
    
     public static String getTables(String Owner) {
        String strSQL;
        strSQL = "SELECT table_name,owner ";
        strSQL += " FROM dba_tables";
        strSQL += " WHERE owner  = ";
        strSQL += "'" + Owner + "'";
        strSQL += " AND table_name NOT LIKE 'DR%'";
        strSQL += " ORDER BY table_name";
        return strSQL;
    }
     
    public static void writeHTMLhead(String fileName) throws IOException  {  
       BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
       out.write("<html>\n");
       out.write("<head>\n");
       out.write("<style>\n");
       out.write("P { page-break-after: always }\n");
       out.write("</style>\n");
       out.write("</head>\n");
       out.write("<body>\n");
       out.close();  
    }
    
     public static void writeHTMLtail(String fileName) throws IOException  {  
       BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
       out.write("</body>\n");
       out.write("</html>\n");
       out.close();  
    }
    
    public static String getH2String(String tableName){
        String quote  = "\"";
        return "<P><BR><H2><A name=" + quote + tableName + quote + ">" + tableName  + "</A></H2>";
    }
    
    public static String startHTMLtable(){
        String quote = "\"";
        return "<table BORDER COLS=6 WIDTH=" + quote  + "100%" + quote + "\n<tbody>\n";
    }
    
    public static String stopHTMLtable() {
        return "</table>\n";
    }
    
    public static String startHTMLrow () {
        return "<TR>\n";
    }
    
    public static String stoptHTMLrow () {
        return "</TR>\n";
    }
    
    public static String makeHTMLcell (String cellValue) {
        return "<TD>" + cellValue + "</TD>\n";
    }
/*    
    public static
*/ 
}

