import java.io.*;

class File3 extends Object
{
     public static void main(String args[]) throws IOException
     {

RandomAccessFile df1 = new RandomAccessFile ("RinFile.dat","rw");

        //DataInputStream df = new DataInputStream (new FileInputStream("inFile.dat"));


          DataInputStream d
		             = new DataInputStream (System.in);

		   String s1;


		 s1= d.readLine();
		 df1.writeBytes(s1+"\n");
		 s1= d.readLine();
		 		 df1.writeBytes(s1+"\n");

		 s1= d.readLine();
		 		 df1.writeBytes(s1+"\n");
		 df1.seek(0);

//DataOutputStream df1 = new DataOutputStream (new FileOutputStream("inFile.dat"));
 //DataInputStream df = new DataInputStream (new FileInputStream("inFile.dat"));



		 s1= df1.readLine();
		 System.out.println(s1);

           s1= df1.readLine();
		 System.out.println(s1);

            s1= df1.readLine();
		   		 System.out.println(s1);






     }
 }