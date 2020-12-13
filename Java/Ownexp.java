class Exp1  extends Exception{
	     private String themesg;
	     public Exp1(String s)
	      {
			   themesg=s;
		   }
		   public String toString()
		    { return themesg;}


	 }

class Test {
	  int i;
	  public Test() {i = 0;}
	  public Test(int j) { i=j; }

	  public void meth() throws Exp1

	  {
		  if (i== 0 )
		    throw new Exp1("i is zero ");
		  else
		     throw new Exp1("i is positive");

		 }

}


class Ownexp{



  public static void main(String [] st)
  {
	  Test obj1 = new Test();
      Test obj2 = new Test(5);

     try{
		 obj1.meth();
	 }

	 catch (Exp1 e)

	  {
	      System.out.println("catch  on obj 1" + e);

	  }
	  try{
	  		 obj2.meth();
	  	 }

	  	 catch (Exp1 e)

	  	  {
	  	      System.out.println("catch  on obj 2"+ e);
	  }


    }
    }
