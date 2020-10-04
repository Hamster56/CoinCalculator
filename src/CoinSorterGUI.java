import java.util.ArrayList;
import java.util.Collections;

public class CoinSorterGUI extends CoinSorter {
	// Constructor
	public CoinSorterGUI(String currencyIn, int minValueIn, int maxValueIn, int[] listIn)	{
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
	// Method to gain size of coin list
	public int coinListLength()	{
		return coinList.size();
	}
}
