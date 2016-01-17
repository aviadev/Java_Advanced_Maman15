import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aviad on 15/01/2016.
 */
public class AccessThread implements Runnable
{

private int numOfThreads;
protected DataBase db;

public AccessThread() {}



public AccessThread(DataBase db)
	{
		this.db = db;
		numOfThreads = db.size();
	}

public void run()
	{
		ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
		executor.execute(new Runnable() {
			@Override
			public void run()
				{

				}
		});
		//executor.submit(updateDb);
		this.updateDb();
		//this.(executor);
	}

protected synchronized void updateDb ()
	{
		synchronized (this)
			{
				db.updateDb(this);
			}
	}

}
