import java.util.ArrayList;

public class Main
{

public static void main(String[] args) throws InterruptedException
	{
		ArrayList<Pylosof> pylosofs = new ArrayList<>();
		ArrayList<Stick> sticks = new ArrayList<>();

		for (int i = 0; i < 5; i++)
			{
			pylosofs.add(new Pylosof("Pylosof #" + (i + 1), i + 1));
			sticks.add(new Stick(i + 1));
			}

		Logic logic = new Logic(pylosofs, sticks);
		logic.initiateEachPylosofWithSticks();
		logic.setAllSticksAsAvailable();
		logic.setAllPylosofsToWaitState();
		logic.run();
	}
}
