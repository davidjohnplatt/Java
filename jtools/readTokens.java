import java.io.*;
import java.net.*;
import java.util.*;

class readTokens {

  public static void main (String[] args) {
   
    String nameURL = "http://10.16.81.57";
    String str;
    try {

      boolean reading;
      URL myURL = new URL(nameURL);
      BufferedReader r = new BufferedReader(new InputStreamReader(myURL.openStream()));
     
      while ((str = r.readLine()) != null) {
        System.out.print("\n#");
        StringTokenizer st = new StringTokenizer(str,"<>");
        while (st.hasMoreTokens()) {
          System.out.print(st.nextToken() + " ");
        }
      }
    }
    catch (IOException e){
        e.printStackTrace();
    }
  }
}
