import java.awt.*;

public class report extends java.applet.Applet {

Flowlayout layout;

  public void init() {
    layout = new Flowlayout();
    setLayout(layout);
    add ("1", new Button("New Boat");
    add ("2", new Button("Save");
    add ("3", new Button("Exit");
  }

  public boolean keyDown(Event e, int key) {
    layout.next(this);
     return true;
  }
}

