import java.awt.*;
import java.applet.Applet;
import java.util.Hashtable;
import java.util.Enumeration;

public class PhoneList extends Applet { 

  List l1;
  TextField tf;
  Font f;
  static String s1;

  public void init() {

    setBackground(Color.pink);
    f = new Font("Dialog",Font.BOLD,20);
//  Hashtable diskHolder = new Hashtable(13,0.5f);

    l1 = new List(3,false);   
    tf = new TextField(25);

    add(l1);
    add(tf);
     }

   public void paint (Graphics g) {

    g.setColor(new Color(0,0,255));
    g.setFont(f);
    g.drawString("PhoneList",200,17);
    l1.add ("Adam     Desk 2441-37164");
    l1.add ("Bobby    Desk 2441-37197");
    l1.add ("Bobby    Cell 44(7764) 538 356");
    l1.add ("Irene    Cell 416-525-9105");
    l1.add ("John     Desk 416-216-6651");
    l1.add ("John     Cell 416-971-0165");    
    l1.add ("Matt     Desk 2441-37105");
    l1.add ("Matt     Cell 44(7764) 538 334");
    l1.addItemListener(new Lis(this));
   }


}

class Lis implements ItemListener {

  PhoneList ap1;

  Lis(PhoneList ap) {
   ap1 = ap;
  }
  
  public void itemStateChanged(ItemEvent e) {
    ap1.tf.setText(ap1.l1.getSelectedItem());
    }

}


