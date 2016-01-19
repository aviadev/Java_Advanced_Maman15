import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aviad on 15/01/2016.
 */
public class Data
{

	Random random;
	protected ArrayList <MyThread> threadArrayList;

public Data()
	{
		threadArrayList = new ArrayList<MyThread>();
		random = new Random();
	}

public Data(ArrayList threadArrayList)
	{
		this.threadArrayList = threadArrayList;
		random = new Random();
	}

protected int size () {return threadArrayList.size();}



protected void initializeListWithRandomNumbers(int numOfThreads)
	{
		for (int i = 0; i < numOfThreads; i++)
			{
			threadArrayList.add(i, new MyThread(random.nextInt(101)));
			}
	}


@Override
public String toString()
	{
		if (threadArrayList == null || threadArrayList.size() == 0) return "";
		String res = "";
		for (int i = 0; i < threadArrayList.size(); i++)
			{
			res += threadArrayList.get(i).getThreadId()+ "\t";
			}
		return res;
	}



}
