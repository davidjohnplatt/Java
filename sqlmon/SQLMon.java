
// Copyright (c) 2001 Solect Technologies
package sqlmon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


/**
 * sqlmon.SQLMon
 * <P>
 * @author Solect
 */
public class SQLMon {
//  static Connection     connection = null;
  static Frame frame;
  /**
   * Constructor
   */
  public SQLMon() {
    frame = new monFrame();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setVisible(true);
  }

  /**
   * main
   * @param args
   */
  public static void main(String[] args){
    try  {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
     new SQLMon();

  }
//  connection.close();


}

