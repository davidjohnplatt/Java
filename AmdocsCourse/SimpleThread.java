class SimpleThread extends Object
{
	public static void main(String[] args)
	{
	Thread th;
		FibonacciThread st = new FibonacciThread("first");
		st.start();
                  FibonacciThread st1 = new FibonacciThread("sec");
                  st1.start();
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
		for(int count=0; count < 100; count++)
		{
			System.out.println( s+ " " +secondNumber);
			int temp =  firstNumber;
			firstNumber = secondNumber;
			secondNumber = firstNumber + temp;
		}
	}
}

