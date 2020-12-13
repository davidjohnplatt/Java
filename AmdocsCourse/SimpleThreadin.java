class SimpleThreadin extends Object
{
	public static void main(String [] args) throws Exception
	{
	Thread th;
		FibonacciThread st = new FibonacciThread("first");
		st.start();
                  FibonacciThread st1 = new FibonacciThread("sec");

                  st1.start();
         st.interrupt();

        System.out.println( "Name = " + Thread.currentThread().getName());

        st1.interrupt();
			}
}
class FibonacciThread extends Thread
{
	int firstNumber, secondNumber;
	String s;
	public FibonacciThread( String s1)
	{     s= s1;
		firstNumber = 0;
		secondNumber = 1;
	}

	public void run()
	{
		try{
			  Thread-1.sleep(2000);
		  }
		  catch(InterruptedException e)
		    { System.out.println("interrupted " + s);
		}

		for(int count=0; count < 10; count++)
		{
			System.out.print( s+ " " +secondNumber);
			int temp =  firstNumber;
			firstNumber = secondNumber;
			secondNumber = firstNumber + temp;
		}
		System.out.println( "Name = " + Thread.currentThread().getName()+ s);
	}
}

