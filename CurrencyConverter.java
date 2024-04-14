import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;



public class CurrencyConverter {

    public static void main(String[] args) {
        
        

        welcome();

        
        
        updateExchangeRate();
        getBaseCurrency();
        getEndCurrency();
        getAmount();
        inverseConversion();
        finalConversionRate();
        currencySymbolFinder();
        feeEstimator();
        
    }

    // Welcomes user & explains how the programs works
    public static void welcome() {
        System.out.println("Welcome to the Quick Currency Conversion Tool");
        System.out.println();
    }

    // Updates the exchange rate with new exchange rate provided by the user
    public static void updateExchangeRate(String currencySymbol, double updatedRate) {

    }

    // Prompts & returns the base currency that the user wants to use for their transaction quote
    public static String getBaseCurrency() {

    }

    // Prompts & returns the base currency that the user wants to use for their transaction quote
    public static String getEndCurrency() {

    }

    // Prompts & returns the amount of base currency that the user wants to exchange
    public static double getAmount() {

    }

    // If needed, finds and converts x/USD conversion rate to USD/x if user is converting into USD
    public static double inverseConversion(String currencySymbol) {

    }

    // Finds and returns the exchange rate for more complciated transactions i.e. through USD where neither base nor end currencies are USD
    public static double finalConversionRate(String baseCurrency, String endCurrency) {

    }

    // Finds & returns the unicode value of the end currency for use in printing
    public static char currencySymbolFinder(String currencySymbol) {

    }

    // Finds & returns estimated low & high range of fees for the transaction to provide user with range of acceptable quotes
    public static String feeEstimator(int finalAmount) {

    }
}