/**
 * Created by aviad on 15/01/2016.
 */
public class MyThread implements Runnable
{

private  int id;
private Data data;
private String name;
//private int numOfThreads;

public int getThreadId() {return id;}

public MyThread() {}

/*public MyThread (String name, int id, Data data)
	{
		super(name);
		this.id = id;
	}

public MyThread (int id, Data data)
	{
		this.id = id;
		//this.data = data;
	}*/

public MyThread (Data data)
{
		this.data = data;
	}

public MyThread (int id)
{
	this.id = id;
}

@Override
public void run()
	{
		System.out.println("\nStarted Run - currentThreadId: " + Thread.currentThread().getId());
		updateData(this);
	}

public void updateData(MyThread threadId)

	{
		int size = data.size();
		int currentThreadId = this.id;


		System.out.println("\ncurrentThreadId: " + currentThreadId);

		//System.out.println("\nupdateData - Executor status: " + this.executor.toString());
		//System.out.println("\nupdateData - Thread status: " + thread.getState());

		int leftNeighbourIndex = (data.threadArrayList.indexOf(this)) -1;
		System.out.println("\nleftNeighbourIndex: " + leftNeighbourIndex);

		int rightNeighbourIndex = (data.threadArrayList.indexOf(this)) +1;
		System.out.println("\nrightNeighbourIndex: " + rightNeighbourIndex);

		int currentThreadIndex = data.threadArrayList.indexOf(this);
		System.out.println("\ncurrentThreadIndex: " + currentThreadIndex);



		if (rightNeighbourIndex == size) rightNeighbourIndex = 0;
		if (leftNeighbourIndex == -1) leftNeighbourIndex = size - 1;

		int rightNeighbourId = data.threadArrayList.get(rightNeighbourIndex).getThreadId();
		int leftNeighbourId =  data.threadArrayList.get(leftNeighbourIndex).getThreadId();

		if (currentThreadId > rightNeighbourId && currentThreadId > leftNeighbourId)
			data.threadArrayList.set(currentThreadIndex, new MyThread(currentThreadId -1));
		else
			data.threadArrayList.set(currentThreadIndex, new MyThread(currentThreadId +1));
	}



/*public MyThread(Data db)
	{
		this.db = db;
		numOfThreads = db.size();
	}*/

/*public void run()
	{
		updateDb(db);
	}*/

/*protected synchronized void updateDb ()
	{
		synchronized (this)
			{
				db.updateDb(this);
			}
	}*/

/*public void updateDb(MyThread myThread)
	{
		//int threadId = db.get((int) accessThread.getId());
		int threadId = this.id;
		System.out.println(threadId);
		int size = data.size();
		MyThread thread = null;


		MyThread leftNeighbour = null;
		MyThread rightNeighbour = null;

		if (thread.id == data.threadArrayList.get(size).id)
		{
		leftNeighbour.id = data.threadArrayList.get(threadId - 1).id;
		rightNeighbour.id = data.threadArrayList.get(0).id;
		}
		else if (threadId == data.threadArrayList.get(0).id)
		{
		leftNeighbour = data.threadArrayList.get(size);
		rightNeighbour = data.threadArrayList.get(threadId + 1);
		}
	else
		{
		leftNeighbour = data.threadArrayList.get(threadId - 1);
		rightNeighbour = data.threadArrayList.get(threadId + 1);
		}

		if (threadId > leftNeighbour.id && threadId > rightNeighbour.id)
			{
			data.threadArrayList.set(threadId, data.threadArrayList.get(threadId + 1));
			}
		else if (threadId < leftNeighbour.id && threadId < rightNeighbour.id)
			{
			data.threadArrayList.set(threadId, data.threadArrayList.get(threadId - 1));
			}

	}*/

/*@Override
public void run()
	{

		updateDb(Data data);
	}*/
}
