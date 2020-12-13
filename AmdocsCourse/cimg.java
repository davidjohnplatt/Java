import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class cimg extends Applet
{
	 Image im, im1, im2,im3;
	 ImageFilter cp;
	 ImageProducer pr;



	 public void init()
	 {

   	im = getImage(getDocumentBase(),"t1.jpg");
	    cp = new CropImageFilter(100,10,50,50);
	    pr = new FilteredImageSource(im.getSource(),cp);
	    im1= createImage(pr);
	    im2= im1.getScaledInstance(200,200,100);
	    im3= im.getScaledInstance(100,100,100);


	}

	 public void paint(Graphics g)
	 {
		 g.drawImage( im,50,50,this);
		 g.drawImage( im1,250,50,this);
		 g.drawImage( im2,250,150,this);
		 g.drawImage( im3,150,250,this);
	 }
}
