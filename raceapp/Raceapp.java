/*
 * @(#)Raceapp.java 1.0 02/06/25
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
package raceapp;

import java.awt.*;
import java.awt.event.*;

class Raceapp extends Frame {
	
	public Raceapp() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public static void main(String args[]) {
		System.out.println("Starting Raceapp...");
		Raceapp mainFrame = new Raceapp();
		mainFrame.setSize(400, 400);
		mainFrame.setTitle("Raceapp");
		mainFrame.setVisible(true);
	}
}
