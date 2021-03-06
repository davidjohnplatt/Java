import java.io.*;
import java.net.*;

class readTokens {

  static int TT_EOF;
  static int TT_EOL;
  static int TT_NUMBER;
  static int TT_WORD;
  int ttype;
  double nval;
  String sval;
  

  public static void main (String[] args) {
   
    String nameURL = "http://psweb1.solect.com";
    String str;
    int lineKount = 0;

    str = "Done";

    try {

      URL myURL = new URL(nameURL);

      Reader r = new BufferedReader(new InputStreamReader(myURL.openStream()));
      StreamTokenizer in = new StreamTokenizer(r);
     
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

      int iType;
      int c;
      boolean kontinue;
/*
      while ((iType = in.nextToken()) != TT_EOF) {
        lineKount++;
        System.out.println(in.sval);
        }

 */
      kontinue = true;
      while (kontinue) {
         c = in.nextToken();
            switch (c) {
               case StreamTokenizer.TT_EOF:
                  System.out.println("EOF");
                  kontinue = false;
                  break;
               case StreamTokenizer.TT_EOL:
                  System.out.println("EOL");
                  break;
               case StreamTokenizer.TT_NUMBER:
                  System.out.println("Number");
                  break;
               case StreamTokenizer.TT_WORD:
                  System.out.println("Word " + in.sval);
                  break;
               default:
                  break;
            }
       }
       out.close();
    } 

    catch (IOException e){
        e.printStackTrace();
    }
  }
}