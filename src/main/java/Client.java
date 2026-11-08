import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{
    public Client(){
        start();
    }

    public void run(){
        System.out.println("Client is running");
        try(
                Socket socket = new Socket("localhost", Server.SERVER_PORT);
                PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            clientWriter.println("Hello from client! Give me my port!");
            String port = clientReader.readLine();
            System.out.println("Server answer: " + port);
        }catch(IOException e){
            System.out.println("Ошибка при запуске клиента: " + e);
            throw new RuntimeException(e);
        }
    }
}
