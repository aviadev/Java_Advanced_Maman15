import java.io.Console;
import java.text.MessageFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aviad on 15/01/2016.
 */
public class Logic implements Runnable
{


private int numOfThreads, numOfLoops;
protected DataBase db;
ExecutorService executor = ;
//AccessThread accessThread;


public Logic(DataBase db, int numOfThreads, int numOfLoops)
	{
		this.db = db;
		this.numOfThreads = numOfThreads;
		this.numOfLoops = numOfLoops;
		//accessThread = new AccessThread(db);
	}


protected void doLoops()
	{

			refreshConsole(0, true);

		for (int i = 0; i < numOfLoops; i++)
			{
				//accessThread.run();
				this.run();
				refreshConsole(i, false);
			}
	}

private void refreshConsole(int loopNumber, boolean initialState)
	{
		if (initialState)
			System.out.println(MessageFormat.format("\nInitial State of DB is:\n{0}", db));
		else
		System.out.println(MessageFormat.format("\nAfter loop # {0}:\n{1}", loopNumber,db));
	}

@Override
public void run()
	{
		executor = Executors.newFixedThreadPool(numOfThreads);
		this.updateDb();

	}

public void updateDb()
	{
		//int threadId = db.get((int) accessThread.getId());
		int threadId = db.get((int) accessThread.getName().charAt(accessThread.getName().length()-1));
		System.out.println(threadId);

		int size = db.size();
		int leftNeighbour = 0, rightNeighbour = 0;

		if (threadId == db.get(size))
			{
			leftNeighbour = db.get(threadId - 1);
			rightNeighbour = db.get(0);
			}
		else if (threadId == db.get(0))
			{
			leftNeighbour = db.get(size);
			rightNeighbour = db.get(threadId + 1);
			}
		else
			{
			leftNeighbour = db.get(threadId - 1);
			rightNeighbour = db.get(threadId + 1);
			}

		if (threadId > leftNeighbour && threadId > rightNeighbour)
			{
			db.set(threadId, db.get(threadId) + 1);
			}
		else if (threadId < leftNeighbour && threadId < rightNeighbour)
			{
			db.set(threadId, db.get(threadId) - 1);
			}

	}
}
