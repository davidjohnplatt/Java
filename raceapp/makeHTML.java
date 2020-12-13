import java.io.*;
import java.util.*;
import java.sql.*;

public class makeHTML {
  static int raceNum;
  
  	public makeHTML(int inInt){
  	 	raceNum = inInt;
  	    String s;
    	String t;
    	String Sailor;
    	String sailNumber;
        String boatType;
        String totalSeconds;
        String rating;
        String corrected;
        String points;
    	int position = 0;

    	String outFile = "/tmp//RaceResults" + java.lang.String.valueOf(raceNum) + ".htm";
        try {
     		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
    	
    		Connection conn;
     	
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    		
    		String queryhead = "SELECT * FROM races WHERE raceno = " + raceNum; 
    		Statement stmt2 = conn.createStatement ();
        	ResultSet rset2 = stmt2.executeQuery (queryhead);
        	rset2.next(); 
      		w.println(raceHeader(raceNum,rset2.getDate(2),rset2.getString(3)));
      		
      		w.println(startTable());
      		w.println(makeBoldCell("Position"));
      		w.println(makeBoldCell("Skipper"));
      		w.println(makeBoldCell("Boat"));
      		w.println(makeBoldCell("Boat Number"));
   
      		w.println(makeBoldCell("Elapsed Time"));
      		w.println(makeBoldCell("Rating"));
      		w.println(makeBoldCell("Corrected Time"));
      		
      		w.println(makeBoldCell("Points"));
 
        	String query = "SELECT * FROM race_detail WHERE raceno = " + raceNum; 
        	query = query + " ORDER BY corrected";    
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
        		w.println(startTableRow());
        		w.println(makeCell(rset.getString(2)));  //position
             	w.println(makeCell(rset.getString(3)));  //skipper
             	w.println(makeCell(rset.getString(5)));  //boatType
             	w.println(makeCell(rset.getString(4)));  //boatNumber
             	w.println(makeCell(rset.getString(7)));  //elapsed
             	w.println(makeCell(rset.getString(6)));  //rating
            	w.println(makeCell(rset.getString(8)));  //corrected
             	
             	w.println(makeCell(rset.getString(9)));  //points
             	w.println(stopTableRow());
       		}
       	
 			w.println(stopTable());
 			w.close();
  
       	
       		
        }
    	catch (IOException e) {
      		e.printStackTrace();
      	}
    	catch (SQLException e) {
    		System.out.println("Error - SQL Statement");
    	}
    	catch (ClassNotFoundException f){
    	}
    	catch (InstantiationException i) {
    	}
    	catch (IllegalAccessException a) {
    	}
    
  
      	
//      w.println(stopTable());

  //    w.close();
      
  }
  
  public static void main(String [] args) {
  	raceNum = 21;
    makeHTML m = new makeHTML(raceNum);
   
  } //main
  
  public static String makeCell(String inStr){
    return ("<td>" + inStr + "</td>");
  }
  
  public static String makeBoldCell(String inStr){
    return ("<td><b>" + inStr + "</b></td>");
  }
  
  public static String startTableRow(){
    return ("<tr>");
  }
  
  public static String stopTableRow(){
    return ("</tr>");
  }
  
  public static String startTable(){
    return ("<table BORDER COLS=6 WIDTH=\"100%\" >");
  }
  
   public static String stopTable(){
    return ("</table>");
  }
  
  public static String raceHeader(int raceno,java.sql.Date rDate,String nameString) {
  	String p = "<p><b><font size=+1>Race # ";
  	p = p + java.lang.String.valueOf(raceno) + "  ";
    p = p + rDate + "&nbsp;</font>Committee Boat: </b>";
    p = p + nameString;
  	return p;
  }
  
}