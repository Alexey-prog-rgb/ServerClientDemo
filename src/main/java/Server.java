import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    public static final Integer SERVER_PORT = 8080;

    public  Server(){
        start();
    };

    // запуск сервера
    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            System.out.println("Cервер запущен!");
            while(true){
                Socket clientSoket = serverSocket.accept();
                try(PrintWriter clientWriter = new PrintWriter(clientSoket.getOutputStream(), true);
                    BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSoket.getInputStream()))
                ){
                    String name = clientReader.readLine();
                    System.out.println("Client message: " + name + "\n");

                    // отправка ответа клиенту
                    clientWriter.printf("Hi %s, your port is %d", name, clientSoket.getPort());
                }catch(IOException e){
                    System.out.println("Ошибка при получении потоков ввода/вывода клиента:" + e);
                    throw new RuntimeException(e);
                }
            }
        }catch(IOException e){
            System.out.println("Ошибка при запуске сервера: " + e);
            throw new RuntimeException(e);
        }
    }
}
