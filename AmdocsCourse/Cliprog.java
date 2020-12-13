import java.io.*;
import java.net.Socket;

class Cliprog {

public static void main(String ar[]) throws Exception
  {


		    Socket soc = new Socket(ar[0], Integer.parseInt(ar[1]));
		    InputStream is = soc.getInputStream();
		    OutputStream os = soc.getOutputStream();
		    int ceof;
		    byte b[] = {65,66,67,68,69};
		    os.write(b);
		    os.write(-1);

		    System.out.println("writing done");
            System.out.println("Starting to read");
		    while((ceof=is.read()) != -1)
		    {
				System.out.println((char)ceof);

			  }


			 System.out.println("\n"+"reading done");
			 soc.close();
		 }
	 }

