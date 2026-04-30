import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class ConversionRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal result;
    private String date;

    public ConversionRecord(String fromCurrency, String toCurrency,
                            BigDecimal amount, BigDecimal rate,
                            BigDecimal result, String date) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.rate = rate;
        this.result = result;
        this.date = date;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + " | " + fromCurrency + " -> " + toCurrency +
                " | amount: " + amount +
                " | rate: " + rate +
                " | result: " + result;
    }
}