import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;



public class CurrencyConverter {

    public static void main(String[] args) {
        
        boolean done = false;
        

        welcome();

        while (!done) {
            String action = promptForAction();

            if (action.equals("quit")) {
                done = true;
            } else {
                System.out.println();
                String baseCurrency = getBaseCurrency();
                char baseCurrencySymbol = currencySymbolFinder(baseCurrency);
                System.out.println();
                String endCurrency = getEndCurrency();
                char endCurrencySymbol = currencySymbolFinder(endCurrency);
                System.out.println();
                double baseAmount = getAmount(baseCurrency, endCurrency);
                System.out.println();


                
                


                String exchangeType = exchangeType(baseCurrency, endCurrency);


                double conversionValue;
                double conversionValueToUSD;
                double conversionValueFromUSD;
                double finalConversionRate;
                double endCurrencyFairValue;
                boolean rateUpdated = false;


                
                if (exchangeType.equals("same")) {
                    System.out.println("It looks like you're trying to convert to the same currency you already have! Please try again with two different currencies.");
                    System.out.println();
                    continue;
                } else if (exchangeType.equals("to USD")) {
                    conversionValue = mathConversionToUSD(baseCurrency);
                    rateUpdated = promptForRateUpdate(baseCurrency, endCurrency, conversionValue);
                    if (rateUpdated) {
                        conversionValue = mathConversionToUSD(baseCurrency);
                    }
                    endCurrencyFairValue = baseAmount * finalConversionRate;

                } else if (exchangeType.equals("from USD")) {
                    conversionValue = mathConversionFromUSD(endCurrency);
                    rateUpdated = promptForRateUpdate(baseCurrency, endCurrency, conversionValue);
                    if (rateUpdated) {
                        conversionValue = mathConversionFromUSD(baseCurrency);
                    }
                    endCurrencyFairValue = baseAmount * finalConversionRate;

                } else {
                    conversionValueToUSD = mathConversionToUSD(baseCurrency);
                    conversionValueFromUSD = mathConversionFromUSD(endCurrency);
                    finalConversionRate = conversionValueToUSD * conversionValueFromUSD;
                    
                    rateUpdated = promptForMultipleRateUpdate(baseCurrency, endCurrency, conversionValueToUSD, conversionValueFromUSD);
                    if (rateUpdated) {
                        conversionValueToUSD = mathConversionToUSD(baseCurrency);
                        conversionValueFromUSD = mathConversionFromUSD(endCurrency);
                        finalConversionRate = conversionValueToUSD * conversionValueFromUSD;
                        
                    }
                    endCurrencyFairValue = baseAmount * finalConversionRate;

                }

                printFinalResults(baseCurrencySymbol, baseAmount, endCurrency, endCurrencySymbol, endCurrencyFairValue);

            }


        }
        
    }

    // Welcomes user & explains how the programs works
    public static void welcome() {
        System.out.println("Welcome to the Quick Currency Conversion Tool");
        System.out.println();
    }

    // Prompt user to either make a currency conversion estimate or quit the program. Returns int value representing their answer.
    public static String promptForAction() {
        System.out.print("Enter 'convert' to get a conversion estimate or 'quit' to quit the app: ");

        Scanner keyboard = new Scanner(System.in);
        String command = keyboard.next();

        while (!command.equals("convert") && !command.equals("quit")) {
            System.out.println("Invalid Command!");
            System.out.print("Enter 'convert' to get a conversion estimate or 'quit' to quit the app: ");
            command = keyboard.next();
        }

        return command;
    }



    // Prompts & returns the base currency that the user wants to use for their transaction quote
    public static String getBaseCurrency() {

    }

    // Prompts & returns the base currency that the user wants to use for their transaction quote
    public static String getEndCurrency() {

    }

    // Prompts & returns the amount of base currency that the user wants to exchange
    public static double getAmount(String base, String end) {

    }

    // Determines the type of exchange that needs to be calculated and returns one of the options as a string
    public static String exchangeType(String currency1, String currency2) {
        
        if (currency1.equals(currency2)) {
            return "same";
        } else if (currency1.equals("USD") || currency2.equals("USD")) {
            if (currency1.equals("USD")) {
                return "from USD";
            } else {
                return "to USD";
            }
        } else {
            return "multiple";
        }
    }

    // Returns exchange rate when exchanging passed currency for USD
    public static double mathConversionToUSD(String baseISO) {

    }

    // Returns exchange rate when exchanging USD for passed currency
    public static double mathConversionFromUSD(String endISO) {

    }

    // Prompts user to verify that the current exchange rate is correct, then updates using updateExchangeRate if necessary. Returns whether update occurred.
    public static boolean promptForRateUpdate(String base, String end, double currentConversionValue) {

    }

    // Prompts user to verify that the current exchange rate for both transactions is correct, then updates using updateExchangeRate if necessary. Returns whether update occurred.
    public static boolean promptForMultipleRateUpdate(String base, String end, double baseToUSDRate, double USDToEndRate) {

    }

    // Updates the exchange rate with new exchange rate provided by the user
    public static void updateExchangeRate(String currencySymbol, double updatedRate) {

    }

    // Finds & returns the unicode value of the passed currency's symbol for use in printing
    public static char currencySymbolFinder(String currencyISO) {

    }

    // Using all of the variables needed, creates a print statement that summarizes the transaction quote & fair value (should state that fees are variable based on broker, would be good to get a range)
    public static void printFinalResults(char baseSymbol, double baseAmount, String endCurrency, char endCurrencySymbol, double endCurrencyFairValue) {

    }
}