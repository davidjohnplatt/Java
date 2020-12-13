import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class  getInt {

  public void getInt() {}

  public int getInt (String inStr) {
    int x = 0;
    String text = "";
    System.out.print("Enter " + inStr + ": ");
    try {
      InputStreamReader converter = new InputStreamReader(System.in);
      BufferedReader in = new BufferedReader(converter);
      text = in.readLine();
      x = NumberFormat.getInstance().parse(text).intValue();
      in.close();
    }
    catch ( IOException e ) { }
    catch ( ParseException pe ) { }
    return x;
  }

}//getInt