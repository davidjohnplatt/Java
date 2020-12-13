import java.io.*;

public class getKeyboardData {
  private StreamTokenizer t = new StreamTokenizer(new InputStreamReader(System.in));

//public getString() {
//}

  public String getString(String inStr) {
    try {
       for (int i = 0; i < 5; i++) {
         System.out.print("Enter " + inStr + ": ");
         if ( t.nextToken() == t.TT_WORD )
           return t.sval;
         else {
           System.out.println("Error -  String required ");
           continue;
         } //else
      }//for
      System.out.println("Error - incorrect after 5 attempts ");
      System.exit(1);
      return null;
    }//try
    catch (IOException e) {
      System.out.println(e);
      System.exit(1);
      return null;
    }//catch
  }//getString

 public int getInt(String inStr) {
    try {
      for (int i = 0; i < 5; i++) {
        System.out.print("Enter " + inStr + ": ");
        if ( t.nextToken() == t.TT_NUMBER )
          return (int)t.nval;
        else {
          System.out.println("Error - integer required ");
          continue;
        } //else
      }//for
      System.out.println("Error - incorrect after 5 attempts ");
      System.exit(1);
      return 0;
    }//try
    catch (IOException e) {
      System.out.println(e);
      System.exit(1);
      return 0;
    }//catch
  }//getInt
}//getKeyboardData

