import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class frameProb extends Frame { 

  List l1;
  TextField tf;

  public frameProb() {
      
    setLayout(new FlowLayout());
    l1 = new List(2,false);
    l1.add ("Red");
    l1.add ("White");
    l1.add ("Blue");
    l1.add ("Yellow");
    l1.add ("Green");

    tf = new TextField(20);
    add(l1);
    add(tf);
  }


  public static void main(String args[]) {

    frameProb obj = new frameProb();
    obj.setVisible(true);

  }
}

class Lis extends WindowAdapter {

  frameProb ap1;

  Lis(frameProb ap) {
   ap1 = ap;
  }
  
  public void frameClicked(MouseEvent e) {

    ap1.tf.setText("x= "+e.getX()+" Y= "+e.getY());
    }

}

