import java.lang.*;
import java.io.*;
import java.util.*;

public class scoreSheet {
  public double sheet [] [] = new double [100] [100];

  public scoreSheet(){
    int i = 0;
    int j = 0;
    int k = 0;
    int lineLength = 0;
    String s;
    
    try {
      BufferedReader r = new BufferedReader(new FileReader("scoreSheet.dat"));
      while( (s = r.readLine())!= null ) {
        i++;
        while (k < s.length()) {
          j++;
          sheet [i] [j] =  Double.parseDouble(s.substring( k, k + 5 ));
          k = k + 6;
        }
        k = 0;
        j = 0;
      }
      r.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  } //scoreSheet constructor


  void print() {
    for (int i = 1;i < 20; i++) {
      for (int j = 1; j < 20; j++) {
        System.out.print(sheet [i] [j] + " ");
      }
      System.out.println(" ");
    }
  }//print

  double getScore(int i, int j){
     return sheet [i] [j];
  } // getScore

}//scoreSheet
