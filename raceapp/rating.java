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
  
  void getBoatList(String [] arrayStr) {
  	int i = 0;
    Set keys = ratemap.keySet();
    Iterator keyIter = keys.iterator();
    while (keyIter.hasNext()) {
      arrayStr [i] = keyIter.next().toString();
      i++;
    }
  }

  	double getRating( String inStr ) {
   	 	double hold = Double.parseDouble((String)ratemap.get(inStr));
    	return hold;
  	}//getRating
  
   	String getStringRating( String inStr ) {
      	String hold = (String)ratemap.get(inStr);
    	return hold;
  	} //getStringRating
}//rating
