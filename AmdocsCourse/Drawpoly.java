import java.awt.*;
import java.applet.Applet;

public class Drawpoly extends Applet
{
      public void paint(Graphics g)
      {
           int x[]={10,30,50,70,90,110,130};
           int y[]={120,140,160,180,120,140,160};
           Polygon p = new Polygon(x,y,6);
           g.setColor(Color.red);
           g.drawPolygon(p);
      }
}

