import java.io.*;
import java.net.*;

class openURL {

  public static void main (String[] args) {
    String fname=(args.length <= 0) ? "MDS.csv": args[0];
    String nameURL = "http://411ca.whitepages.com/1234/search/ReversePhone?phone=905-821-2270";

    try {

      URL myURL = new URL(nameURL);

      BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

      String str;
      String priceStr;

      int commaPos[] = new int[5];
      int j = 0;
      int lineKount = 0;

      double priceDbl = 0;
      double totalDbl = 0;

      while ((str = in.readLine()) != null) {
 //     out.write(str);
        j = 0;
        lineKount++;
        int textLength = str.length();
        priceStr = "";
        System.out.println(str);
 /*       
        for (int i = 0; i < textLength; i++) {
           
          char ch = str.charAt(i);
          if (ch == ',') {
            commaPos[j] = i;
 //         System.out.print(commaPos[j] + ",");
            j++;
          }
          else {
            if (j == 3){
              priceStr = priceStr + ch;
            }
          }
        }
 */       
//        priceDbl = Double.valueOf(priceStr).doubleValue();
 //       totalDbl = totalDbl + priceDbl;
//      out.write(priceStr);   
//      out.newLine();
      }

 //     System.out.println("Rolling Average from file : " + totalDbl / lineKount );
 
      in.close();
      out.close();
    } 
    catch (IOException e){
        e.printStackTrace();
    }
  }
}