package my.project.myClient;

import my.project.myServer.MyServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) {
        String host = "netology.homework";
        String[] request = {"Дмитрий","Передайте данные соиденения ","Это полезная информация","Отбой"};
        try (Socket clientSoket = new Socket(host, MyServer.LOCALHOCT_PORT);
             PrintWriter writer = new PrintWriter(clientSoket.getOutputStream(),true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSoket.getInputStream()))){
            for (String next:request ) {
                writer.println(next);
                System.out.println(String.format(" отправил запрос %s",next));
                String answerSerwer = reader.readLine();
                System.out.println("получил ответ  "+answerSerwer);
                System.out.println("*******");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
