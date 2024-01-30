import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.ServerSocket;
public class Server {
    //порт 4321
    private static final int PORT = 43210;
    ////за съдържане на множество клиенти
    private ExecutorService executorService;
    //продуктите да се съдържат в подходяща структура
    //Конструктор

    public Server(){

        //ще поема по 3 клиента
        executorService = Executors.newFixedThreadPool(3);

    }
    public static void main(String[] args){
        Server server = new Server();
        server.start();
    }
    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Server is running");
            while(true){
                Socket clientSocket = serverSocket.accept();
                if(Thread.activeCount()<=3){
                    executorService.execute(new GroceryServer(clientSocket));
                }else{
                    System.out.println("Maximum clients reached");
                    clientSocket.close();
                }
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}