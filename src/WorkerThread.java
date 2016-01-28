

/**
 * Created by aviad on 20/01/2016.
 */
public class WorkerThread extends Thread
{

private int rndId;
private int leftNeighbourIndex;
private int rightNeighbourIndex;
private int index;

public WorkerThread(int leftNeighbourIndex, int currentIndex, int rightNeighborIndex, int rndId, int size)
	{
		this.rndId = rndId;
		this.index = currentIndex;

		if (leftNeighbourIndex < 0)
			{
			this.leftNeighbourIndex = size - 1;
			this.rightNeighbourIndex = rightNeighborIndex;
			}
		if (rightNeighborIndex > size - 1)
			{
			this.rightNeighbourIndex = 0;
			this.leftNeighbourIndex = size - 2;
			}
		else if (leftNeighbourIndex >= 0 && rightNeighborIndex < size)
			{
			this.rightNeighbourIndex = rightNeighborIndex;
			this.leftNeighbourIndex = leftNeighbourIndex;
			}
	}

protected int getRndId() {return rndId;}

protected int getIndex() {return index;}

protected int getLeftNeighbourIndex() {return leftNeighbourIndex;}

protected int getRightNeighbourIndex() {return rightNeighbourIndex;}

protected void setId(int id) {this.rndId = id;}

@Override
public void run()
	{
		try
			{
			Threads threads = new Threads(this);
			} catch (Exception e)
			{
			e.printStackTrace();
			}
	}
}

