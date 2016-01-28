
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
	    {
		    int numOfThreads = getAndValidateInput("Threads");
		    int numOfLoops = getAndValidateInput("Loops");

		    Threads threads = new Threads(numOfThreads);

		    for (int i = 0; i <= numOfLoops; i++) //One time more for representing initial state
			    {
			    //   System.out.println("\n---About to refresh console---\n");
			    threads.refreshConsole(i);
			    //  System.out.println("\n---Done refreshing console---\n");
			    threads.process();

			    }
		    //    System.out.println("\n---Done process method---\n");
	    }


private static int getAndValidateInput(String inputType)
	{
		Scanner scanner = new Scanner(System.in);
		String userInput = null;
		int retVal = 0;
		boolean isSuccessful = false;
		System.out.println("Please enter the number of " + inputType + ":\n");
		userInput = scanner.nextLine();

		while (!isSuccessful)
			{
			try
				{
				retVal = Integer.parseInt(userInput);

				isSuccessful = true;
				}
			catch (NumberFormatException ex)
				{
				System.out.println("Invalid input! Please enter a number only.\n");
				userInput = scanner.nextLine();
				}

			}
		return retVal;
	}
}
