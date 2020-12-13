import java.io.*;
public class ConsoleIO2{

public static void main(String as[]) throws IOException  {
 byte [] b = new byte[20];
 int e;
 while ( (e= System.in.read(b)) != -1)
 {
	 System.out.write(b);

 }
 }
}
