/*
 * Problem1.java
 *
 * Created on December 1, 2006, 9:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Problem1;

/**
 *
 * @author David
 
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
public class Problem1 extends javax.swing.JApplet {
*/    
    /** Creates a new instance of Problem1 
    public Problem1() {
        Button b1,b2;
        TextField tf;
        b1=new Button("Button 1");
        b2=new Button("Button 2");
        tf = new TextField(50);
        b2.setEnabled(false);
    }
   */
import java.awt.*;
import java.applet.*;
// import an extra class for the ActionListener
import java.awt.event.*;

// Tells the applet you will be using the ActionListener methods.

public class ActionExample extends Applet implements ActionListener
{

     Button okButton;
     Button wrongButton;
     TextField nameField;
     CheckboxGroup radioGroup;
     Checkbox radio1;
     Checkbox radio2;
     Checkbox radio3;

     public void init() 
     {
  // Now we will use the FlowLayout
          setLayout(new FlowLayout());
          okButton = new Button("Action!");
          wrongButton = new Button("Don't click!");
          nameField = new TextField("Type here Something",35);
          radioGroup = new CheckboxGroup();
          radio1 = new Checkbox("Red", radioGroup,false);
          radio2 = new Checkbox("Blue", radioGroup,true);
          radio3 = new Checkbox("Green", radioGroup,false);
          add(okButton);
          add(wrongButton);
          add(nameField);
          add(radio1);
          add(radio2);
          add(radio3);

  // Attach actions to the components
          okButton.addActionListener(this);
          wrongButton.addActionListener(this);
         }

 // Here we will show the results of our actions
         public void paint(Graphics g)
         {
  // If the radio1 box is selected then radio1.getState() will
  // return true and this will execute
          if (radio1.getState()) g.setColor(Color.red);
  // If it was not red we'll try if it is blue
        else if (radio2.getState()) g.setColor(Color.blue);
  // Since always one radiobutton must be selected it must be green
          else g.setColor(Color.green);

  // Now that the color is set you can get the text out the TextField
  // like this
          g.drawString(nameField.getText(),20,100);
     }

 // When the button is clicked this method will get automatically called
 // This is where you specify all actions.

        public void actionPerformed(ActionEvent evt) 
         {
  // Here we will ask what component called this method
              if (evt.getSource() == okButton) 
   // So it was the okButton, then let's perform his actions
   // Let the applet perform Paint again.
   // That will cause the aplet to get the text out of the textField
   // again and show it.
                   repaint();

  // Actions of the wrongButton
          else if (evt.getSource() == wrongButton) 
          {

   // Change the text on the button for fun
               wrongButton.setLabel("Not here!");
   // Changes the text in the TextField
               nameField.setText("That was the wrong button!");
   // Lets the applet show that message.
               repaint();
          }
     } 

}
  
}
