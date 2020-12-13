import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.NoSuchElementException;

class StringTokenizer extends Object
{
    public static void main(String[] args)
    {
        // use default delimeters
        String s = "Java Class Libraries";
        StringTokenizer tokenizer = new StringTokenizer(s);
        System.out.println("\nInput: "+s);
        System.out.println("There are "+ tokenizer.countTokens()+" entries in the path");

        try
        {
            while(tokenizer.hasMoreTokens())
            {
               System.out.println(tokenizer.nextToken());
            }
         }

         catch(NoSuchElementException e)
         {
            System.out.println(e);
         }

         // Example that uses a ";" as a seperator

         s = "c:\\windows\\command;c:\\dos;c:\\bin;c:\\util";
         tokenizer = new StringTokenizer(s,";");
         System.out.println("\nInput: "+s);
         System.out.println("There are "+ tokenizer.countTokens()+" entries in the path");

         try
         {
             while(tokenizer.hasMoreTokens())
	                 {
	                    System.out.println(tokenizer.nextToken());
	                 }
         }

         catch(NoSuchElementException e)
	 {
	             System.out.println(e);
         }

         // Example that uses multiple seperators


         s="a=b+c, d=e.";
         tokenizer = new StringTokenizer(s,",.",true);
         System.out.println("\nInput: "+s);
	 System.out.println("There are "+ tokenizer.countTokens()+" tokens");

	 try
	 {
	     while(tokenizer.hasMoreTokens())
	     {
	 	    System.out.println(tokenizer.nextToken());
             }
         }

	 catch(NoSuchElementException e)
	 {
	 	e.printStackTrace();
         }

    }

 }