import java.util.ArrayList;
import java.util.Collections;

public class CoinSorter {
	protected ArrayList<Integer> coinList;
	protected ArrayList<Integer> outputList;
	private String currency = "";
	private int minValue;
	private int maxValue;
	//private int excludedCoin;
	
	/*	Constructors	*/
	
	// Constructor with various inputs
	public CoinSorter(String currencyIn, int minValueIn, int maxValueIn, int[] listIn)
	{
		// Create new ArrayList objects
		coinList = new ArrayList<Integer>();
		outputList = new ArrayList<Integer>();
		// Fills the coinList ArrayList with the coins from the input array listIn
		setCoinList(listIn);
		// Makes sure the list of coins is in descending order else the multiple coin algorithm will break
		Collections.sort(coinList, Collections.reverseOrder());
		// Sets the parameters to the ones passed by the constructor
		setCurrency(currencyIn);
		setMinValue(minValueIn);
		setMaxValue(maxValueIn);
	}
	// Default constructor
	public CoinSorter()
	{
		// Create new ArrayList objects
		coinList = new ArrayList<Integer>();
		outputList = new ArrayList<Integer>();
		// Sets the default coins to be used when no array of coins is supplied via the other constructor
		int[] defaultCoins = {200, 100, 50, 20, 10, 1};
		// Fills the coinList ArrayList with the default coins
		setCoinList(defaultCoins);
		// Makes sure the list of coins is in descending order else the multiple coin algorithm will break
		Collections.sort(coinList, Collections.reverseOrder());
		// Sets the parameters to the defaults suggested in the brief
		setCurrency("£");
		setMinValue(0);
		setMaxValue(10000);
	}
	
	/*	Getters	*/
	
	// Get coin list
	public String printCoinList()
	{
		// Declare local String variable for output
		String coinListString = "";
		// Loop to iterate through the ArrayList, concatenating the result into the output String
		for (int i = 0; i < coinList.size(); i++)
		{
			coinListString += coinList.get(i)  + currency + " ";
		}
		// return the output String
		return coinListString;
	}
	// Get coin
	public int getCoin(int index)
	{
		return coinList.get(index);
	}
	// Get Currency
	public String getCurrency()
	{
		return currency;
	}
	// Get Minimum input value
	public int getMinValue()
	{
		return minValue;
	}
	// Get Maximum input value
	public int getMaxValue()
	{
		return maxValue;
	}
	
	/*	Setters	*/
	
	// Set coin list
	public void setCoinList(int[] coinListIn)
	{
		// Test to see if the ArrayList is empty, if not the ArrayList must be emptied first
		if (coinList.size() != 0) coinList.clear();
		// Iterates through all items in the input Array and adds them to the empty ArrayList
		for (int i = 0; i < coinListIn.length; i++)
		{
			coinList.add(coinListIn[i]);
		}
	}
	// Set Currency
	public void setCurrency(String currencyIn)
	{
		currency = currencyIn;
	}
	// Set Minimum total value
	public void setMinValue(int minValueIn)
	{
		minValue = minValueIn;
	}
	// Set maximum total value
	public void setMaxValue(int maxValueIn)
	{
		maxValue = maxValueIn;
	}
	
	/*	Coin List Operations */
	
	// Add coin to list and sort
	public void addCoin(int coinIn)
	{
		coinList.add(coinIn);
		Collections.sort(coinList, Collections.reverseOrder());
	}
	// Attempt to remove coin from the list, returning a boolean value if successful or not
	public boolean removeCoin(int coinIn)
	{
		// Check to see if the coin is a valid coin 
		if (!validateExcludedCoin(coinIn)) return false;
		else
		{
			// Temporary variable used to store the excluded coin's index within the list
			int excludedCoinIndex = 0;
			// Loop to iterate through each item in the coinList and set excludedCoinIndex to be the index of the excluded coin
			for (int i = 0; i < coinList.size(); i++)
			{
				if (coinList.get(i) == coinIn) excludedCoinIndex = i;
			}
			// Remove the coin from the list
			coinList.remove(excludedCoinIndex);
			return true;
		}
	}
	
	/*	Single coin calculator	*/
	
	public String coinCalculate(int value, int coin)
	{
		return value/coin + " "  + coin + currency + " coins and " + value%coin + currency + " remaining\n";
	}
	
	/*	Validation tools	*/
	
	// Validate total value to make sure it is within the bounds set by the min and max value
	public boolean validateTotalValue(int totalValueIn)
	{
		if(totalValueIn >= minValue && totalValueIn <= maxValue) return true;
		else return false;			
	}
	// Validate excluded coin to make sure it is contained in the coin list
	public boolean validateExcludedCoin(int excludedCoinIn)
	{
		if(coinList.contains(excludedCoinIn)) return true;
		else return false;
	}
	
	/*	Multiple coin calculator	*/
	
	public String multiCoinCalculate(int value, int excludedCoin)
	{
		// Check if excluded coin is a valid coin and return an error message if not
		if (!validateExcludedCoin(excludedCoin))
		{
			return "Error";
		}
		else 
		{
			// Output list is cleared to receieve the output information
			outputList.clear();
			// Excluded coin is removed from the coin list via the remove method
			removeCoin(excludedCoin);
			// Output string is locally defined
			String coinCalculateOutput = "";		
			// algorithm to sort the remaining coins, iterating through each coin in the coin list
			for (int j = 0; j < coinList.size(); j++)
			{
				// The number of each coin is found via integer division, and recorded to the output list
				outputList.add(value / coinList.get(j));
				// The remainder is found via the modulo operator and passed back to the value variable
				value = value % coinList.get(j);
			}
			// algorithm to return the output ArrayList as a String
			for (int k = 0; k < outputList.size(); k++)
			{
				coinCalculateOutput += outputList.get(k) + "\t" + coinList.get(k) + currency + " coins\n";
			}
			addCoin(excludedCoin);
			return coinCalculateOutput;
		}
	}
	
	/*Display program configurations*/
	
	public String displayProgramConfigs()
	{
		return "*** Program Configuration ***\n"
				+ "Current Coin List:\t" + printCoinList()
				+ "\nMinimum total value:\t" + minValue
				+ "\nMaximum total value:\t" + maxValue
				+ "\nCurrency:\t\t" + currency;
	}
}
