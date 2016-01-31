import java.text.MessageFormat;

import static java.lang.System.currentTimeMillis;

public class Pylosof extends Thread implements Comparable<Pylosof>
{

private int myId;
private int duration;
private Activity activity;

public Pylosof(String name, int myId)
	{
		super(name);
		this.myId = myId;
	}

protected int getMyId() {return myId;}

protected synchronized Activity getActivity() {return activity;}

protected void setActivity(Activity activity)
	{
		switch (activity)
			{
			case Eat:
				this.activity = Activity.Eat;
				break;
			case Think:
				this.activity = Activity.Think;
				break;
			case Wait:
				this.activity = Activity.Wait;
			}
	}

protected void setActivityAndItsDuration(Activity activity, int duration)
	{
		setActivity(activity);
		this.duration = duration;
	}

@Override
public synchronized void run()
	{
		if (this.activity == Activity.Eat)
			{
			System.err.println(this.getName() + " is going to eat for " + this.duration / 1000 + " Seconds");
			long tStart = currentTimeMillis();
			try
				{
				sleep(duration);
				long tEnd = currentTimeMillis();
				System.err.println(this.getName() + " done eating after " + ((tEnd - tStart) / 1000) + " Seconds");
				notify();

				} catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}

		else if (this.activity == Activity.Think)
			{

			System.err.println(this.getName() + " is going to think for " + this.duration / 1000 + " Seconds");
			long tStart = currentTimeMillis();
			try
				{
				sleep(duration);
				long tEnd = currentTimeMillis();
				System.err.println(this.getName() + " done thinking after " + ((tEnd - tStart) / 1000) + " Seconds");
				notify();

				} catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}
	}

public synchronized String toString()
	{

		return MessageFormat.format("Pylosof # {0}:" +
				"\tState: {1}" +
				"\t\tDuration: {2}", myId, activity, duration / 1000);
	}

@Override
public int compareTo(Pylosof p)
	{
		if (this.getMyId() > p.getMyId()) return 1;
		else if (this.getMyId() < p.getMyId()) return -1;
		else return 0;
	}

protected enum Activity
{
	Eat, Think, Wait
}

}


