
class SyncObject extends Object
{
	private int value ;
	private boolean avail ;

	public SyncObject ()
	{
		avail = false ;
		value = 0;
	}

	public synchronized int get ()
	{
		while (avail == false)
		{
			try
			{
				wait () ;
			}
			catch (InterruptedException exc)
			{
			}
		}
		avail = false ;
		notify() ;
		return value ;
	}

	public synchronized void put (int newVal)
	{
		while(avail == true)
		{
			try
			{
				wait () ;
			}
			catch(InterruptedException exc)
			{
			}
		}
		value = newVal ;
		avail = true ;
		notify () ;
	}
}

class WaitNotify extends Object
{
	public static void main (String [] args)
	{
		SyncObject obj = new SyncObject () ;
		Producer prod = new Producer (obj) ;
		Consumer cons = new Consumer (obj) ;
		prod.start () ;
		cons.start() ;
	}
}

class Producer extends Thread
{
	private SyncObject synObj ;

	public Producer(SyncObject obj)
	{
		synObj = obj ;
	}

	public void run ()
	{
		for(int count = 0; count<10; count ++)
		{
			synObj.put(count) ;
			System.out.println("Producer put : #" + count);
		}
	}
}

class Consumer extends Thread
{
	private SyncObject synObj ;
	public Consumer(SyncObject obj)
	{
		synObj = obj ;
	}

	public void run ()
	{
		int currentValue ;
		for(int count = 0; count<10; count ++)
		{
			currentValue = synObj.get() ;
			System.out.println ("Consumer got : #" + currentValue);
		}
}

}

