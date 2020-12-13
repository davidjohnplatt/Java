import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;


public class dataCollect extends JFrame {
    private boolean DEBUG = true;
    Object [] [] data = new Object [20] [11];   
    String[] columnNames = {"Sailor","Boat Number","Boat Type","Hours","Minutes",
                            "Seconds","Time in Seconds","Rating","Corrected Time",
                            "Position","Points"};
   JTable table = new JTable(data, columnNames);
 
    public dataCollect(Object data [] []) {
        super();
 
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it. 
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this window.
        getContentPane().add(scrollPane, BorderLayout.CENTER);

	    this.setSize(700, 360);
		this.setTitle("Race Results");
		this.setVisible(true);
		
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
 //               System.exit(0);
            }
        });
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
 //       dataCollect frame = new dataCollect();    
    }
    
    public void addSailor(Object sailorName,int currentRow) {
        table.setValueAt(sailorName,currentRow,0);
    }
    
    public void addBoatNumber(Object bNum, int currentRow) {
    	table.setValueAt(bNum,currentRow,1);
    }
    
    public void addBoatType(Object bType, int currentRow) {
    	table.setValueAt(bType,currentRow,2);
    }
    
    public void addHours(Object bHours, int currentRow) {
    	table.setValueAt(bHours,currentRow,1);
    }
    	
    public void addMinutes(Object bMin, int currentRow) {
    	table.setValueAt(bMin,currentRow,1);
    }
    
    public void addSeconds(Object bSec, int currentRow) {
    	table.setValueAt(bSec,currentRow,1);
    }
    
    public void addRating(Object bRating, int currentRow) {
    	table.setValueAt(bRating,currentRow,7);
    }
}
