class SimpleThreadslp extends Object
{
	public static void main(String [] args) throws Exception
	{
	Thread th, th1;
		FibonacciThread st = new FibonacciThread("first");
		th = new Thread(st);
		th.start();
                  FibonacciThread st1 = new FibonacciThread("sec");
                th1 = new Thread(st1);
		   th1.start();
                  st1.start();

                  try{       System.out.println("sleep " + st1.s);
				  			 st1.sleep(2000);
				  		  }
				  		  catch(InterruptedException e)
				  		    { System.out.println("interrupted " + st1.s);
				  		}

         //st.interrupt();

        System.out.println( "Name = " + Thread.currentThread().getName());

        st1.interrupt();
			}
}
class FibonacciThread implements Runnable // extends Thread
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
		/*try{
			  sleep(2000);
		  }
		  catch(InterruptedException e)
		    { System.out.println("interrupted " + s);
		} */

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

