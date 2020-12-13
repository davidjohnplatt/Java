import java.awt.*;
import java.applet.Applet;

public class MyApplet extends Applet {
   Image im;
   Font f;
   String nm;

   public void init() {
      setBackground(Color.pink);
      nm = getParameter("aname");
      im = getImage(getDocumentBase(),"Solect.gif");
      f = new Font("Dialog",Font.BOLD,40);
   }

   public void paint (Graphics g) {
       g.setColor(Color.red);
       g.drawString(nm,100,100);

       g.drawImage(im,10,10,this);

       g.setColor(new Color(0,0,255));
       g.setFont(f);
       g.drawString("Jessica",200,200);
   }
}
