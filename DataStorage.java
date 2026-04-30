import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<ConversionRecord> history;

    public DataStorage() {
        history = new ArrayList<>();
    }

    public ArrayList<ConversionRecord> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<ConversionRecord> history) {
        this.history = history;
    }

    public void addRecord(ConversionRecord recordToAdd) {
        history.add(recordToAdd);
    }
}
