import java.sql.*;

public class Invoice {	
	String custID;           
	String invoiceID;
	String invoiceDate;
	String invoiceStatus;

	
	/**
	 * Method Customer
	 *
	 *
	 */
	public Invoice() {
	
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
	
	 public void addInvoice(Connection bConn,String bCustid,String bInvoiceID,
                                 String bInvoiceDate,String bInvoiceStatus) {
    	String insCmd = "INSERT INTO invoice_master";	
		insCmd = insCmd + "(cust_id,invoice_id,invoice_date,invoice_status)"; 
		insCmd = insCmd + "VALUES("; 
		insCmd = insCmd + "'" + bCustid + "'" + ",";
		insCmd = insCmd + "'" + bInvoiceID + "'" + ","; 
		insCmd = insCmd + "'" + bInvoiceDate + "'" + ","; 
		insCmd = insCmd + "'" + bInvoiceStatus + "'";
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
    
    public void updateInvoice(Connection bConn,String bCustid,String bInvoiceID,
                                 String bInvoiceDate,String bInvoiceStatus) {
    	String updCmd = "UPDATE invoice_master SET";
    	updCmd = updCmd + " invoice_date  = '" + bInvoiceDate + "'";
    	updCmd = updCmd + ",invoice_status  = '" + bInvoiceStatus + "'";
    	updCmd = updCmd + " WHERE cust_id = '" + bCustid + "'";
    	updCmd = updCmd + " AND invoice_id = '" + bInvoiceID + "'";
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
    
    	
    public void deleteInvoice(Connection bConn,String bCustid, String bInvoiceID) {
    	String delCmd = "DELETE FROM invoice_master WHERE cust_id = '" + bCustid + "' AND invoice_id = '" + bInvoiceID + "'";
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