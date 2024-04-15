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
                String baseCurrencySymbol = currencySymbolFinder(baseCurrency);
                System.out.println();
                String endCurrency = getEndCurrency();
                String endCurrencySymbol = currencySymbolFinder(endCurrency);
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
    public static String currencySymbolFinder(String c) {

        if (c == "AED") {
            return "\u062F.\u0625";
        } else if (c == "BGN") {
            return "\u043B\u0432";
        } else if (c == "BHD") {
            return "\u062F";
        } else if (c == "BRL") {
            return "R$";
        } else if (c == "BWP") {
            return "P";
        } else if (c == "CHF") {
            return c;
        } else if (c == "CZK") {
            return "K\u010D";
        } else if (c == "DKK") {
            return "dk";
        } else if (c == "EUR") {
            return "\u20AC";
        } else if (c == "GBP") {
            return "\u00A3";
        } else if (c == "HUF") {
            return "Ft";
        } else if (c == "IDR") {
            return "Rp";
        } else if (c == "ILS") {
            return "\u20AA";
        } else if (c == "INR") {
            return "\u20B9";
        } else if (c == "IRR") {
            return "\uFDFC";
        } else if (c == "ISK" || c == "NOK" || c == "SEK") {
            return "kr";
        } else if (c == "JPY" || c == "RMB") {
            return "\u00A5";
        } else if (c == "KRW") {
            return "\u20A9";
        } else if (c == "KWD") {
            return "\u062F.\u0643";
        } else if (c == "KZT") {
            return "\u20B8";
        } else if (c == "LKR" || c == "NPR" || c == "PKR") {
            return "\u20A8";
        } else if (c == "LYD") {
            return "\u062F.\u0644";
        } else if (c == "MUR") {
            return "\u20B9";
        } else if (c == "MYR") {
            return "RM";
        } else if (c == "OMR") {
            return "\u0631.\u0639.";
        } else if (c == "PHP") {
            return "\u20B1";
        } else if (c == "PLN") {
            return "\u007A\u0142";
        } else if (c == "QAR") {
            return "\u0631";
        } else if (c == "RON") {
            return "lei";
        } else if (c == "RUB") {
            return "\u20BD";
        } else if (c == "SAR") {
            return "\u0631.\u0633";
        } else if (c == "THB") {
            return "\u0E3F";
        } else if (c == "TRY") {
            return "\u20BA";
        } else if (c == "TWD") {
            return "NT$";
        } else if (c == "VED") {
            return "Bs. S.";
        } else if (c == "ZAR") {
            return "R";
        } else {
            return "$";
        }
    }

    // Using all of the variables needed, creates a print statement that summarizes the transaction quote & fair value (should state that fees are variable based on broker, would be good to get a range)
    public static void printFinalResults(String baseSymbol, double baseAmount, String endCurrency, String endCurrencySymbol, double endCurrencyFairValue) {

    }
}