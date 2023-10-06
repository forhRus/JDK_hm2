import client.ClientGUI;
import server.Server;
import server.repo.Repository;
import server.repo.Storage;

public class Main {
    public static void main(String[] args) {
        // Создаём интерфейс репозитория с объектом типа хранилища(файл)
        Repository repository = new Storage("./src/server/repo/log.txt");

        //Сервер работает с репозиторием
        Server server = new Server(repository);

        // К серверу подключаются клиенты через GUI
        new ClientGUI(server);
        new ClientGUI(server);
    }
}
