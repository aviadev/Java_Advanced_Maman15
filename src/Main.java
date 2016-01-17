
import java.util.IllegalFormatConversionException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
        {
            int numOfThreads, numOfLoops;

	        DataBase db = new DataBase();

            numOfThreads = getAndValidateInput("Threads");

            db.initializeListWithRandomNumbers(numOfThreads);
	        numOfLoops = getAndValidateInput("Loops");

	        Logic logic = new Logic (db, numOfThreads ,numOfLoops);
            logic.doLoops();



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
				if (retVal < 1) retVal = 1;
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
