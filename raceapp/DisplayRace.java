import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class DisplayRace extends JFrame {
    private boolean DEBUG = false;
    Object [] [] data = new Object [20] [8];   
    String[] columnNames = {"Position","Sailor","Boat Number","Boat Type","Elapsed","Rating",
    						"Corrected","Points"};
   	JTable table = new JTable(data, columnNames);
 
    public DisplayRace(int raceNum) {
    //    super();        
        try {
        	int i = 0;
        	
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
    		Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/sailrace");
    		
    		String query = "SELECT * FROM race_detail WHERE raceno = ";
			query = query + raceNum + " ORDER BY corrected";
			
			Statement stmt = conn.createStatement ();
        	ResultSet rset = stmt.executeQuery (query);
        	while (rset.next ()) {
        		data [i] [0] = rset.getString(2);
        		data [i] [1] = rset.getString(3);
        		data [i] [2] = rset.getString(4);
        		data [i] [3] = rset.getString(5);
        		data [i] [4] = rset.getString(7);
        		data [i] [5] = rset.getString(6);
        		data [i] [6] = rset.getString(8);
        		data [i] [7] = rset.getString(9);
        		i++;
        	}
    
 
        	final JTable table = new JTable(data, columnNames);
        	table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        	if (DEBUG) {
            	table.addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent e) {
                    	printDebugData(table);
               		}
            	});
        	}

//  Create the scroll pane and add the table to it. 
        	JScrollPane scrollPane = new JScrollPane(table);

//Add the scroll pane to this window.
        	getContentPane().add(scrollPane, BorderLayout.CENTER);

	    	this.setSize(700, 360);
			this.setTitle("Race #" + raceNum + " Results");
			this.setVisible(true);

       
    	}
    	catch (SQLException e) {
    		System.out.println(e);
    	}
    	catch (ClassNotFoundException f){
    	}
    	catch (InstantiationException ie) {
    	}
    	catch (IllegalAccessException a) {
    	}
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    public static void main(String[] args) { 	
        DisplayRace frame = new DisplayRace(16);    
    }
    
  
}
