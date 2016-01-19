import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aviad on 15/01/2016.
 */
public class Logic
{


private int numOfLoops;
//private int	numOfThreads;
//protected Data data;
private ExecutorService executor;
//private MyThread myThread;


public Logic () {};

public Logic(Data data, int numOfThreads, int numOfLoops)
	{
		//this.data = data;
		//this.numOfThreads = numOfThreads;
		this.numOfLoops = numOfLoops;
		//myThread = new MyThread();
		executor = Executors.newFixedThreadPool(numOfThreads);
	}


protected void processLoops(Data data)
	{


			refreshConsole(data,0, true);

		for (int i = 0; i < numOfLoops; i++)
			{
				Runnable worker = new MyThread(data.threadArrayList.get(0).getThreadId());
				executor.execute(worker);

				refreshConsole(data, i+1, false);
			}
		executor.shutdown();

		while (!executor.isTerminated())
			{
			}
		System.out.println("\nFinished all threads");

	}

private void refreshConsole(Data data, int loopNumber, boolean initialState)
	{
		if (initialState)
			System.out.println(MessageFormat.format("\nInitial State of DB is:\n{0}", data));
		else
		System.out.println(MessageFormat.format("\nAfter loop # {0}:\n{1}", loopNumber,data));
	}


/*private void updateData(Data data)
	{
		int size = data.size();
		int currentThreadId = this.executor..getThreadId();

		System.out.println("\ncurrentThreadId: " + currentThreadId);

		//System.out.println("\nupdateData - Executor status: " + this.executor.toString());
		//System.out.println("\nupdateData - Thread status: " + thread.getState());

		int leftNeighbourIndex = (data.threadArrayList.indexOf(thread)) -1;
		System.out.println("\nleftNeighbourIndex: " + leftNeighbourIndex);

		int rightNeighbourIndex = (data.threadArrayList.indexOf(thread)) +1;
		System.out.println("\nrightNeighbourIndex: " + rightNeighbourIndex);

		int currentThreadIndex = data.threadArrayList.indexOf(thread);
		System.out.println("\ncurrentThreadIndex: " + currentThreadIndex);



		if (rightNeighbourIndex == size) rightNeighbourIndex = 0;
		if (leftNeighbourIndex == -1) leftNeighbourIndex = size - 1;

		int rightNeighbourId = data.threadArrayList.get(rightNeighbourIndex).getThreadId();
		int leftNeighbourId =  data.threadArrayList.get(leftNeighbourIndex).getThreadId();

		if (currentThreadId > rightNeighbourId && currentThreadId > leftNeighbourId)
			data.threadArrayList.set(currentThreadIndex, new MyThread(currentThreadId -1));
		else
			data.threadArrayList.set(currentThreadIndex, new MyThread(currentThreadId +1));
	}*/



/*@Override
public void run()
	{
		executor.execute(updateDb(db));
		updateDb(db);

	}*/

/*public void updateDb()
	{
		//int threadId = db.get((int) accessThread.getId());
		int threadId = this.id;
		System.out.println(threadId);

		int size = data.size();
		int leftNeighbour = 0, rightNeighbour = 0;

		if (threadId == data.threadArrayList.get(size))
			{
			leftNeighbour = db.db.get(threadId - 1);
			rightNeighbour = db.db.get(0);
			}
		else if (threadId == db.db.get(0))
			{
			leftNeighbour = db.db.get(size);
			rightNeighbour = db.db.get(threadId + 1);
			}
		else
			{
			leftNeighbour = db.db.get(threadId - 1);
			rightNeighbour = db.db.get(threadId + 1);
			}

		if (threadId > leftNeighbour && threadId > rightNeighbour)
			{
			db.db.set(threadId, db.db.get(threadId) + 1);
			}
		else if (threadId < leftNeighbour && threadId < rightNeighbour)
			{
			db.db.set(threadId, db.db.get(threadId) - 1);
			}

	}*/


}
