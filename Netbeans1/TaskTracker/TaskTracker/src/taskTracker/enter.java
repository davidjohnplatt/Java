/*
 * enter.java
 *
 * Created on December 1, 2006, 9:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package taskTracker;

/**
 *
 * @author David
 */
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;  
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.sql.*;


public class enter extends javax.swing.JApplet {

    /** Creates a new instance of enter */
    public enter() {
        Image im;
        Font f;
        String nm;
/*
      Button b1,b2;
      TextField tf;
      b1=new Button("Button 1");
      b2=new Button("Button 2");
      tf = new TextField(50);
  
      b2.setEnabled(false);
*/      

      

    }
      public void init() {
      setBackground(Color.pink);
  //    nm = getParameter("aname");
 //     im = getImage(getDocumentBase(),"Solect.gif");
  //    f = new Font("Dialog",Font.BOLD,40);
   }

      public void paint (Graphics g) {
       g.setColor(Color.red);
 //      g.drawString(nm,100,100);

//       g.drawImage(im,10,10,this);

       g.setColor(new Color(0,0,255));
//       g.setFont(f);
       g.drawString("Jessica",200,200);
   }


    
}
