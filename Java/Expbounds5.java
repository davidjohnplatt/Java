class Expbounds5{

    void f1() throws Exception
      {
		   throw new Exception();
	   }


  public static void main(String [] st)
  {
	    Expbounds5 obj = new Expbounds5();
	    int [] a = new int[5];
      try{
           obj.f1();
	  System.out.println(a[6]);
	  }
	   catch (ArrayIndexOutOfBoundsException e)
	   {
		   System.out.println("array out of bound");
	   }
	  catch(Exception e)
	   {
		   System.out.println("some other error");

	   }

	   finally {
		   System.out.println("finally block");

	   }
    }

    }
