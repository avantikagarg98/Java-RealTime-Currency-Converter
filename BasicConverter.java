import java.util.Map;
import java.util.Scanner;
import java.math.BigDecimal;

public class BasicConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        label:
        while (true) {
            System.out.println("\n=== Currency Converter ===");
            System.out.println("C - Convert currency");
            System.out.println("H - View history");
            System.out.println("Q - Quit");
            System.out.print("Choose an option: ");

            String choice = sc.next().trim().toUpperCase();

            switch (choice) {
                case "Q":
                    System.out.println("Goodbye.");
                    break label;
                case "H":
                    showHistory();
                    continue;
                case "C":
                    doConversion(sc);
                    continue;
            }

            System.out.println("Invalid option.");
        }

        sc.close();
    }

    public static void doConversion(Scanner sc) {
        try {
            System.out.print("Enter currency to convert from: ");  // base curr
            String base_curr = sc.next().trim().toUpperCase();

            System.out.print("Enter amount " + base_curr + ": ");  // amt
            BigDecimal amt = sc.nextBigDecimal();

            ExchangeResponse exchangeData = Currencies.getExchangeData(base_curr);
            Map<String, BigDecimal> exchange_rates = exchangeData.getRates();

            System.out.println("Available currencies: " + exchange_rates.keySet());
            System.out.print("Choose currency to convert to: ");
            String new_curr = sc.next().trim().toUpperCase();

            ConversionRecord record = CurrencyConverterService.convert(base_curr, new_curr, amt);

            System.out.println("Exchange date: " + record.getDate());
            System.out.println("Rate: " + record.getRate());
            System.out.println("Result: " + record.getResult() + " " + record.getToCurrency());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showHistory() {
        DataStorage storage = HistoryManager.loadHistory();

        if (storage.getHistory().isEmpty()) {
            System.out.println("No history found.");
            return;
        }

        System.out.println("\n=== Conversion History ===");
        for (ConversionRecord record : storage.getHistory()) {
            System.out.println(record);
        }
    }
}