/**
 * @(#)OracleJob.java
 *
 * Sample Applet application
 *
 * @author 
 * @version 1.00 04/01/30
 */
import java.applet.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import oracle.jdbc.driver.*;

public class OracleJob extends JApplet {
	
	private Container con = getContentPane();
	
	public void init() {  
	  String driver="oracle.jdbc.driver.OracleDriver";  
	  String url="jdbc:oracle:thin:privuser/privuser@gubber:1521:gubberdb";  
	  String user="report";  
	  String pass="report";  
	  String Sel = "";  
	  String From = "";  
	  String Where =  " ";   
	  String SelStr = "";  
	  String err = "";  
	  int oldlev=0, 
	  curlev = 0; 
	  
	  try{ DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());   
		  err ="1";   
		  Class.forName (driver);   
		  err ="2";
		  Connection con = DriverManager.getConnection(url);   
		  err ="3";   
		  java.sql.Statement s = con.createStatement();
	  }
	  catch(Exception e){
		  System.out.println(e+"\n"+err);
	  }	
	}
	 
 	public void paint(Graphics g) {
		g.drawString("Welcome to Java!!", 50, 60 );
	}
	
}

