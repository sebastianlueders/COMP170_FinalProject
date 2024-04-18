import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;



public class CurrencyConverter {

    public static void main(String[] args) throws FileNotFoundException {
        
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
                String endCurrency = getEndCurrency(baseCurrency);
                String endCurrencySymbol = currencySymbolFinder(endCurrency);
                System.out.println();
                double baseAmount = getAmount(baseCurrency, endCurrency);
                System.out.println();
                
                
                


                String exchangeType = exchangeType(baseCurrency, endCurrency);


                // The following could be put into a getEndCurrencyFairValue method w/ header:
                // public static double getEndCurrencyFairValue(String exchangeType, String baseCurrency, String endCurrency) returns endCurrencyFairValue



                boolean rateUpdated = false;
                double conversionValue;
                double conversionValueToUSD;
                double conversionValueFromUSD;
                double finalConversionRate;
                double endCurrencyFairValue;
                


                
                if (exchangeType.equals("same")) {
                    System.out.println("It looks like you're trying to convert to the same currency you already have! Please try again with two different currencies.");
                    System.out.println();
                    continue;
                } else if (exchangeType.equals("to USD")) {
                    conversionValue = mathConversionToUSD(baseCurrency);
                    rateUpdated = promptForRateUpdate(baseCurrency, "USD", conversionValue);
                    if (rateUpdated) {
                        conversionValue = mathConversionToUSD(baseCurrency);
                    }
                    endCurrencyFairValue = baseAmount * conversionValue;

                } else if (exchangeType.equals("from USD")) {
                    conversionValue = mathConversionFromUSD(endCurrency);
                    rateUpdated = promptForRateUpdate("USD", endCurrency, conversionValue);
                    if (rateUpdated) {
                        conversionValue = mathConversionFromUSD(baseCurrency);
                    }
                    endCurrencyFairValue = baseAmount * conversionValue;

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

                done = printFinalResults(baseCurrencySymbol, baseAmount, endCurrency, endCurrencySymbol, endCurrencyFairValue);

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
    public static String getBaseCurrency() throws FileNotFoundException {
        boolean match = false;
        String base = "";

        while (!match) {
        
            System.out.println("What currency are you wanting to exchange today?");
            System.out.print("Please enter the 3-letter ISO code for the currency you are currently holding: ");
            Scanner keyboard = new Scanner(System.in);
            base = keyboard.next();
            // I think we should use a if/else statement here
            while (base.length() != 3 || !Character.isLetter(base.charAt(0)) || !Character.isLetter(base.charAt(1)) || !Character.isLetter(base.charAt(2))) {
                System.out.print("Please enter a 3-letter ISO code: ");
                base = keyboard.next();
            }

            base.toUpperCase();

            Scanner exrates = new Scanner(new File("exrates.txt"));

            while (exrates.hasNextLine() && !match) {
                
                String iso = exrates.next();

                if (iso.equals(base) || base.equals("USD")) {
                    match = true;
                } else {
                    exrates.nextLine();
                }
            }

            if (!match) {
                System.out.println("The ISO code you entered could not be found in our list of supported currencies. Please try again.");
            }
        }

        return base;
    }

    // Prompts & returns the base currency that the user wants to use for their transaction quote
    public static String getEndCurrency(String base) throws FileNotFoundException {
        boolean match = false;
        String end = "";

        while (!match) {
        
            System.out.println("What currency do you want in exchange for " + base + "?");
            System.out.print("Please enter the 3-letter ISO code for the currency you are looking to exchange to: ");
            Scanner keyboard = new Scanner(System.in);
            System.out.println();
            end = keyboard.next();
            while (end.length() != 3 || !Character.isLetter(end.charAt(0)) || !Character.isLetter(end.charAt(1)) || !Character.isLetter(end.charAt(2))) {
                System.out.print("Please enter a 3-letter ISO code: ");
                end = keyboard.next();
            }

            end = end.toUpperCase();

            Scanner exrates = new Scanner(new File("exrates.txt"));

            while (exrates.hasNextLine() && !match) {
                
                String iso = exrates.next();

                if (iso.equals(end) || end.equals("USD")) {
                    match = true;
                } else {
                    exrates.nextLine();
                }
            }

            if (!match) {
                System.out.println("The ISO code you entered could not be found in our list of supported currencies. Please try again.");
            }
        }

        return end;

    }

    // Prompts & returns the amount of base currency that the user wants to exchange
    public static double getAmount(String base, String end) {
        boolean validInput = false;
        double amount = 0;

        while (!validInput) {
        
            System.out.print("How much " + base + " would you like to convert to " + end + " today? ");
            Scanner keyboard = new Scanner(System.in);

            while (!keyboard.hasNextDouble()) {
                System.out.println("Please enter a numerical amount you'd like to exchange today: ");
                keyboard.next();
            }
            
            amount = keyboard.nextDouble();

            if (amount <= 0) {
                System.out.println("Sorry, we can't exchange this value. Please try again.");
            } else {
                validInput = true;
            }

        }

        return amount;
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

    // Returns line number of ISO passed
    public static int getLine(String iso) throws FileNotFoundException {
        Scanner exrates = new Scanner(new File("exrates.txt"));
        boolean match = false;
        String candidate;
        int lineCounter = 0;

        while (exrates.hasNextLine() && !match) {
            
            candidate = exrates.next();

            if (iso.equals(candidate)) {
                match = true;
            } else {
                exrates.nextLine();
                lineCounter++;
            }
        }

        return lineCounter;
    }

    // Returns exchange rate when exchanging passed currency for USD
    public static double mathConversionToUSD(String baseISO) throws FileNotFoundException {
        int lineNumber = getLine(baseISO);

        Scanner exrates = new Scanner(new File("exrates.txt"));

        for (int i = 1; i <= lineNumber; i++) {
            exrates.nextLine();
        }

        String relevantLine = exrates.nextLine();

        Scanner line = new Scanner(relevantLine);

        line.next();

        double conversionRate = line.nextDouble();

        conversionRate = 1 / conversionRate;

        return conversionRate;
    }

    // Returns exchange rate when exchanging USD for passed currency
    public static double mathConversionFromUSD(String endISO) throws FileNotFoundException {
        int lineNumber = getLine(endISO);

        Scanner exrates = new Scanner(new File("exrates.txt"));

        for (int i = 1; i <= lineNumber; i++) {
            exrates.nextLine();
        }

        String relevantLine = exrates.nextLine();

        Scanner line = new Scanner(relevantLine);

        line.next();

        double conversionRate = line.nextDouble();

        return conversionRate;

    }

    // Prompts user to verify that the current exchange rate is correct, then updates using updateExchangeRate if necessary. Returns whether update occurred.
    public static boolean promptForRateUpdate(String base, String end, double currentConversionValue) throws FileNotFoundException {

        String dateUpdated = "";
        int relevantLineNumber;
        String currencyToUpdate;
        boolean rateInversionNeeded;
        boolean rateUpdated = false;

        if (base.equals("USD")) {
            relevantLineNumber = getLine(end);
            currencyToUpdate = end;
            rateInversionNeeded = false;
        } else {
            relevantLineNumber = getLine(base);
            currencyToUpdate = base;
            rateInversionNeeded = true;
        }

        Scanner exrate = new Scanner(new File("exrates.txt"));

        for (int i = 1; i <= relevantLineNumber; i++) {
            exrate.nextLine();
        }

        String relevantLine = exrate.nextLine();

        Scanner lineParser = new Scanner(relevantLine);

        lineParser.next();
        lineParser.next();

        dateUpdated = lineParser.next();

        boolean updateConfirmed = false;

        while (!updateConfirmed) {

            System.out.println("The conversion rate we currently have on file for " + base + "/" + end + " is: " + currentConversionValue);
            System.out.println("It was last updated: " + dateUpdated);

            System.out.print("Would you like to update this rate? (yes/no): ");

            Scanner keyboard = new Scanner(System.in);

            String updateRequest = keyboard.next();

            while (!updateRequest.equals("yes") && !updateRequest.equals("no")) {
                System.out.print("Would you like to update this rate? Enter yes or no: ");
                updateRequest = keyboard.next();
            }

            if (updateRequest.equals("yes")) {
                System.out.print("Please input an updated exchange rate for " + base + "/" + end + ": ");
                double userUpdatedRate;
                while (!keyboard.hasNextDouble()) {
                    keyboard.nextLine();
                    System.out.print("Please input a numerical value for the updated exchange rate: ");
                }
                userUpdatedRate = keyboard.nextDouble();
                System.out.println("Please confirm that you want to update the exchange rate for " + base + "/" + end + " to " + userUpdatedRate);
                System.out.print("Is this correct? (yes/no): ");
                String answer = keyboard.next();
                while (!answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("Please confirm that you want to update the exchange rate for " + base + "/" + end + " to " + userUpdatedRate);
                    System.out.print("Is this correct? (yes/no): ");
                    answer = keyboard.next();
                }

                if (answer.equals("yes")) {
                    updateConfirmed = true;
                    
                    if (rateInversionNeeded) {
                        userUpdatedRate = 1 / userUpdatedRate;
                    }

                    updateExchangeRate(currencyToUpdate, userUpdatedRate);
                    rateUpdated = true;
                } else {
                    updateConfirmed = false;
                    System.out.println("Please try again.");
                }

            } else {
                updateConfirmed = true;
            }

        }

        return rateUpdated;

    }

    // Prompts user to verify that the current exchange rate for both transactions is correct, then updates using updateExchangeRate if necessary. Returns whether update occurred.
    public static boolean promptForMultipleRateUpdate(String base, String end, double baseToUSDRate, double USDToEndRate) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("The exchange rate that we have on file for " + base + "/" + end + " is: " + baseToUSDRate * USDToEndRate);
        System.out.println("We convert through USD so if this is incorrect you would need to update both conversion rates. Would you like to convert the " + base + "/" + "USD rate and the USD/" + end + " rate?");
        System.out.print("Type yes/no: ");
        String answer = keyboard.next();
        boolean baseRateUpdate = false;
        boolean endRateUpdate = false;

        while(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("Your input is not an option. Please type yes or no: ");
            answer = keyboard.next();
        } 

        if(answer.equals("no")) {
            return false;
        } else {
            System.out.println("The " + base + "/USD rate is: " + baseToUSDRate);
            System.out.print("Would you like to change this rate? (yes/no): ");
            answer = keyboard.nextLine();
            while(!answer.equals("yes") && !answer.equals("no")) {
                System.out.println("Your input is not an option. Please type yes or no: ");
                answer = keyboard.next();
            } 
            if (answer.equals("yes")) {
                baseRateUpdate = promptForRateUpdate(base, "USD", baseToUSDRate);
                endRateUpdate = promptForRateUpdate("USD", end, USDToEndRate);
            }

            return baseRateUpdate || endRateUpdate;
        }

    }

    // Updates the exchange rate with new exchange rate provided by the user
    public static void updateExchangeRate(String currencySymbol, double updatedRate) throws FileNotFoundException {

        Scanner exrates = new Scanner(new File("exrates.txt"));

        int lineNumber = getLine(currencySymbol);

        // Creates new file named updatedexrates.txt
        PrintStream output = new PrintStream(new File("updatedexrates.txt"));

        Scanner keyboard = new Scanner(System.in);

        if (lineNumber == 0) {
            String relevantLine = exrates.nextLine();
            Scanner lineParser = new Scanner(relevantLine);
            String ticker = lineParser.next();
            output.print(ticker + " ");
            output.print(updatedRate + " ");
            System.out.println("Please input today's date in the following format YYYY-MM-DD");
            System.out.print("Input date here: ");
            String date = keyboard.next();
            while (date.length() != 10 || !(Character.isDigit(date.charAt(0))) || !(Character.isDigit(date.charAt(1))) || date.charAt(4) != '-' || 
            !(Character.isDigit(date.charAt(2))) || !(Character.isDigit(date.charAt(3))) || date.charAt(7) != '-' || !(Character.isDigit(date.charAt(5))) ||
            !(Character.isDigit(date.charAt(6))) || !(Character.isDigit(date.charAt(8))) || !(Character.isDigit(date.charAt(9)))) {
                System.out.print("Incorrect format. Please input date with 'YYYY-MM-DD' format: ");
                date = keyboard.next();
            }

            output.println(date);

            while (exrates.hasNextLine()) {
                relevantLine = exrates.nextLine();
                output.println(relevantLine);
            }

            Scanner updatedExRates = new Scanner(new File("updatedexrates.txt"));
            PrintStream finalOutput = new PrintStream(new File("exrates.txt"));

            while (updatedExRates.hasNextLine()) {
                relevantLine = updatedExRates.nextLine();
                finalOutput.println(relevantLine);
            }

        } else {
            for (int i = 1; i <= lineNumber; i++) {
                String relevantLine = exrates.nextLine();
                output.println(relevantLine);
            }

            String relevantLine = exrates.nextLine();
            Scanner lineParser = new Scanner(relevantLine);
            String ticker = lineParser.next();
            output.print(ticker + " ");
            output.print(updatedRate + " ");
            System.out.println("Please input today's date in the following format YYYY-MM-DD");
            System.out.print("Input date here: ");
            String date = keyboard.next();
            while (date.length() != 10 || !(Character.isDigit(date.charAt(0))) || !(Character.isDigit(date.charAt(1))) || date.charAt(4) != '-' || 
            !(Character.isDigit(date.charAt(2))) || !(Character.isDigit(date.charAt(3))) || date.charAt(7) != '-' || !(Character.isDigit(date.charAt(5))) ||
            !(Character.isDigit(date.charAt(6))) || !(Character.isDigit(date.charAt(8))) || !(Character.isDigit(date.charAt(9)))) {
                System.out.print("Incorrect format. Please input date with 'YYYY-MM-DD' format: ");
                date = keyboard.next();
            }

            output.println(date);

            while (exrates.hasNextLine()) {
                relevantLine = exrates.nextLine();
                output.println(relevantLine);
            }

            Scanner updatedExRates = new Scanner(new File("updatedexrates.txt"));
            PrintStream finalOutput = new PrintStream(new File("exrates.txt"));

            while (updatedExRates.hasNextLine()) {
                relevantLine = updatedExRates.nextLine();
                finalOutput.println(relevantLine);
            }

        }

    }

    // Finds & returns the unicode value of the passed currency's symbol for use in printing
    public static String currencySymbolFinder(String c) {

        if (c.equals("AED")) {
            return "\u062F.\u0625";
        } else if (c.equals("BGN")) {
            return "\u043B\u0432";
        } else if (c.equals( "BHD")) {
            return "\u062F";
        } else if (c.equals("BRL")) {
            return "R$";
        } else if (c.equals("BWP")) {
            return "P";
        } else if (c.equals("CHF")) {
            return c;
        } else if (c.equals("CZK")) {
            return "K\u010D";
        } else if (c.equals("DKK")) {
            return "dk";
        } else if (c.equals("EUR")) {
            return "\u20AC";
        } else if (c.equals("GBP")) {
            return "\u00A3";
        } else if (c.equals("HUF")) {
            return "Ft";
        } else if (c.equals("IDR")) {
            return "Rp";
        } else if (c.equals("ILS")) {
            return "\u20AA";
        } else if (c.equals("INR")) {
            return "\u20B9";
        } else if (c.equals("IRR")) {
            return "\uFDFC";
        } else if (c.equals("ISK") || c.equals("NOK") || c.equals("SEK")) {
            return "kr";
        } else if (c.equals("JPY") || c.equals("RMB")) {
            return "\u00A5";
        } else if (c.equals("KRW")) {
            return "\u20A9";
        } else if (c.equals("KWD")) {
            return "\u062F.\u0643";
        } else if (c.equals("KZT")) {
            return "\u20B8";
        } else if (c.equals("LKR") || c.equals("NPR") || c.equals("PKR")) {
            return "\u20A8";
        } else if (c.equals("LYD")) {
            return "\u062F.\u0644";
        } else if (c.equals("MUR")) {
            return "\u20B9";
        } else if (c.equals("MYR")) {
            return "RM";
        } else if (c.equals("OMR")) {
            return "\u0631.\u0639.";
        } else if (c.equals("PHP")) {
            return "\u20B1";
        } else if (c.equals("PLN")) {
            return "\u007A\u0142";
        } else if (c.equals("QAR")) {
            return "\u0631";
        } else if (c.equals("RON")) {
            return "lei";
        } else if (c.equals("RUB")) {
            return "\u20BD";
        } else if (c.equals("SAR")) {
            return "\u0631.\u0633";
        } else if (c.equals("THB")) {
            return "\u0E3F";
        } else if (c.equals("TRY")) {
            return "\u20BA";
        } else if (c.equals("TWD")) {
            return "NT$";
        } else if (c.equals("VED")) {
            return "Bs. S.";
        } else if (c.equals("ZAR")) {
            return "R";
        } else {
            return "$";
        }
    }

    // Using all of the variables needed, creates a print statement that summarizes the transaction quote & fair value (should state that fees are variable based on broker, would be good to get a range)
    public static boolean printFinalResults(String baseSymbol, double baseAmount, String endCurrency, String endCurrencySymbol, double endCurrencyFairValue) {
        Scanner keyboard = new Scanner(System.in); 
        
        System.out.println();
        System.out.println();
        System.out.printf("The fair market value of converting %s%.2f to %s is: %s%.2f \n", baseSymbol, baseAmount, endCurrency, endCurrencySymbol, endCurrencyFairValue);
        double finalValueWithFees = 0.9945 * endCurrencyFairValue;
        System.out.printf("Considering an average exchange fee of 0.55%%, the minimum fair quote after fees is: %s%.2f \n", endCurrencySymbol, finalValueWithFees);
        System.out.println();
        System.out.println("Do you want to make another transaction? (yes/no)");


        String input = keyboard.next(); 
        while(!input.equals("yes") && !input.equals("no")) {
            System.out.println("Your input is not an option. Please type yes or no: ");
            input = keyboard.next();
        } 

        boolean done = false;
        if (input.equals("no")) {
            System.out.println("Goodbye.");
            done = true;

        }

        return done;




    }
}
