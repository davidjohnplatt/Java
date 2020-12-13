import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class AnonExample extends Applet
{
      Button b1;
      TextField tf;
      public void init()
      {
	      b1=new Button("Button 1");
		      tf = new TextField(50);

	      b1.addActionListener( new ActionListener ()
                                 {  public void actionPerformed(ActionEvent e)
                                     {  tf.setText("button clicked"+"\n");
							}
                                   }
                                   );
	      add(b1);

	      add(tf);
	      validate();
	   }



}

