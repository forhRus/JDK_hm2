package server.repo;

public interface Repository {
    String readData(); // чтение
    void writeData(String text); // запись
}
