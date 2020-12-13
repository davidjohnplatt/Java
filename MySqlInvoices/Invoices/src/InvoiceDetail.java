import java.sql.*;

public class InvoiceDetail {	      
	String invoiceID;
	String invoiceLineID
	String serviceDate;
	String description;
	Float price;
	Float Quantity;
	Float discount;
	

	public InvoiceDetail() {	
	}
	
	
    public void addInvoiceDetail(Connection bConn,String bInvoiceID,Int bInvoiceLineID,String bServicedate,
                                 String bDescription,Float bPrice, Float bQuantity,
                                 Float bDiscount
                                 ) {
    	String insCmd = "INSERT INTO invoice_detail";	
		insCmd = insCmd + "(invoice_id,invoice_lind_id,service_date,description,price,quantity,discount)"; 
		insCmd = insCmd + "VALUES("; 
		insCmd = insCmd + "'" + bInvoiceID + "'" + ","; 
		insCmd = insCmd + "'" + bInvoiceLineID + "'" + ","; 
		insCmd = insCmd + "'" + bServicedate + "'";
		insCmd = insCmd + "'" + bDescription + "'";
		insCmd = insCmd + "'" + bPrice + "'";
		insCmd = insCmd + "'" + bQuantity + "'";
		insCmd = insCmd + "'" + bDiscount + "'";
		insCmd = insCmd +")";
		
		System.out.println(insCmd);
		try {
			Statement stmt = bConn.createStatement ();
			stmt.executeUpdate(insCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		ErrorFrame ef = new ErrorFrame(errString);
    	}
    }
    
    public void updateInvoice(Connection bConn,String bInvoiceID,int bInvoiceLineID,String bServicedate,
                                 String bDescription,Float bPrice, Float bQuantity,
                                 Float bDiscount
                                 ) {
    	String updCmd = "UPDATE invoice_master SET";
    	updCmd = updCmd + " invoice_id  = '" + bInvoiceDate + "'";
    	updCmd = updCmd + " service_date  = '" + bInvoiceStatus + "'";
   		updCmd = updCmd + " description  = '" + bInvoiceStatus + "'";
		updCmd = updCmd + " price  = '" + bInvoiceStatus + "'";
		updCmd = updCmd + " quantity  = '" + bInvoiceStatus + "'";
		updCmd = updCmd + " discount  = '" + bInvoiceStatus + "'";
    	updCmd = updCmd + " WHERE invoice_id = '" + bInvoiceID + "'";
    	updCmd = updCmd + " AND invoice_id = '" + bInvoiceLineID + "'";
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
    
    	
    public void deleteInvoice(Connection bConn,String bInvoiceID, String bInvoiceLineID) {
    	String delCmd = "DELETE FROM invoice_master WHERE invoiceID = '" + bCustid + "' AND invoice_line_id = '" + bInvoiceLine ID + "'";
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