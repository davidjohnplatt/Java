import java.io.*;
import java.net.*;

class URLexample {

public static void main(String ar[]) throws Exception
  {


		    URL  u = new URL(ar[0]);
		    URLConnection ucon = u.openConnection();
		    InputStream is = ucon.getInputStream();

		    int ceof;

		    while((ceof=is.read()) != -1)
		    {
				System.out.println((char)ceof);

			  }


			 System.out.println("\n"+"reading done");

		 }
	 }

