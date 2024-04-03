import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;


/**
 * Currency Exchange Program
 * 
 * Pseudocode:
 * 
 * 1. Welcome user through console messaging
 * 2. Prompt user for base currency
 * 3. Prompt user for currency they want to exchange to
 * 4. Inform the user when the last exchange rate update was and to check if the exchange rate matches the existing rate
 * 5. Allow user to update the exchange rate with the latest value (if user is exchanging through USD, need both base/USD & x/USD for update)
 * 6. If necessary, find a conversion rate given the x/USD pairings in the exrates.txt file
 * 7. Convert the amount they want to exchange into the fair value amount of their wanted currency
 * 8. Locate the unicode value for the currency they want to exchange to
 * 9. Approximate the fee of the transaction based on typical exchange booth fees
 * 10. Returns the range of "accpetable" exchange values for the currency they are looking to exchange to
 */
public class CurrencyConverter {

    public static void main(String[] args) {
        
        System.out.println("Welcome to the Quick Currency Conversion Tool");
        System.out.println();



        
        
        updateExchangeRate();
        getBaseCurrency();
        getEndCurrency();
        getAmount();
        mathConversion();
        currencySymbolFinder();
        feeEstimator();
        
    }

    
}