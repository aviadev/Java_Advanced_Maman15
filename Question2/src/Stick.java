import java.text.MessageFormat;

/**
 * Created by aviad on 28/01/2016.
 */
public class Stick
{

private int id;
private boolean isAvailable;

public Stick(int id)
	{
		this.id = id;
	}

protected synchronized boolean isAvailable() {return isAvailable;}

public synchronized void setBusy()
	{
		isAvailable = false;
	}

public synchronized void setAvailable()
	{
		isAvailable = true;
	}

public synchronized String toString()
	{
		String retVal = MessageFormat.format("\nStick id: {0}" + "\t\tAvailability: {1}", id, isAvailable);
		return retVal;
	}
}
