import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;



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