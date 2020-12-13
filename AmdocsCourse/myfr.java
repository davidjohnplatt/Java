import java.io.*;
import java.awt.*;

class myfr extends Frame {
	  //Filedialog fd;
	  mycanvas c1;

	myfr()
	{
		super("MYFRAME");
		setLayout(new FlowLayout());
	c1 = new mycanvas();
	add(c1);
  }

public static void main(String ar[])
  {

         myfr f = new myfr();
         f.setVisible(true);

		 }
	 }


class mycanvas extends Canvas
{  Image im;

	mycanvas()
	{     setSize(100,100);
	       setBackground(Color.pink);
		 im = Toolkit.getDefaultToolkit().getImage("Modem.gif");
	 }

	 public void paint(Graphics g)
	 {
		 g.drawImage(im,0,0,this);
	 }

 }