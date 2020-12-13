import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;


public class MainMenu extends JFrame implements ActionListener{
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu;
	JMenuItem exitMenuItem;
	JMenu maintainMenu;
	JMenuItem customerMenuItem;
	JMenuItem raceMenuItem;
	JMenuItem racedMenuItem;
	JMenuItem raceEditMenuItem;
	JMenuItem sailorMenuItem;
	Image image;
	
	
	public MainMenu(){
		
	
	    ImagePanel ip = new ImagePanel();
	    Container contentPane = getContentPane();
	    contentPane.add(ip);
		
		setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);	
		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(this);
		
		maintainMenu = new JMenu("Maintain");
		menuBar.add(maintainMenu);
		
		customerMenuItem = new JMenuItem("Customers");
		maintainMenu.add(customerMenuItem);
		customerMenuItem.addActionListener(this);

		maintainMenu = new JMenu("Help");
		menuBar.add(maintainMenu);

//      Viewer v = new Viewer("titlePage001.jpg");



//	    repaint();
		this.setTitle("Invoices");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setVisible(true);

	}
	
	public static void main (String [] args) {
		MainMenu m = new MainMenu();
	}
	
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	if (source == exitMenuItem){
    		System.exit(0);
    	} else if (source == customerMenuItem){ 		
            CustomerMenu b = new CustomerMenu();
             
        } /*else if (source == raceMenuItem) {
            Race r = new Race();
    	} else if (source == racedMenuItem) {
            masterSail m = new masterSail();
        } else if (source == sailorMenuItem) {
            Sailor s = new Sailor();  
    	} else if (source == raceEditMenuItem) {
    		RaceEditDetail r = new RaceEditDetail();
    	}*/
	}
	


    class ImagePanel extends JPanel {
    	
    	public ImagePanel(){
    		String fileName = "titlePage001.jpg";
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			image = toolkit.getImage(fileName);
			MediaTracker m = new MediaTracker(this);
			m.addImage(image,0);
			
			try {
				m.waitForID(0);
			}
			catch (InterruptedException e) {
				System.out.println("Error loading Image");
			}
    	}
    	
    	public void paint (Graphics g) {
			super.paint(g);
			g.drawImage(image,0,0,null);
	}
    }
			
}
