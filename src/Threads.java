import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Threads
{

private static ArrayList<WorkerThread> threads;
private WorkerThread current;

public Threads(WorkerThread workerThread) throws Exception
	{
		//	System.out.println("\n---Entered Threads Constructor---\n");
		this.current = workerThread;
		//System.out.println("\n---About to enter updateData method---\n");

		updateData(current);

	}

public Threads(int numOfThreads)
	{
		if (numOfThreads < 3)
			{
			System.out.println("Invalid Input! number of Threads must be at least 3.\n" + "App will create 3 threads.");
			initialize(3);
			}
		else
			{
			initialize(numOfThreads);
			}
	}

public void initialize(int size)
	{
		threads = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < size; i++)
			{
			threads.add(i, new WorkerThread(i - 1, i, i + 1, random.nextInt(101), size));
			}

	}

protected void process()
	{
		ExecutorService executor = Executors.newFixedThreadPool(threads.size());

		for (WorkerThread workerThread : threads)
			{
			executor.submit(workerThread);
			}
		try
			{
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e)
			{
			System.err.println("tasks interrupted");
			} finally
			{
			if (!executor.isTerminated())
				{
				System.err.println("cancel non-finished tasks");
				}
			executor.shutdownNow();
			System.out.println("shutdown finished");
			}
	}

private void updateData(WorkerThread current)
	{
		int leftIndex = current.getLeftNeighbourIndex();
		int rightIndex = current.getRightNeighbourIndex();
		int currentId = current.getRndId();
		int currentIndex = current.getIndex();

		if (currentId > threads.get(leftIndex).getRndId() && currentId > threads.get(rightIndex).getRndId())
			{
			threads.get(currentIndex).setId(currentId - 1);
			}
		else if (currentId < threads.get(leftIndex).getRndId() && currentId < threads.get(rightIndex).getRndId())
			{
			threads.get(currentIndex).setId(currentId + 1);
			}
	}

protected void refreshConsole(int loopNumber)
	{

		if (threads == null || threads.size() == 0) System.out.println("Null or Empty");

		else
			{
			String res = "";
			for (int i = 0; i < threads.size(); i++)
				{
				res += threads.get(i).getRndId() + "\t";
				}
			if (loopNumber == 0)
				{
				System.out.println(MessageFormat.format("\nInitial State of DB is:\n{0}", res));
				}
			else System.out.println(MessageFormat.format("\nAfter loop # {0}:\n{1}", loopNumber, res));

			}
	}
}
