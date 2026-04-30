import java.io.*;

public class HistoryManager {
    private static final String FILE_NAME = "History.dat";

    public static DataStorage loadHistory() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new DataStorage();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new BufferedInputStream(
                                     new FileInputStream(FILE_NAME)))) {

            return (DataStorage) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading history: " + e.getMessage());
            return new DataStorage();
        }
    }

    public static void saveHistory(DataStorage storage) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(FILE_NAME)))) {

            oos.writeObject(storage);

        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }
}