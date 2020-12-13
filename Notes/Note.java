import java.sql.*;
import java.lang.*;

public class Note {	
	String custID;           
	String custName;
	String custAddress1;
	String custAddress2;
	String custCity;
	String custProvince;
	String custCountry;
	String custPostalCode;
	
	/**
	 * Method Customer
	 *
	 *
	 */
	public Note() {
	
	}

	/**
	 * Method main
	 *
	 *
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		// TODO: Add your code here
	}
	
	 public void addNote(Connection bConn,String pSubject,String pText){

//        java.util.Date d = new java.util.Date(); 
        java.lang.Date d = new java.lang.Date();                        
    	String insCmd = "INSERT INTO notes";	
		insCmd = insCmd + "(timestamp,subject,text)"; 
		insCmd = insCmd + "VALUES("; 
		insCmd = insCmd + "'" + d + "'" + ",";   
		insCmd = insCmd + "'" + pSubject + "'" + ",";   
		insCmd = insCmd + "'" + pText + "'"; 
		insCmd = insCmd +")";
		
//		System.out.println(insCmd);
		try {
			Statement stmt = bConn.createStatement ();
			stmt.executeUpdate(insCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    }
    
    public void updateCustomer(Connection bConn,String bCustid,String bCustName,
                                 String bAddress1,String bAddress2, String bCity, 
                                 String bProvince,String bCountry,String bPostalCode) {
    	String updCmd = "UPDATE customers SET";
    	updCmd = updCmd + " cust_name = '" + bCustName + "'";
    	updCmd = updCmd + ",address1  = '" + bAddress1 + "'";
    	updCmd = updCmd + ",address2  = '" + bAddress2 + "'";
    	updCmd = updCmd + ",city  = '" + bCity + "'";
    	updCmd = updCmd + ",province  = '" + bProvince + "'";
    	updCmd = updCmd + ",country  = '" + bCountry + "'";
    	updCmd = updCmd + ",postal_code  = '" + bPostalCode + "'";
    	updCmd = updCmd + " WHERE custid = '" + bCustid + "'";
//    	System.out.println(updCmd);
    	try {
			Statement stmt = bConn.createStatement ();
			stmt.executeUpdate(updCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    }
    
    	
    public void deleteCustomer(Connection bConn,String bCustid) {
    	String delCmd = "DELETE FROM customers WHERE custid = '" + bCustid + "'";
    	try {
			Statement stmt = bConn.createStatement ();
			stmt.executeUpdate(delCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
   
    	
    }	
}
