import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class frm3 extends Frame
{
      MenuBar mb1;
      Menu m1;
      MenuItem mi1;
      FileDialog fd;
      TextField tf;
     public frm3()
     {   super("MyFrameExample");
         setLayout(new FlowLayout());
		addWindowListener(new Lis());

     mb1= new MenuBar();
     m1= new Menu("FILE");
     mi1= new MenuItem("Open");
     mi1.addActionListener(new Lis1(this));
     m1.add(mi1);
     m1.add (new MenuItem("save"));
     mb1.add(m1);
     m1= new Menu("HELP");
     mi1= new MenuItem("About");
     m1.add(mi1);
     m1.add (new MenuItem("Index"));
     mb1.add(m1);

     m1= new Menu("EDIT");
     mi1= new MenuItem("Insert");
     mb1.add(m1);
     m1.add(mi1);

     setMenuBar(mb1);
     fd = new FileDialog(this,"ffff",FileDialog.LOAD);
     tf = new TextField(20);
     add(tf);

	 }

  public static void main(String [] ar)
  {
	  frm3 obj = new frm3();
	  obj.show();
  }

}

class Lis extends WindowAdapter
{

	public void windowClosing(WindowEvent e)
	 {
		e.getWindow().dispose();
		System.exit(0);
	 }


 }


class Lis1 implements ActionListener
{
	 frm3 apmem;

	public Lis1( frm3 ap)
	{
		apmem =ap;
	}



	public void actionPerformed(ActionEvent e)
	 {
		 apmem.fd.setVisible(true);
		 apmem.tf.setText(apmem.fd.getFile());
	 }


 }

