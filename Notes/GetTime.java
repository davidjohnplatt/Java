import java.net.*;
import java.io.*;

public class GetTime {
 public static void main(String args[]) {
/*  if (args.length != 1) {
   System.out.println("Usage: GetTime HOST");
   return;
   }*/
  try  {
//   Socket s = new Socket(args[0],13);
 Socket s = new Socket("ariel",13);
   InputStream is = s.getInputStream();
   while (true) {
    byte b[] = new byte[100];
    int i=is.read(b);
    if (i==-1) return;
    System.out.print(new String(b,0,i));
    }
   }
  catch (Exception e)  {
   e.printStackTrace();
   }
  }
}
