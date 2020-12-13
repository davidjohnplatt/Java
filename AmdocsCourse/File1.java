import java.io.*;

class File1 extends Object
{
     public static void main(String args[]) throws IOException
     {

DataOutputStream df1 = new DataOutputStream (new FileOutputStream("inFile.dat"));

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
		 df1=null;

//DataOutputStream df1 = new DataOutputStream (new FileOutputStream("inFile.dat"));
 DataInputStream df = new DataInputStream (new FileInputStream("inFile.dat"));



		 s1= df.readLine();
		 System.out.println(s1);

           s1= df.readLine();
		 System.out.println(s1);

            s1= df.readLine();
		   		 System.out.println(s1);






     }
 }