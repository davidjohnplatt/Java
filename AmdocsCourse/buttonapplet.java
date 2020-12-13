import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class Problem1 extends Applet implements ActionListner
{
      Button b1,b2;
      TextField tf;
      b1=new Button("Button 1");
      b2=new Button("Button 2");
      tf = new TextField(50);
      b2.setEnabled(false);
      