import java.sql.*;

public class Customer {	
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
	public Customer() {
	
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
	
	 public void addCustomer(Connection bConn,String bCustid,String bCustName,
                                 String bAddress1,String bAddress2, String bCity, 
                                 String bProvince,String bCountry,String bPostalCode) {
    	String insCmd = "INSERT INTO customers";	
		insCmd = insCmd + "(custid,cust_name,address1,address2,city,province,country,postal_code)"; 
		insCmd = insCmd + "VALUES("; 
		insCmd = insCmd + "'" + bCustid + "'" + ",";
		insCmd = insCmd + "'" + bCustName + "'" + ","; 
		insCmd = insCmd + "'" + bAddress1 + "'" + ","; 
		insCmd = insCmd + "'" + bAddress2 + "'" + ",";
		insCmd = insCmd + "'" + bCity + "'" + ","; 
		insCmd = insCmd + "'" + bProvince + "'" + ",";
		insCmd = insCmd + "'" + bCountry + "'" + ",";   
		insCmd = insCmd + "'" + bPostalCode + "'"; 
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
