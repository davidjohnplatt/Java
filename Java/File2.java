import java.io.*;

class Acc implements Serializable
{
	int custnum;
	String nm;

    public  Acc()
    {
	}

	public Acc(int x, String s)
	{
		custnum=x;
		nm=s;
	}

	public String toString()
	{
		return ("ACCNO = "+custnum+"NAME = "+nm);
	}
}


class File2 extends Object
{
     public static void main(String args[]) throws Exception
     {

     FileOutputStream ostream = new FileOutputStream("acct.dat");
     ObjectOutputStream  df1= new ObjectOutputStream(ostream);

     DataInputStream d = new DataInputStream (System.in);

     String s1; int x;
		   Acc  acobj = new Acc(101, "firstacc");

          df1.writeObject(acobj);
		    acobj = new Acc(102, "Secondacc");

		           df1.writeObject(acobj);

		  acobj = new Acc(103, "Thirsdacc");

		           df1.writeObject(acobj);
            ostream.close();
		    df1=null;

		           FileInputStream istream = new FileInputStream("acct.dat");
				   	ObjectInputStream df = new ObjectInputStream(istream);



       acobj = (Acc)df.readObject();

		 System.out.println(acobj);
		   acobj = (Acc)df.readObject();

		 		 System.out.println(acobj);

  acobj = (Acc)df.readObject();

		 System.out.println(acobj);



     }
 }
