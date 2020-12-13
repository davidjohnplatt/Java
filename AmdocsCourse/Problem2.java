import java.awt.*;
import java.awt.event.*;

class Problem2 extends Frame
{
   TextField tf;
   List l;
   Problem2()
   {
       setLayout(new FlowLayout());
       tf=new TextField(20);
       add(tf);
       l= new List(10);
       add(l);
       tf.addActionListener(new MyLis(this));
       validate();
   }

   public static void main(String args[])
   {
       Problem2 prob = new Problem2();
       prob.pack();
       prob.show();
   }
}

class MyLis implements ActionListener
{
    Problem2 prob;
    MyLis(Problem2 pr)
    {
       prob = pr;
    }
    public void actionPerformed(ActionEvent te)
    {
       prob.l.addItem(prob.tf.getText());
       prob.tf.setText("");
    }
}


