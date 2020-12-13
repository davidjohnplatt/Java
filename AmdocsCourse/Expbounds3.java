class Expbounds3 {


  public static void main(String [] st)
  {
	    int [] a = new int[5];
      try{

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
    }
    }
