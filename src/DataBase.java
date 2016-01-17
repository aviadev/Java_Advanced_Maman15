import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aviad on 15/01/2016.
 */
public class DataBase
{

	Random random;
	protected ArrayList <Integer> db;

public DataBase ()
	{
		db = new ArrayList<>();
		random = new Random();
	}

public DataBase (ArrayList db)
	{
		this.db = db;
		random = new Random();
	}

protected int size () {return db.size();}

@Override
public String toString()
	{
		if (db == null || db.size() == 0) return "";
		String res = "";
		for (int i = 0; i < db.size(); i++)
			{
				 res += db.get(i).toString() + "\t";
			}
		return res;
	}

protected void initializeListWithRandomNumbers(int numOfThreads)
	{
		for (int i = 0; i < numOfThreads; i++)
			{
				db.add(i, random.nextInt(101));
			}
	}



}
