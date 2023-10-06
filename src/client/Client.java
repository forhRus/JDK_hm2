package client;

import server.Server;

public class Client {
    private String name; // информация о клиенте
    private ClientView clientView; // графический интерфейс
    private Server server;  // сервер
    private boolean connected; // статус соединения

    // клиент подключается к серверу, у клиента есть GUI
    public Client(ClientView clientView, Server serverWindow) {
        this.clientView = clientView;
        this.server = serverWindow;
    }

    // если сервер активен, то наш клиент в списке. Получаем историю и выводим её в чат, если она есть.
    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)) {
            connected = true;
            sendToGUI("Вы успешно подключились");
            String log = server.getHistory();
            if (log != null) {
                sendToGUI(log);
            }
            return true;
        } else {
            sendToGUI("Подключение не удалось");
            return false;
        }
    }

    // вывод сообщений и историю в чат
    private void sendToGUI(String msg) {
        clientView.showMessage(msg);
    }

    // отправка сообщений на сервер
    public void sendToServer(String msg) {
        if (connected) {
            if (!msg.isEmpty()) {
                server.sendMessageToServerView(name + ": " + msg); // отправитель
            }
        } else {
            sendToGUI("Нет подключения к серверу");
        }
    }


    // приём сообщений от сервера, для передачи их в GUI
    public void serverAnswer(String answer) {
        sendToGUI(answer);
    }

    // штатное отключение от сервера
    public void disconnect() {
        if (connected) {
            connected = false; // меняем статус активности
            clientView.disconnectFromServer(); // включаем панельку для повторного подключения
            server.disconnectUser(this);  // удаляемся из списка
            sendToGUI("Вы были отключены от сервера!"); // уведомление в чат
        }
    }

    public String getName() {
        return name;
    }

}
