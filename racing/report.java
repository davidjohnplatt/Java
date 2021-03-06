import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.URL;



public class report extends java.applet.Applet  {

  FlowLayout layout;
  java.awt.List list1;
  TextField textField1;
  TextField textField2;
  TextField textField3;
  
  public void init() {
    layout = new FlowLayout();
    setLayout(layout);
    String s;
   
    Vector boats = new Vector();
    Iterator itor;

    try {
      URL url = new URL(getCodeBase(),"boat.txt");
      BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
      while( (s = r.readLine())!= null ) {
        boats.addElement( new boat(s));
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    Choice choice1 = new Choice();
    Choice choice2 = new Choice();
    itor = boats.iterator();
    while( itor.hasNext() ) {
      boat item = (boat)itor.next();
      choice1.add(item._boatOwner);
      choice2.add(item._boatNumber);
    }
    add(choice1);
    add(choice2);

    textField1 = new TextField(2);
    textField2 = new TextField(2);
    textField3 = new TextField(2);
   
    add(textField1);
    add(textField2);
    add(textField3);

    Button button1 = new Button("New Boat");
    Button button2 = new Button("Save");

    add (button1);
    Button1Listener controlButton1 = new Button1Listener();
    button1.addActionListener(controlButton1);

    add (button2);
    Button2Listener controlButton2 = new Button2Listener();
    button2.addActionListener(controlButton2);

   
  }

  class  Button1Listener implements ActionListener {
    public void  actionPerformed(ActionEvent e) {
      Object source = e.getSource();
       textField1.setText("00");
      
    }
  }

  class  Button2Listener implements ActionListener {
    public void  actionPerformed(ActionEvent e) {
      Object source = e.getSource();
     
        textField1.setText("11");
      
    }
  }

  class boat {
    public String _boatOwner;
    public String _boatNumber;
    public String _boatClass;
    public double _boatRating;

    public boat() {}

    public boat(String rec){
      int i = rec.indexOf( ',', 0 );
      _boatOwner = rec.substring( 0, i ).trim();
      int j = rec.indexOf(',',i + 1 );
      _boatNumber = rec.substring( i + 1, j ).trim();
      int k = rec.indexOf( ',', j + 1 );
      _boatClass = rec.substring( j + 1 , k ).trim();
      _boatRating = Double.parseDouble(rec.substring( k  + 1 ).trim());
    }

    boolean equals( boat b ) {
      return( _boatClass.equals( b._boatClass ) );
    }
  }


  public class result {
    public int _raceNumber;
    public String _sailor;
    public String _boatNumber;
    public String _boatClass;
    public int _elapsedTime;
    public int _correctedTime;
    public int _position;
    public int _positionOf;
    public double _score;

    public result(){};

    public result( int raceNumber,
                   String sailor,
                   String boatNumber,
                   String boatClass,
                   int elapsedTime,
                   int correctedTime,
                   int position,
                   int positionOf,
                   double score){
      _raceNumber = raceNumber;
      _sailor = sailor;
      _boatNumber = boatNumber;
      _boatClass = _boatClass;
      _elapsedTime = elapsedTime;
      _correctedTime = correctedTime;
      _position = position;
      _positionOf = positionOf;
      _score = score;
    }

  }
}



