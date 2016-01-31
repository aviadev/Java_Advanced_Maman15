import java.text.MessageFormat;

/**
 * Created by aviad on 28/01/2016.
 */
public class Pylosof extends Thread implements Comparable<Pylosof>
{

private int myId;
private int duration;
private Activity activity;

//public Pylosof(String name, int myId) {this.name = name; this.myId = myId;}
public Pylosof(String name, int myId)
	{
		super(name);
		this.myId = myId;
	}

protected int getDuration() {return duration;}

protected int getMyId() {return myId;}

//protected synchronized String getName() {return name;}
protected synchronized Activity getActivity() {return activity;}

protected void setActivity(Activity activity)
	{
		switch (activity)
			{
			case Eat:
				this.activity = Activity.Eat;
				//isEating = true;
				break;
			case Think:
				this.activity = Activity.Think;
				//isThinking = true;
				break;
			case Wait:
				this.activity = Activity.Wait;
				//isWaiting = true;
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
		Logic logic = new Logic(this);
		if (this.activity == Activity.Eat)
			{
			System.err.println(this.getName() + " is going to eat for " + this.duration / 1000 + " Seconds");
			long tStart = System.currentTimeMillis();
			try
				{
				this.sleep(duration);
				//this.wait(duration);
				long tEnd = System.currentTimeMillis();
				System.err.println(this.getName() + " done eating after " + ((tEnd - tStart) / 1000) + " Seconds");
				notify();

				//notifyAll();
				} catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}

		else if (this.activity == Activity.Think)
			{

			System.err.println(this.getName() + " is going to think for " + this.duration / 1000 + " Seconds");
			long tStart = System.currentTimeMillis();
			try
				{
				this.sleep(duration);
				//this.wait(duration);
				long tEnd = System.currentTimeMillis();
				System.err.println(this.getName() + " done thinking after " + ((tEnd - tStart) / 1000) + " Seconds");
				notify();
				//notifyAll();

				} catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}
	}

public synchronized String toString()
	{

		String retVal = MessageFormat.format("Pylosof # {0}:" +
				"\tState: {1}" +
				"\t\tDuration: {2}", myId, activity, duration / 1000);
		return retVal;
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


