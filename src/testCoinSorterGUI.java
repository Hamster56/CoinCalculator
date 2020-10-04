import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color; 
import javafx.scene.text.Font; 
import javafx.stage.Stage; 
import javafx.geometry.Pos;


public class testCoinSorterGUI extends Application {
	int[] coinListArray = {200, 100, 50, 20, 10, 1};				// Coin Array 
	CoinSorterGUI c = new CoinSorterGUI("p", 0, 10000, coinListArray);	// Constructor
	// Declaring the scenes before constructing them is necessary for having scene switching actions occur earlier than the scene creation in the code
	Scene simpleCoinCalcScene, mainMenuScene, multiCoinCalcScene, displayCoinListScene, programSettingScene; 
	
	@Override
	public void start(Stage stage)	{
		/* Main menu scene */
		
		// Labels
		Label mainMenuTitleLabel = new Label("Coin Sort Calculator");	// Main menu title
		mainMenuTitleLabel.setFont(Font.font("Arial", 40));
		mainMenuTitleLabel.setTextFill(Color.BLACK);
		// Buttons
		// Coin Calculator
		Button coinCalculatorButton = new Button();
		coinCalculatorButton.setText("Coin Calculator");
		coinCalculatorButton.setOnAction(e -> {
			stage.setScene(simpleCoinCalcScene);	// Changes scene to the simple coin calculator scene
			stage.setTitle("Coin Calculator");		// Changes the title to correspond with the scene change
		});
		// Multi Coin calculator
		Button multiCoinCalculatorButton = new Button();
		multiCoinCalculatorButton.setText("Multiple Coin Calculator");
		multiCoinCalculatorButton.setOnAction(e -> {
			stage.setScene(multiCoinCalcScene);			// Changes scene to the multi coin calc scene
			stage.setTitle("Multi Coin Calculator");	// Changes the title to correspond with the scene change
		});
		// Display coin list
		Button displayCoinListButton = new Button();
		displayCoinListButton.setText("Display Coin List");
		displayCoinListButton.setOnAction(e -> {
			stage.setScene(displayCoinListScene);	// Changes the scene to the coin list scene
			stage.setTitle("Coin List");			// Changes the title to correspond with the scene change
		});
		// Enter settings menu
		Button settingsMenuButton = new Button();
		settingsMenuButton.setText("Program settings");
		settingsMenuButton.setOnAction(e -> {
			stage.setScene(programSettingScene);	// Changes the scene to the settings sub menu scene
			stage.setTitle("Program Settings");		// Changes the title to correspond with the scene change
		});
		// Quit
		Button quitButton = new Button();
		quitButton.setText("Quit");
		quitButton.setOnAction(e -> Platform.exit());	// Stops the program
		// HBox with the buttons as elements
		HBox mainMenuBox = new HBox(5);
		mainMenuBox.setAlignment(Pos.CENTER);
		mainMenuBox.getChildren().addAll(coinCalculatorButton, multiCoinCalculatorButton, displayCoinListButton, settingsMenuButton, quitButton);
		// VBox with the previous HBox and the title as elements 
		VBox mainMenu = new VBox(75);
		mainMenu.setAlignment(Pos.CENTER);        
		mainMenu.getChildren().addAll(mainMenuTitleLabel, mainMenuBox);
		// Create scene with the VBox
		mainMenuScene = new Scene(mainMenu, 750, 220); 
		
		/* Coin Calc Scene */
		
		// Labels
		Label coinCalcCoinLabel = new Label("Coin Size");		// Label for the coin size input
		coinCalcCoinLabel.setTextFill(Color.BLACK);
		coinCalcCoinLabel.setFont(Font.font("Arial", 15));
		Label coinCalcValueLabel = new Label("Total Value");	// Label for the value input
		coinCalcValueLabel.setTextFill(Color.BLACK);
		coinCalcValueLabel.setFont(Font.font("Arial", 15));
		HBox coinCalcInputLabels = new HBox(50);				// HBox to group the labels together
		coinCalcInputLabels.setAlignment(Pos.CENTER);
		coinCalcInputLabels.getChildren().addAll(coinCalcCoinLabel, coinCalcValueLabel);
		// Text Fields
		TextField coinCalcCoinInput = new TextField();			// Coin size input
		coinCalcCoinInput.setMaxWidth(50);
		TextField coinCalcValueInput = new TextField();			// Value input
		coinCalcValueInput.setMaxWidth(50);
		HBox coinCalcInputs = new HBox(50);						// HBox to group the inputs together
		coinCalcInputs.setAlignment(Pos.CENTER);
		coinCalcInputs.getChildren().addAll(coinCalcCoinInput, coinCalcValueInput);
		// Display area
		TextArea coinCalcDisplay = new TextArea();    			// Output area    
		coinCalcDisplay.setEditable(false);         
		coinCalcDisplay.setMinSize(250,60);         
		coinCalcDisplay.setMaxSize(250,60); 
		// Buttons
		Button coinCalcMenuReturn = new Button();				// Button to return to main menu
		coinCalcMenuReturn.setText("Return");
		coinCalcMenuReturn.setOnAction(e -> {
			stage.setScene(mainMenuScene);						// Change scene to main menu
			stage.setTitle("Main Menu");						// Changes the title to correspond with the scene change
		});
		Button coinCalcGo = new Button();
		coinCalcGo.setText("Calculate");						// Button to calculate the output given the two inputs
		coinCalcGo.setOnAction(e -> {
			if(coinCalcCoinInput.getText().isEmpty() || coinCalcValueInput.getText().isEmpty())	{	// Test if the inputs are non-empty                     
				coinCalcDisplay.setText("Please input a value for both fields");       				// Error message returned if either input is empty                
			}
			else	{  	// Run if both inputs are non-empty   
				try	{	// Try block
					if (Integer.parseInt(coinCalcCoinInput.getText()) <= 0) {	// Test if the coin entered is less than 0 (an impossible coin)
						coinCalcDisplay.setText("Coin value must be positive integer number");	// Error message if coin is less than 0
					}
					else if (!c.validateTotalValue(Integer.parseInt(coinCalcValueInput.getText())))	{		// Test if the non empty value input will pass the validateTotalValue method
						coinCalcDisplay.setText("Please enter a value \nbetween\n" + c.getMinValue() + " and " + c.getMaxValue());  // Error message if the value input fails the validation test                     
					}
					else coinCalcDisplay.setText(c.coinCalculate(Integer.parseInt(coinCalcValueInput.getText()), Integer.parseInt(coinCalcCoinInput.getText())));	// set the output to display the output of the coin calc method
				} catch (NumberFormatException e1) {	// Catches number format exception errors
					coinCalcDisplay.setText("Input values must be integer numbers");	// Error message for when the catch block runs on number format exception error
				}
			}                                                                                                           
		});
		// HBox to contain the buttons
		HBox coinCalcButtons = new HBox(50);
		coinCalcButtons.setAlignment(Pos.CENTER);
		coinCalcButtons.getChildren().addAll(coinCalcMenuReturn, coinCalcGo);
		// VBox
		VBox simpleCoinCalcMenu = new VBox(25);
		simpleCoinCalcMenu.setAlignment(Pos.CENTER);
		simpleCoinCalcMenu.getChildren().addAll(coinCalcInputLabels, coinCalcInputs, coinCalcDisplay, coinCalcButtons);
		// Create the scene
		simpleCoinCalcScene = new Scene(simpleCoinCalcMenu, 400, 250);
		
		/* Multi Coin Calculator*/
		
		// Labels
		Label multiCoinCalcLabel = new Label("Multi Coin Calculator");	// Title label
		multiCoinCalcLabel.setTextFill(Color.BLACK);
		multiCoinCalcLabel.setFont(Font.font("Arial", 25));
		Label multiCoinCalcInputLabel = new Label("Total Value");		// Label for total value input
		// Display area
		TextArea multiCoinCalcDisplay = new TextArea();        			// Output display area
		multiCoinCalcDisplay.setEditable(false);         
		multiCoinCalcDisplay.setMinSize(100,100);         
		multiCoinCalcDisplay.setMaxSize(130,120);
		// Text Field
		TextField multiCoinCalcValueInput = new TextField();			// Total value input field
		multiCoinCalcValueInput.setMaxWidth(70);
		// Buttons
		HBox multiCoinCalcCoins = new HBox(5);							// HBox to store buttons
		multiCoinCalcCoins.setAlignment(Pos.CENTER);
		for (int i = 0; i < c.coinListLength(); i++)	{				// Loop to create an arbitrary number of buttons to correspond with the number of coins in the coin list
			Button coinButton = new Button();							// Button creation
			coinButton.setText("Remove " + Integer.toString(c.getCoin(i)) + c.getCurrency() + " coin");			// Set button text based on the size of coin
			final int innerI = i;										// Final variable created to get around limitations of passing variables to methods within a lambda expression
			coinButton.setOnAction(e -> {
				if(multiCoinCalcValueInput.getText().isEmpty())        // Test if the input field is empty
				{                               
					multiCoinCalcDisplay.setText("Please a value into the total value field");	// Error message if input field is empty           
				}
				else                        							// Run if field is non-empty
				{     
					try {												// Try-catch block for expected int input
						if (!c.validateTotalValue(Integer.parseInt(multiCoinCalcValueInput.getText()))){	// Number format exception expected to be thrown here
							multiCoinCalcDisplay.setText("Please enter a value \nbetween\n" + c.getMinValue() + " and " + c.getMaxValue());	// Error message displayed if validation failed
						}
						// Set output to display the output string of the multiCoinCalculate method
						else multiCoinCalcDisplay.setText(c.multiCoinCalculate(Integer.parseInt(multiCoinCalcValueInput.getText()), c.getCoin(innerI))); 
					} catch (NumberFormatException e1) {	// Catch block to catch number format exception errors
						multiCoinCalcDisplay.setText("Input value must be\ninteger numbers");	// Error message if input is not an integer value and throws an exception
					}
				}   
			});
			multiCoinCalcCoins.getChildren().add(coinButton);	// Add new button to the HBox created above before finishing the loop
		}
		Button multiCoinCalcMenuReturn = new Button();			// Button to return to main menu
		multiCoinCalcMenuReturn.setText("Return");
		multiCoinCalcMenuReturn.setOnAction(e -> {
			stage.setScene(mainMenuScene);
			stage.setTitle("Main Menu");
		});
		// VBox
		VBox multiCoinVBox = new VBox(18);
		multiCoinVBox.setAlignment(Pos.CENTER);
		multiCoinVBox.getChildren().addAll(multiCoinCalcLabel, multiCoinCalcDisplay, multiCoinCalcInputLabel, multiCoinCalcValueInput, multiCoinCalcCoins, multiCoinCalcMenuReturn);
		// Construct Scene
		multiCoinCalcScene = new Scene(multiCoinVBox, 700, 360);
		
		/* Display Coin List */
		
		//Labels
		Label displayCoinListTitleLabel = new Label("Coin List");	// Title label
		displayCoinListTitleLabel.setFont(Font.font("Arial", 40));
		displayCoinListTitleLabel.setTextFill(Color.BLACK);
		Label coinListLabel = new Label(c.printCoinList());			// Coin list
		coinListLabel.setFont(Font.font("Arial", 30));
		coinListLabel.setTextFill(Color.BLACK);
		//Buttons
		Button displayCoinListReturn = new Button();				// Button to return to main menu
		displayCoinListReturn.setText("Return");
		displayCoinListReturn.setOnAction(e -> {
			stage.setScene(mainMenuScene);
			stage.setTitle("Main Menu");
		});
		//VBox
		VBox displayCoinListBox = new VBox(10);
		displayCoinListBox.setAlignment(Pos.CENTER);
		displayCoinListBox.getChildren().addAll(displayCoinListTitleLabel, coinListLabel, displayCoinListReturn);
		// Construct Scene
		displayCoinListScene = new Scene(displayCoinListBox, 400, 200);
		
		/* Settings scene */
		
		// Labels
		Label settingsTitle = new Label("Settings");													// Title label
		settingsTitle.setFont(Font.font("Arial", 40));
		settingsTitle.setTextFill(Color.BLACK);
		Label settingsCoinListLabel = new Label(c.printCoinList());										// Coin list at top of screen to aid user in adding and removing coins from the list
		settingsCoinListLabel.setFont(Font.font("Arial", 20));
		settingsCoinListLabel.setTextFill(Color.BLACK);
		Label settingsCurrencyLabel = new Label("Currency: " + c.getCurrency());						// Labels the row as the currency row and displays the current setting
		settingsCurrencyLabel.setFont(Font.font("Arial", 20));
		settingsCurrencyLabel.setTextFill(Color.BLACK);
		Label settingsMinValueLabel = new Label("Minimum value: " + Integer.toString(c.getMinValue()));	// Labels row as min value row and displays the current setting
		settingsMinValueLabel.setFont(Font.font("Arial", 20));
		settingsMinValueLabel.setTextFill(Color.BLACK);
		Label settingsMaxValueLabel = new Label("Maximum value: " + Integer.toString(c.getMaxValue()));	// Labels row as max value row and displays the current setting
		settingsMaxValueLabel.setFont(Font.font("Arial", 20));
		settingsMaxValueLabel.setTextFill(Color.BLACK);
		Label settingsCoinWarningLabel = new Label("");													// Warning label for coin add and remove row
		settingsCoinWarningLabel.setFont(Font.font("Arial", 15));
		settingsCoinWarningLabel.setTextFill(Color.RED);
		Label settingsMinWarningLabel = new Label("");													// Warning label for min value row
		settingsMinWarningLabel.setFont(Font.font("Arial", 15));
		settingsMinWarningLabel.setTextFill(Color.RED);
		Label settingsMaxWarningLabel = new Label("");													// Warning label for max value row
		settingsMaxWarningLabel.setFont(Font.font("Arial", 15));
		settingsMaxWarningLabel.setTextFill(Color.RED);
		Label outputHeading = new Label("Current Setting");												// Heading label for the current settings column
		outputHeading.setFont(Font.font("Arial", 20));
		outputHeading.setTextFill(Color.BLACK);
		Label inputHeading = new Label("Input Field");													// Heading label for the input field column
		inputHeading.setFont(Font.font("Arial", 20));
		inputHeading.setTextFill(Color.BLACK);
		Label buttonHeading = new Label("Change Setting");												// Heading label for the button column
		buttonHeading.setFont(Font.font("Arial", 20));
		buttonHeading.setTextFill(Color.BLACK);
		Label addCoinExplanation = new Label("Add or remove coin");										// Labels the row as the coin add or remove row
		addCoinExplanation.setFont(Font.font("Arial", 14));
		addCoinExplanation.setTextFill(Color.BLACK);
		// Text Fields
		TextField settingsCurrencyInput = new TextField();												// Currency input field
		settingsCurrencyInput.setMaxWidth(50);
		TextField settingsMinValueInput = new TextField();												// Min value input field
		settingsMinValueInput.setMaxWidth(50);
		TextField settingsMaxValueInput = new TextField();												// Max value input field
		settingsMaxValueInput.setMaxWidth(50);
		TextField settingsAddRemoveCoinInput = new TextField();											// Coin add or remove field
		settingsAddRemoveCoinInput.setMaxWidth(50);
		// Buttons
		Button settingsSetCurrency = new Button();														// Set currency to input field button
		settingsSetCurrency.setText("Set Currency");
		settingsSetCurrency.setOnAction(e -> {
			c.setCurrency(settingsCurrencyInput.getText());												// Currency changed here
			settingsCurrencyLabel.setText("Currency: " + c.getCurrency());								
			settingsCurrencyInput.clear();
			// Fix Coin List
			coinListLabel.setText(c.printCoinList());
			// Fix settings menu coin list
			settingsCoinListLabel.setText(c.printCoinList());
		});
		Button settingsSetMinValue = new Button();														// Set min value to input field button
		settingsSetMinValue.setText("Set minimum value");
		settingsSetMinValue.setOnAction(e -> {
			try {
				c.setMinValue(Integer.parseInt(settingsMinValueInput.getText()));						// Number format exception error expected to be thrown here
				settingsMinValueLabel.setText("Minimum value: " + Integer.toString(c.getMinValue()));
				settingsMinWarningLabel.setText("");
				settingsMinValueInput.clear();
			} catch (NumberFormatException e1)	{
				settingsMinWarningLabel.setText("Please enter an integer value");
				settingsMinValueInput.clear();
			}
		});
		Button settingsSetMaxValue = new Button();														// Set max value to input field button
		settingsSetMaxValue.setText("Set Maximum value");
		settingsSetMaxValue.setOnAction(e -> {
			try	{
				c.setMaxValue(Integer.parseInt(settingsMaxValueInput.getText()));						// Number format exception error expected to be thrown here
				settingsMaxValueLabel.setText("Maximum value: " + Integer.toString(c.getMaxValue()));
				settingsMaxWarningLabel.setText("");
				settingsMaxValueInput.clear();
			} catch (NumberFormatException e1)	{
				settingsMaxWarningLabel.setText("Please enter an integer value");
				settingsMaxValueInput.clear();
			}
		});
		Button settingsAddCoin = new Button();															// Add input field to coin list
		settingsAddCoin.setText("Add coin");
		settingsAddCoin.setOnAction(e -> {
			try	{
				if (!c.validateTotalValue(Integer.parseInt(settingsAddRemoveCoinInput.getText()))) {	// Number format exception error expected to occur here
					settingsCoinWarningLabel.setText("Please enter a valid number between " + c.getMinValue() + " and " + c.getMaxValue());
					settingsAddRemoveCoinInput.clear();
				}
				else if (c.validateExcludedCoin(Integer.parseInt(settingsAddRemoveCoinInput.getText()))) {
					settingsCoinWarningLabel.setText("This coin already exists");
					settingsAddRemoveCoinInput.clear();
				}
				else {	// Code to run after the input has passed all validation 
					c.addCoin(Integer.parseInt(settingsAddRemoveCoinInput.getText()));	// Add coin to coin list
					settingsCoinListLabel.setText(c.printCoinList());					// Refresh coin list in settings page
					settingsCoinWarningLabel.setText("");								// Set warning label to empty
					settingsAddRemoveCoinInput.clear();									// Clear input field
					// Fix multi coin calc buttons
					multiCoinCalcCoins.getChildren().clear();							// Since the Button creation loop just adds new coins, we clear the HBox of buttons to start again
					for (int i = 0; i < c.coinListLength(); i++)	{
						Button coinButton = new Button();
						coinButton.setText(Integer.toString(c.getCoin(i)));
						final int innerI = i;
						coinButton.setOnAction(e2 -> {
							if(multiCoinCalcValueInput.getText().isEmpty())        
							{                               
								multiCoinCalcDisplay.setText("Please a value into the total value field");   
							}
							else                        
							{     
								try {
									multiCoinCalcDisplay.setText(c.multiCoinCalculate(Integer.parseInt(multiCoinCalcValueInput.getText()), c.getCoin(innerI)));
								} catch (NumberFormatException e1) {
									multiCoinCalcDisplay.setText("Input value must be integer numbers");
								}
							}   
						});
						multiCoinCalcCoins.getChildren().add(coinButton);
					}
					// Fix Coin List
					coinListLabel.setText(c.printCoinList());
					// Fix settings menu coin list
					settingsCoinListLabel.setText(c.printCoinList());
					
				}	
			} catch (NumberFormatException e1) {
				settingsCoinWarningLabel.setText("Please enter an integer number ");
				settingsAddRemoveCoinInput.clear();
			}
		});
		Button settingsRemoveCoin = new Button();												// Remove input field from coin list
		settingsRemoveCoin.setText("Remove coin");
		settingsRemoveCoin.setOnAction(e -> {
			try	{
				if (!c.validateExcludedCoin(Integer.parseInt(settingsAddRemoveCoinInput.getText()))) {	// Number format exception error expected to occur here
					settingsCoinWarningLabel.setText("Please enter a valid coin");
					settingsAddRemoveCoinInput.clear();
				}
				else {																					// Code to run if the input is a valid coin
					c.removeCoin(Integer.parseInt(settingsAddRemoveCoinInput.getText()));				// Coin removed from coin list
					settingsCoinListLabel.setText(c.printCoinList());									// Coin list at top of settings page refreshed
					settingsCoinWarningLabel.setText("");
					settingsAddRemoveCoinInput.clear();
					// Fix multi coin calc buttons
					multiCoinCalcCoins.getChildren().clear();											// Since the Button creation loop just adds new coins, we clear the HBox of buttons to start again
					for (int i = 0; i < c.coinListLength(); i++)	{
						Button coinButton = new Button();
						coinButton.setText(Integer.toString(c.getCoin(i)));
						final int innerI = i;
						coinButton.setOnAction(e2 -> {
							if(multiCoinCalcValueInput.getText().isEmpty())        
							{                               
								multiCoinCalcDisplay.setText("Please a value into the total value field");                       
							}
							else                        
							{     
								try {
									multiCoinCalcDisplay.setText(c.multiCoinCalculate(Integer.parseInt(multiCoinCalcValueInput.getText()), c.getCoin(innerI)));
								} catch (NumberFormatException e1) {
									multiCoinCalcDisplay.setText("Input value must be integer numbers");
								}
							}   
						});
						multiCoinCalcCoins.getChildren().add(coinButton);
					}
					// Fix Coin List
					coinListLabel.setText(c.printCoinList());
					// Fix settings menu coin list
					settingsCoinListLabel.setText(c.printCoinList());

				}
			} catch (NumberFormatException e1) {
				settingsCoinWarningLabel.setText("Please enter an integer number");
				settingsAddRemoveCoinInput.clear();	
			}
		});
		Button settingsReturn = new Button();	// Return to main menu
		settingsReturn.setText("Return");
		settingsReturn.setOnAction(e -> {
			stage.setScene(mainMenuScene);
			stage.setTitle("Main Menu");
		});
		// GridPane
		GridPane settingsGridPane = new GridPane();
		settingsGridPane.setHgap(10);
		settingsGridPane.setVgap(10);
		settingsGridPane.setAlignment(Pos.CENTER);								// Grid laid out as so:
		settingsGridPane.add(outputHeading,						0, 0, 1, 1);	// Output heading		Input heading					Button heading
		settingsGridPane.add(inputHeading,						1, 0, 1, 1);	// Currency Label		Currency input					Currency button
		settingsGridPane.add(buttonHeading,						2, 0, 2, 1);	// Min value label		Min value input					Min value button				Min value warning
		settingsGridPane.add(settingsCurrencyLabel,				0, 1, 1, 1);	// Max value label		Max value input					Max value button				Max value warning
		settingsGridPane.add(settingsCurrencyInput,				1, 1, 1, 1);	// +/- coin	label		+/- coin input		Add coin button		Remove coin button		+/- coin warning
		settingsGridPane.add(settingsSetCurrency,				2, 1, 2, 1);
		settingsGridPane.add(settingsMinValueLabel,				0, 2, 1, 1);
		settingsGridPane.add(settingsMinValueInput,				1, 2, 1, 1);
		settingsGridPane.add(settingsSetMinValue,				2, 2, 2, 1);
		settingsGridPane.add(settingsMinWarningLabel,	 		4, 2, 1, 1);
		settingsGridPane.add(settingsMaxValueLabel, 			0, 3, 1, 1);
		settingsGridPane.add(settingsMaxValueInput, 			1, 3, 1, 1);
		settingsGridPane.add(settingsSetMaxValue, 				2, 3, 2, 1);
		settingsGridPane.add(settingsMaxWarningLabel, 			4, 3, 1, 1);
		settingsGridPane.add(addCoinExplanation, 				0, 4, 1, 1);
		settingsGridPane.add(settingsAddRemoveCoinInput, 		1, 4, 1, 1);
		settingsGridPane.add(settingsAddCoin, 					2, 4, 1, 1);
		settingsGridPane.add(settingsRemoveCoin, 				3, 4, 1, 1);
		settingsGridPane.add(settingsCoinWarningLabel, 			4, 4, 1, 1);
		// VBox
		VBox settingsVBox = new VBox(20);
		settingsVBox.setAlignment(Pos.CENTER);
		settingsVBox.getChildren().addAll(settingsTitle, settingsCoinListLabel, settingsGridPane, settingsReturn);
		// Construct Scene
		programSettingScene = new Scene(settingsVBox, 710, 400);
		// Set default scene
		stage.setScene(mainMenuScene);         
		stage.setTitle("Main Menu");        
		stage.show();
	}
	public static void main(String[] args)	{
		launch(args);
	}
}
