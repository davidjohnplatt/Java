import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import boat;
import result;
import scoreSheet;
import rating;
import getKeyboardData;
import myFrame;


public class main {
  public static void main (String [] args){
    int j = 0;

    String s;
    String text = "";

// figure out hoe porstmouth formula works

    scoreSheet ss = new scoreSheet();
    Vector boats = new Vector();
    Iterator itor;
    Vector results = new Vector();
    try {
      BufferedReader r = new BufferedReader(new FileReader("boat.dat"));
      while( (s = r.readLine())!= null ) {
        boats.addElement( new boat(s));
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
 //   result pot = new result(1,"fred","W7218","Wayfarer",3600,1,9,104.5);
//    pot.print();

 //   boat laserboat = new boat();
   // laserboat._boatClass = "Laser";
 //   ss.print();
    System.out.println(ss.getScore(3,5));


    rating r = new rating();
    double boatRate = r.getRating("Laser");

    getKeyboardData k = new getKeyboardData();
    String boatNum = "";
/*
    while ( boatNum != "Q" ) {
      boatNum = k.getString("Boat Number");
      String sailor = k.getString("Sailor");
      int hours = k.getInt("Elapsed Hours");
      int minutes = k.getInt("Elapsed Minutes");
      int seconds = k.getInt("Elapsed Seconds");
      int et = hours * 3600 + minutes * 60 + seconds;

      System.out.println( et );
    }
*/
/*
    itor = boats.iterator();
    while( itor.hasNext() ) {
      boat item = (boat._boatNumber)itor.next();
      item.print();
     
      if( item.equals( boatNumber ) ) {
        System.out.println( item._boatRating );
      }
    }
 */
    myFrame raceFrame = new myFrame();
    raceFrame.setVisible(true);
  }//main
}

  class Listen extends WindowAdapter {

    public void windowClosing(WindowEvent e){
       e.getWindow().dispose();
       System.exit(0);
    } 
  }




