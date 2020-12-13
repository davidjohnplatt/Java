import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

class Servprog {

public static void main(String ar[])
  {
	  try{
		    ServerSocket sersoc = new ServerSocket(Integer.parseInt(ar[0]));
		    Socket soc = sersoc.accept();
		    InputStream is = soc.getInputStream();
		    OutputStream out = soc.getOutputStream();
		    int ceof;

		    while((ceof=is.read()) != -1)
		    {
				System.out.println((char)ceof);
				  out.write(ceof);
			  }
			 System.out.println("writing done");
			 soc.close();
		 }
		 catch(Exception e)
		 {
			 System.out.println("Unable to proceed");
			 System.out.println(e);
		 }
	 }
 }
