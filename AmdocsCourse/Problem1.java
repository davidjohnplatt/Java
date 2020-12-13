import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class Problem1 extends Applet { // implements ActionListener {
      Button b1,b2;
      TextField tf;

      public void init()
      {
	      b1=new Button("Button 1");
	      b2=new Button("Button 2");
	      tf = new TextField(50);
	      b2.setEnabled(false);
	      b1.addActionListener(new Lis(this));
	      add(b1);
	      b2.addActionListener(new Lis(this));
              add(b2);
	      add(tf);
	      validate();
      }

      public void paint (Graphics g) {}

}

class Lis implements ActionListener {

  Problem1 ap1;

  Lis(Problem1 ap) {
   ap1 = ap;
  }
  
  public void actionPerformed(ActionEvent e) {

    if(e.getActionCommand().equals("Button 1")) {
       ap1.b2.setEnabled(true);
       ap1.b1.setEnabled(false);
       }

    if(e.getActionCommand().equals("Button 2")) {
       ap1.b1.setEnabled(true);
       ap1.b2.setEnabled(false);
       }

    ap1.tf.setText(e.getActionCommand()+" "+"is clicked"+"\n");
    }

}

