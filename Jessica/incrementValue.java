import java.sql.*;

public class incrementValue {
  
  Statement statement;

  /**
   * Constructs a new instance.
   */
  public incrementValue() {
  }


 public static void main(String[] args){
 	
 	    incrementValue l = new incrementValue();
 
		try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/invoices");
    		int k = l.increment(conn,"personid");
    		System.out.println(k);
    	}
    	catch (SQLException e) {
    	}
    	catch (ClassNotFoundException f){
    		System.out.println(f);
    	}
    	catch (InstantiationException i) {
    	}
    	catch (IllegalAccessException a) {
    	}

  }
  
    public int increment(Connection pConn,String pValName) {
      int returnValue = -1;
      String selCmd = "SELECT value_value FROM master_values WHERE value_name = '" + pValName + "'";
      String updCmd = "UPDATE master_values SET value_value = value_value + 1 WHERE value_name =  '" + pValName + "'";
      try {
			Statement updt = pConn.createStatement ();
			updt.executeUpdate(updCmd);
		}
    	catch (SQLException e) {
    		String errString = e + "";
    		System.out.println(errString);
    	}

      
      try {
		Statement stmt = pConn.createStatement ();
       	ResultSet rset = stmt.executeQuery (selCmd);
       	while (rset.next ()) {
       	  returnValue = rset.getInt(1);	
    	}      	
      }
      catch (SQLException e) {
    	String errString = e + "";
        System.out.println(errString);
      }      
      return returnValue;

    }
}