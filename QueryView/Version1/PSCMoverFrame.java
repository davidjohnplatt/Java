import java.awt.*;
import java.awt.event.*;

/**
 * Sample application using Frame.
 *
 * @author 
 * @version 1.00 04/02/02
 */
public class PSCMoverFrame extends Frame {
    
    /**
     * The constructor.
     */  
     public PSCMoverFrame() {
     	GridLayout gridLayout1 = new GridLayout(5,10);
        this.setLayout(gridLayout1);        
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu();
        MenuItem menuFileExit = new MenuItem();
        
        menuFile.setLabel("File");
        menuFileExit.setLabel("Exit");
        
        // Add action listener.for the menu button
        menuFileExit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PSCMoverFrame.this.windowClosed();
                }
            }
        ); 
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        /*
        Choice choice2 = new Choice();
        choice2.setBounds(new Rectangle(12, 52, 109, 21));
        this.add(choice2, null);
        */
  		Button TruncateButton = new Button();
  		TextField UserField = new TextField();
  		UserField.setBounds(new Rectangle(10, 10, 10, 10));
    	UserField.setText("UserField");
    	this.add(UserField, null);
  		TextField PasswdField = new TextField();
  		TextField InstanceField = new TextField();
        
        setTitle("PSCMover");
        setMenuBar(menuBar);
        setSize(new Dimension(400, 400));
        
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    PSCMoverFrame.this.windowClosed();
                }
            }
        );  
    }
    
    
    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() {
    	
    	// TODO: Check if it is save to close the application
    	
        // Exit application.
        System.exit(0);
    }
    

}
