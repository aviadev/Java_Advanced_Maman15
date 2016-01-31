import java.text.MessageFormat;

public class Stick
{

private int id;
private boolean isAvailable;

protected Stick(int id)
	{
		this.id = id;
	}

protected synchronized boolean isAvailable() {return isAvailable;}

protected synchronized void setBusy()
	{
		isAvailable = false;
	}

protected synchronized void setAvailable()
	{
		isAvailable = true;
	}

public synchronized String toString()
	{
		return MessageFormat.format("\nStick id: {0}" + "\t\tAvailability: {1}", id, isAvailable);
	}
}
