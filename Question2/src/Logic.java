import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logic
{

private ArrayList<Pylosof> pylosofs;
private ArrayList<Stick> sticks;
private HashMap<Pylosof, List<Stick>> hashMap;
private TreeMap<Pylosof, List<Stick>> sortedMap;

protected Logic(ArrayList<Pylosof> pylosofs, ArrayList<Stick> sticks)
	{
		hashMap = new HashMap<>();
		this.pylosofs = pylosofs;
		this.sticks = sticks;
	}

protected void initiateEachPylosofWithSticks()
	{
		for (int i = 0; i < 4; i++) //Last Pylosof is using stick #5 and #1.
			{
			hashMap.put(pylosofs.get(i), sticks.subList(i, i + 2));
			}

		Pylosof lastPylosof = pylosofs.get(4);
		ArrayList<Stick> lastFirstSticks = new ArrayList();
		lastFirstSticks.add(0, sticks.get(4));
		lastFirstSticks.add(1, sticks.get(0));
		hashMap.put(lastPylosof, lastFirstSticks);
		sortedMap = sortList(hashMap);

	}

private TreeMap<Pylosof, List<Stick>> sortList(HashMap<Pylosof, List<Stick>> hashMap)
	{
		return new TreeMap<>(hashMap);
	}

protected void run() throws InterruptedException
	{
		long tStart = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Random random = new Random();

		for (Pylosof p : sortedMap.keySet())
			{
			//refreshConsole();
			switch (p.getActivity())
				{
				case Wait:
				{
				if (canGetTwoSticks(p)) //Can eat
					{
					p.setActivityAndItsDuration(Pylosof.Activity.Eat, random.nextInt(60) * 1000);
					setSticksAsOccupied(p);
					executorService.submit(p);
					break;
					}
				break;
				}

				case Eat:
				{
				releasePylosofSticks(p);
				p.setActivityAndItsDuration(Pylosof.Activity.Think, random.nextInt(60) * 1000);
				executorService.submit(p);
				break;
				}

				case Think:
				{
				p.setActivityAndItsDuration(Pylosof.Activity.Wait, 0);
				executorService.submit(p);
				break;
				}
				}
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println(elapsedSeconds);
			}
	}

private boolean canGetTwoSticks(Pylosof p)
	{
		return sticks.stream().filter(stick -> hashMap.get(p).contains(stick)).allMatch(Stick::isAvailable);
	}

private void setSticksAsOccupied(Pylosof p)
	{
		//	System.err.println(p.getName() + " Is about to set sticks as occupied...");
		sticks.stream().filter(stick -> hashMap.get(p).contains(stick)).forEach(Stick::setBusy);
		//	System.err.println(p.getName() + " set occupied as occupied successfully.");

	}

private void releasePylosofSticks(Pylosof p)
	{
		//System.err.println(p.getName() + " Is about to release the sticks...");
		sticks.stream().filter(stick -> hashMap.get(p).contains(stick)).forEach(Stick::setAvailable);
		//	System.err.println(p.getName() + " released the sticks successfully.");
	}

protected void setAllSticksAsAvailable()
	{
		sticks.forEach(Stick::setAvailable);
	}

protected void setAllPylosofsToWaitState()
	{
		pylosofs.forEach(pylosof -> pylosof.setActivity(Pylosof.Activity.Wait));
	}

protected synchronized void refreshConsole()
	{

		if (sortedMap == null || sortedMap.size() == 0) System.out.println("Null or Empty");

		else
			{

			System.out.println();
			System.out.println("\nCurrently Eating:\n-----------------\n");
			sortedMap.keySet().stream().filter(p1 -> p1.getActivity() == Pylosof.Activity.Eat).forEach(
					System.out::println);

			System.out.println("\nCurrently Thinking:\n-----------------");
			sortedMap.keySet().stream().filter(p1 -> p1.getActivity() == Pylosof.Activity.Think).forEach(
					System.out::println);

			System.out.println("\nCurrently Waiting:\n----------------\n");
			sortedMap.keySet().stream().filter(p1 -> p1.getActivity() == Pylosof.Activity.Wait).forEach(
					System.out::println);

			System.out.println("\n---------------------------------------------------");
			}
	}
}

