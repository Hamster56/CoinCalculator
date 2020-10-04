import java.util.Scanner;

public class CoinSorterTest {

	public static void main(String[] args) {
		int[] coinListArray = {200, 100, 50, 20, 10, 1};				// Coin Array 
		CoinSorter c = new CoinSorter("p", 0, 10000, coinListArray);	// Constructor
		Scanner sc = new Scanner(System.in);
		int choiceMain = 0;						// used for the main menu switch
		boolean mainMenuTest = false;			// used to validate user input on main menu
		int choiceDetails = 0;					// used for the details sub menu switch
		boolean detailsMenuTest = false;		// used for details sub menu input validation
		int totalValue = 0;						// used for the total value input in both simple and multi coin sorter
		boolean totalValueTestSimple = false;	// used for total value input validation on simple sort
		boolean totalValueTestMulti = false;	// used for total value input validation on multi sort
		int coinSize = 0;						// used for coin size in simple coin sorter
		boolean coinSizeTest = false;			// used for coin size input validation in simple coin sorter
		int coinExcluded = 0;					// used for coin excluded from multi coin sorter
		boolean coinExcludedTest = false;		// used for coin excluded from multi coin sort input validation
		boolean minCoinTest = false;			// used for validating input on the min coin value in the settings sub menu
		boolean maxCoinTest = false;			// used for validating input on the max coin value in the settings sub menu

		do	{		// Main loop which repeats the menu until option 6 is selected to quit
			do	{	// Loop for validating the choiceMain input 
				// Print menu list
				mainMenuTest = false;
				System.out.println("***Coin Sorter Main Menu***\n"
						+ "1 - Coin Calculator\n"
						+ "2 - Multipule Coin Calculator\n"
						+ "3 - Print Coin List\n"
						+ "4 - Set Details\n"
						+ "5 - Display program configurations\n"
						+ "6 - Quit the program");			
				//Input menu option
				if (sc.hasNextInt()) {		// check if the next input is an int
				choiceMain = sc.nextInt();	// assign the int to choiceMain
				sc.nextLine();				// advances Scanner to prevent input errors
				mainMenuTest = true;		// sets condition to true to break the loop
				}
				else	{
					System.out.println("Invalid entry, try again.\n");
					sc.nextLine();			// advances Scanner to prevent input errors
				}
			} while (!mainMenuTest);
						
			// Switch statement for choice
			switch (choiceMain) {
			case 1:		// Coin Calculator
				System.out.println("\n***Coin Calculator***");
				do	{	// Loop for input validation
					totalValueTestSimple = false;
					System.out.print("Enter total value:\t");
					if (sc.hasNextInt()) {			// Check if the next input is an int
						totalValue = sc.nextInt();	// Assign the int to totalValue variable
						sc.nextLine();				// Advances the Scanner to prevent input errors
						if (c.validateTotalValue(totalValue))	{	// Check if the inputted int passes the total value validation test method
							totalValueTestSimple = true;			// Sets condition to true to break the loop
						}
						else	{
							// error message if the int does not pass the validation test method
							System.out.println("Value not within the bounds of " + c.getMinValue() + " and " + c.getMaxValue() + ". Please try again.\n");
						}
					}
					else {
						// Error message for if the Scanner does not detect an int
						System.out.println("Incorrect input type, please try again.\n");
						sc.nextLine();	// Advances Scanner to prevent input errors
					}
				} while (!totalValueTestSimple);			
				do	{	// Loop for input validation
					coinSizeTest = false;	// Set condition to false to reset the loop
					System.out.print("Enter Coin deomination:\t");	
					if (sc.hasNextInt()) {	// Test if the input is an int
						coinSize = sc.nextInt();	// Set the scanned int to the variable
						sc.nextLine();				// Advance Scanner to next line to prevent input errors
						if (coinSize > 0) {			// Test if the coin is a positive number
							coinSizeTest = true;		// Set condition to true to break the loop
						}
						else System.out.println("Please enter a positive integer value.");
					}
					else {
						// Error message for if the input is not an int
						System.out.println("Incorrect input type, please try again.\n");
						sc.nextLine();	// Advance Scanner to next line to prevent input errors
					}
				} while (!coinSizeTest);
				// Call the coinCalculate method and print
				System.out.println("\n" + c.coinCalculate(totalValue, coinSize));
				break;
			case 2:		// Multiple Coin Calculator
				System.out.println("***Multiple Coin Calculator***");
				do	{	// Loop for input validation
					totalValueTestMulti = false;	// Set condition to false to reset the loop
					System.out.print("Enter total value:\t");
					if (sc.hasNextInt()) {			// Test if the input is an int
						totalValue = sc.nextInt();	// Set variable to input
						sc.nextLine();				// Advance Scanner to prevent input errors
						// Check if the new variable passes the validateTotalValue method
						if (c.validateTotalValue(totalValue))	{
							totalValueTestMulti = true;	// Set condition to true to break the loop
						}
						// Error message for when the input is of correct type, but is out of bounds
						else System.out.println("Value not within the bounds of " + c.getMinValue() + " and " + c.getMaxValue() + ". Please try again.\n");
					}
					else {
						// Error message for when the input is not of correct type
						System.out.println("Incorrect input type, please try again.\n");
						sc.nextLine();
					}
				} while (!totalValueTestMulti);			
				do	{
					coinExcludedTest = false;
					System.out.print("Enter coin to exclude:\t");
					if (sc.hasNextInt()) {
						coinExcluded = sc.nextInt();
						sc.nextLine();
						if (c.validateExcludedCoin(coinExcluded)) {
							coinExcludedTest = true;
						}
						else System.out.println("Coin not in circulation. Please try again.\n");
					}
					else {
						System.out.println("Incorrect input type, please try again.\n");
						sc.nextLine();
					}
				} while (!coinExcludedTest);
				System.out.println(c.multiCoinCalculate(totalValue, coinExcluded));
				break;
			case 3:		// Print Coin List
				System.out.println(c.printCoinList());
				break;
			case 4:		// Set Details
				do 	{
					do	{
						detailsMenuTest = false;
						// Display sub menu options
						System.out.println("***Set Details sub menu***\n\n"
								+ "1 - Set currency\n"
								+ "2 - Set minimum coin input value\n"
								+ "3 - Set maximum coin input value\n"
								+ "4 - Return to main menu");
						if (sc.hasNextInt()) {					
							// Input sub menu option
							choiceDetails = sc.nextInt();
							sc.nextLine();
							// switch statement for sub menu
							switch (choiceDetails)	{
							case 1:		// Set currency
								System.out.println("*** Set Currency ***");
								c.setCurrency(sc.next());
								System.out.println("Currency set to:\t" + c.getCurrency());
								break;
							case 2:		// Set minimum coin input value
								do {
									minCoinTest = false;
									System.out.println("*** Set minimum coin input value ***");
									if (sc.hasNextInt()) {
										c.setMinValue(sc.nextInt());
										sc.nextLine();
										minCoinTest = true;
									}
									else {
										System.out.println("Incorrect input type, please try again.\n");
										sc.nextLine();
									}
								} while (!minCoinTest);
								System.out.println("Minimum value set to:\t" + c.getMinValue());
								break;
							case 3:		// Set maximum coin input value
								do	{
									maxCoinTest = false;
									System.out.println("***Set maximum coin input value ***");
									if (sc.hasNextInt()) {
										c.setMaxValue(sc.nextInt());
										sc.nextLine();
										maxCoinTest = true;
									}
									else {
										System.out.println("Incorrect input type, please try again.\n");
										sc.nextLine();
									}
								} while (!maxCoinTest);		
								System.out.println("Maximum value set to:\t" + c.getMaxValue());
								break;
							case 4:		// Return to main menu
								detailsMenuTest = true;
								break;
							}
						}
						else {
							System.out.println("Incorrect input type, please try again.\n");
							sc.nextLine();
						}
					} while (!detailsMenuTest);
				} while (choiceDetails != 4);
				break;
			case 5:		// Display program configurations
				System.out.println(c.displayProgramConfigs());
				System.out.println();
				break;
			case 6:		// Quit the program
				System.out.println("Goodbye!");
				break;
			default:	// Default
				System.out.println("Please enter a number between 1 and 6.");
				break;				
			}
		}while (choiceMain != 6);
		sc.close();
	}
}
