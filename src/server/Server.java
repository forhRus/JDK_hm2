package server;

import client.Client;
import server.repo.Repository;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private Repository repository;
    private ServerView serverView;
    private List<Client> clientList;
    private boolean work;

    public Server(Repository repository) {
        this.repository = repository;
        serverView = new ServerGUI(this);
        clientList = new ArrayList<>();
    }

    // проверка активности сервера
    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client); // добавляем клиента в список
        return true;
    }

    // получаем историю
    public String getHistory() {
        return repository.readData();
    }

    // отключение клиента
    public void disconnectUser(Client client) {
        serverView.showMessage(client.getName() + " отключился"); // сообщение в чат сервера
        clientList.remove(client);  // удаление клиента из списка
    }

    // отправка сообщения в интерфейс сервера
    public void sendMessageToServerView(String msg) {
        if (!work) { // если сервер `on`
            return;
        }
        repository.writeData(msg); // записываем сообщение в хранилище
        serverView.showMessage(msg);
        answerAll(msg);  // отправляем сообщения в GUI клиентов
    }

    // ответ сервера для всех клиентов, для обновления чата
    private void answerAll(String text) {
        for (int i = clientList.size() - 1; i >= 0; i--) {
            System.out.println("Клиент " + i + " " + clientList.get(i).getName());
            clientList.get(i).serverAnswer(text);
        }
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }
}
