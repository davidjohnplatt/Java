import java.awt.*;
import java.awt.event.*;

public class EventHandle
     implements MouseMotionListener,
                MouseListener {
  private TextField txtFld;

  public EventHandle (TextField t) {
	txtFld = t;
  }

  // These are MouseMotionListener events
  public void mouseDragged(MouseEvent e) {
    String s =  "Mouse dragging:  X = " + e.getX()
                + " Y = " + e.getY();
    txtFld.setText(s);
  }

  public void mouseEntered(MouseEvent e) {
    String s = "The mouse entered";
    txtFld.setText(s);
  }

  public void mouseExited(MouseEvent e) {
    String s = "The mouse has left the building";
    txtFld.setText(s);
  }

  // Unused MouseMotionListener method.
  // All methods of a listener must be present in the
  // class even if they are not used.
  public void mouseMoved(MouseEvent e) { }

  // Unused MouseListener methods.
  public void mousePressed(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
}
