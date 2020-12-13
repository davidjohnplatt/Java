import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class ErrorFrame extends JFrame {
	
	
	public ErrorFrame() {
	}
	
	public ErrorFrame(String inStr) {
		super();
		JPanel panel = new JPanel();
		Container contentPane = this.getContentPane();
		JLabel J = new JLabel(inStr);
		panel.add(J);
		contentPane.add(panel);
		this.setSize(inStr.length() * 8, 75);
		this.setTitle("Error");
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

}//  ErrorFrame