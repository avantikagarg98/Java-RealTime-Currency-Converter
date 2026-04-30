import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyConverterService {
    public static ConversionRecord convert(String baseCurr, String newCurr, BigDecimal amt) throws Exception {
        if (baseCurr == null || newCurr == null || amt == null) {
            throw new IllegalAccessException("Inputs cannot be empty");
        }

        baseCurr = baseCurr.trim().toUpperCase();
        newCurr = newCurr.trim().toUpperCase();

        if (baseCurr.isEmpty() || newCurr.isEmpty()) {
            throw new IllegalArgumentException("Currency code cannot be empty.");
        }

        if (amt.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        ExchangeResponse allData = Currencies.getExchangeData(baseCurr);
        Map<String, BigDecimal> rates = allData.getRates();
        String date = allData.getDate();
        BigDecimal exRate = rates.get(newCurr);

        if (exRate == null) {
            throw new IllegalArgumentException("Invalid target currency.");
        }

        BigDecimal res = amt.multiply(exRate).setScale(2, RoundingMode.HALF_UP);

        ConversionRecord record = new ConversionRecord(baseCurr, newCurr, amt, exRate, res, date);
        DataStorage storage = HistoryManager.loadHistory();
        storage.addRecord(record);
        HistoryManager.saveHistory(storage);

        return record;
    }
}
