package server.repo;

import java.io.FileReader;
import java.io.FileWriter;


public class Storage implements Repository {
    private final String LOG_PATH; // путь к файлу

    public Storage(String LOG_PATH) {
        this.LOG_PATH = LOG_PATH;
    }

    // запись в файл
    @Override
    public String readData() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeData(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
