import java.io.*;
public class DataConsoleIO
{

   public static void main(String as[]) throws IOException  
   {
      FileInputStream infile = new FileInputStream("datafile");
      DataInputStream in = new DataInputStream(infile);

      FileOutputStream outfile = new FileOutputStream("datafile.out");
      DataOutputStream out = new DataOutputStream(outfile);

      String s;

      s = in.readLine();
      out.writeBytes(s+"\n");
//    out.flush;
      System.out.println(s);
   }
}
