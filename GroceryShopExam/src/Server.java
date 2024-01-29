import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private static final int PORT = 4321;
    public static void main(String[] args)throws IOException{
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while(true){
                Socket socket = serverSocket.accept();


            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}