package my.project.myServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static final Integer LOCALHOCT_PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(LOCALHOCT_PORT)) {
            System.out.println("\t< *  Server started  * >");
            int connectionСounting = 1;
            String nameClient = "";
            String[] answer = {"", "Добрый день", "данные Вашего соиденения", "Рады сообщить её Вам", " Ждём Ваших запросов "};
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    String clientInformation = in.readLine();
                    if (clientInformation.contains("Дмитрий")) {
                        System.out.println("Запрос от " + clientInformation);
                        nameClient = clientInformation;
                        out.println(String.format("%s %s", answer[connectionСounting], clientInformation));
                    }
                    connectionСounting++;
                    clientInformation = in.readLine();
                    if (clientInformation.contains("данные соиденения")) {
                        System.out.println("Запрос  " + clientInformation);
                        out.println(String.format("%s ,%s: порт: %d IP: %s",nameClient, answer[connectionСounting], clientSocket.getPort(), clientSocket.getInetAddress()));
                    }
                    connectionСounting++;
                    clientInformation = in.readLine();
                    if (clientInformation.contains("полезная информация")) {
                        System.out.println(clientInformation);
                        out.println(String.format("%s %s", answer[connectionСounting],nameClient));

                    }
                    connectionСounting++;
                    clientInformation = in.readLine();
                    if (clientInformation.contains("Отбой")) {
                        System.out.println(clientInformation);
                        out.println(String.format("%s ", answer[connectionСounting]));
                    }
                    connectionСounting=1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}