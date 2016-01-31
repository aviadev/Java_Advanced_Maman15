import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by aviad on 28/01/2016.
 */
public class Logic
{

private Pylosof pylosof;
private ArrayList<Pylosof> pylosofs;
private ArrayList<Stick> sticks;
private HashMap<Pylosof, List<Stick>> hashMap;
private TreeMap<Pylosof, List<Stick>> sortedMap;
private ScheduledExecutorService eatingScheduler;
private ScheduledExecutorService thinkingScheduler;
private ScheduledExecutorService scheduler;
private ExecutorService executorService;

public Logic(ArrayList<Pylosof> pylosofs, ArrayList<Stick> sticks)
	{
		hashMap = new HashMap<>();
		this.pylosofs = pylosofs;
		this.sticks = sticks;
	}

public Logic(Pylosof pylosof)
	{
		this.pylosof = pylosof;
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

public void run() throws InterruptedException
	{
		long tStart = System.currentTimeMillis();
		scheduler = Executors.newScheduledThreadPool(5);
		executorService = Executors.newFixedThreadPool(5);
		Random random = new Random();

		while (true)
			{

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
						//scheduler.execute(p);
						//scheduler.scheduleAtFixedRate(p, 0, p.getDuration()/1000, TimeUnit.SECONDS);
						//scheduler.schedule(p, p.getDuration()/1000, TimeUnit.SECONDS);
						//scheduler.submit(p);
						executorService.submit(p);
						break;
						}
					break;
					}

					case Eat:
					{
					//pylosof.wait();
					releasePylosofSticks(p);
					p.setActivityAndItsDuration(Pylosof.Activity.Think, random.nextInt(60) * 1000);
					//		scheduler.execute(p);
					//scheduler.scheduleAtFixedRate(p, 0, p.getDuration()/1000, TimeUnit.SECONDS);
					//scheduler.schedule(p, p.getDuration()/1000, TimeUnit.SECONDS);
					// scheduler.submit(p);
					executorService.submit(p);
					break;
					}

					case Think:
					{

					//p.wait();
					p.setActivityAndItsDuration(Pylosof.Activity.Wait, 0);
					//scheduler.execute(p);
					//scheduler.schedule(p, 0, TimeUnit.SECONDS);
					//scheduler.submit(p);
					executorService.submit(p);
					break;
					}
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
		return sticks.stream().filter(stick -> hashMap.get(p).contains(stick)).allMatch(stick -> stick.isAvailable());
	}

private void setSticksAsOccupied(Pylosof p)
	{
		//	System.err.println(p.getName() + " Is about to set sticks as occupied...");
		sticks.stream().filter(stick -> hashMap.get(p).contains(stick)).forEach(stick -> stick.setBusy());
		//	System.err.println(p.getName() + " set occupied as occupied successfully.");

	}

private void releasePylosofSticks(Pylosof p)
	{
		//System.err.println(p.getName() + " Is about to release the sticks...");
		sticks.stream().filter(stick -> hashMap.get(p).contains(stick)).forEach(stick1 -> stick1.setAvailable());
		//	System.err.println(p.getName() + " released the sticks successfully.");
	}

public void setAllSticksAsAvailable()
	{
		sticks.forEach(stick1 -> stick1.setAvailable());
	}

public void setAllPylosofsToWaitState()
	{
		pylosofs.forEach(pylosof -> pylosof.setActivity(Pylosof.Activity.Wait));
	}

/*protected synchronized void refreshConsole()
	{

		if (sortedMap == null || sortedMap.size() == 0) System.out.println("Null or Empty");

		else
			{
			System.out.println("\nCurrently Waiting:\n");
			sortedMap.keySet().stream()
					.filter(p1 -> p1.getActivity() == Pylosof.Activity.Wait)
					.forEach(System.out::print);

			System.out.println("\nCurrently Eating:\n");
			sortedMap.keySet().stream()
					.filter(p1 -> p1.getActivity() == Pylosof.Activity.Eat)
					.forEach(System.out::print);

			System.out.println("\nCurrently Thinking:\n");
			sortedMap.keySet().stream()
					.filter(p1 -> p1.getActivity() == Pylosof.Activity.Think)
					.forEach(System.out::print);
			System.out.println("\n---------------------------------------------------");
			}
	}*/

/*protected synchronized void refreshConsole()
	{

		if (sortedMap == null || sortedMap.size() == 0) System.out.println("Null or Empty");

		else
			{
				sortedMap.keySet()
						.stream()
						.collect(groupingBy(p-> p.getActivity(),  toList()));
						//.forEach((activity, pylosofs1) -> System.out::println;)
						System.out.println(sortedMap);


			}
	}*/

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

