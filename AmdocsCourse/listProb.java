import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class listProb extends Applet { 

  List l1;
  TextField tf;

  public void init() {
      
    l1 = new List(2,false);
    l1.add ("Red");
    l1.add ("White");
    l1.add ("Blue");
    l1.add ("Yellow");
    l1.add ("Green");
    l1.addItemListener(new Lis(this));

    tf = new TextField(20);

    add(l1);
    add(tf);
  }

}

class Lis implements ItemListener {

  listProb ap1;

  Lis(listProb ap) {
   ap1 = ap;
  }
  
  public void itemStateChanged(ItemEvent e) {

    ap1.tf.setText(ap1.l1.getSelectedItem());
    }

}

