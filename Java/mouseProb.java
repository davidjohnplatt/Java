import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class mouseProb extends Applet { 

  List l1;
  TextField tf;

  public void init() {
      
    l1 = new List(2,false);
    l1.add ("Red");
    l1.add ("White");
    l1.add ("Blue");
    l1.add ("Yellow");
    l1.add ("Green");

    tf = new TextField(20);

    add(l1);
    add(tf);
    addMouseListener(new Lis(this));
  }

}

class Lis extends MouseAdapter {

  mouseProb ap1;

  Lis(mouseProb ap) {
   ap1 = ap;
  }
  
  public void mouseClicked(MouseEvent e) {

    ap1.tf.setText("x= "+e.getX()+" Y= "+e.getY());
    }

}

