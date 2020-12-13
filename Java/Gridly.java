import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class Gridly extends Applet implements ActionListener
{
      Button b1,b2;
      TextField tf;
      public void init()
      {   setLayout(new GridLayout(2,1));
	      b1=new Button("Button 1");
	      b2=new Button("Button 2");
	      tf = new TextField(50);
	      b2.setEnabled(false);
	      b1.addActionListener(this);
	      add(b1);
	      b2.addActionListener(this);
		  add(b2);
	      add(tf);
	      validate();
	   }

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Button 1"))
		{
			b2.setEnabled(true);
			b1.setEnabled(false);
	    }

	    if(e.getActionCommand().equals("Button 2"))
		{
				b1.setEnabled(true);
				b2.setEnabled(false);
	    }

	    tf.setText(e.getActionCommand()+" "+"is clicked"+"\n");
	}

}

