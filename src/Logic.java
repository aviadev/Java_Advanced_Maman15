/*
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

*/
/**
 * Created by aviad on 20/01/2016.
 *//*

public class Logic
{

//protected WorkerThread data;
//protected int numOfThreads;
//private WorkerThread threads;
private Threads threads;

*/
/*public Logic(int numOfThreads)
	{

		//data = new WorkerThread();
		if (numOfThreads < 3)
			{
			System.out.println("Invalid Input! number of Threads must be at least 3.\n" +
					"App will create 3 threads.");
			//this.numOfThreads = 3;
		//	data.initialize(3);
			//workers = new ArrayList<>(3);
			//workers2 = new WorkerThread[3];
			threads = new WorkerThread [3];
			}
		else
			{
		//	data.initialize(numOfThreads);
			//workers = new ArrayList<>(numOfThreads);
			//workers2 = new WorkerThread[numOfThreads];
			//this.numOfThreads = numOfThreads;
			threads = new WorkerThread [numOfThreads];
			}

	}*//*



public Logic(Threads threads)
	{
		this.threads = threads;
	}

public synchronized void process(int numOfLoops)
	{
		prepareThreads ();

		ExecutorService executor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

		for (int i = 1; i <= numOfLoops; i++)
			{
			for (WorkerThread wt : threads)
				{
				executor.execute(wt);
				}

			//sendThreads();
			refreshConsole(i);
			}

		executor.shutdown();
	}

private void prepareThreads()
	{

		Random random = new Random();


		for (int i = 0; i < threads.length; i++)
			{
			System.out.println("threads.getSize()" + threads.length);
			WorkerThread wt = new WorkerThread(i-1 ,i, i+1, random.nextInt(101));
			//threads[i] = wt;
			//threads.getThreads()[i] = wt;
			threads[i].addThread(i, wt);
			//threads = new WorkerThread(threads);
			//WorkerThread wtArr = new WorkerThread(workers2);
			}
		//WorkerThread copy = new WorkerThread (threads);
		refreshConsole(0);
	}


private void sendThreads()
	{

		System.out.println("SendThreads method - does nothing");

	}


protected synchronized void refreshConsole(int loopNumber)
	{
		if (loopNumber == 0) System.out.println(MessageFormat.format("\nInitial State of DB is:\n{0}", threads));
		else System.out.println(MessageFormat.format("\nAfter loop # {0}:\n{1}", loopNumber, threads));

	}




}
*/
