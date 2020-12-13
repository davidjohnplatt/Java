import java.lang.*;
import java.io.*;
import java.util.*;

public class rating {
  public Hashtable ratemap = new Hashtable();

  public rating(){
    String s;
    try {
      BufferedReader r = new BufferedReader(new FileReader("rating.txt"));
      while( ( s = r.readLine())!= null ) {
        StringTokenizer st = new StringTokenizer(s,",");
        String boatClass = st.nextToken();
        String boatRating = st.nextToken();
        ratemap.put(boatClass,boatRating);
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  } //rating constructor

  void print() {
    Set keys = ratemap.keySet();
    Iterator keyIter = keys.iterator();
    while (keyIter.hasNext()) {
      System.out.println (keyIter.next());
    }
  }//print

  double getRating( String inStr ) {
    double hold = Double.parseDouble((String)ratemap.get(inStr));
    return hold;
  }//getRating
}//scoreSheet
