import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import  java.io.*;

public class ServerThread  implements Runnable{

    Scanner scanner;
    private ArrayList<Product> products;
    private Socket socket;
    private HashMap<String, Object> dataSession;
    ServerThread(Socket server){
        socket = server;
        dataSession = new HashMap<String,Object>();
        products =new  ArrayList<Product>(){{
            add(new Product("Banana","1111","Lidl",3.00));
            add(new Product("Guitar","2222","Muziker",500));
            add(new Product("tenisBall", "3333","Lev",1.00));
        }};
    }
    @Override
    public void run(){
        try{
            PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientInput;

            while ((clientInput = clientReader.readLine())!=null){
                Functions(clientInput,clientWriter,clientReader);
            }

            clientReader.close();
            clientWriter.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void Functions(String cmd, PrintWriter clientWriter, BufferedReader clientReader) throws IOException {


        if (cmd.equals("1")){
            String name = clientReader.readLine();
            for(Product product : products){
                if(product.getName().equals(name)){
                    clientWriter.println( "It is in" + product.getShop()) ;
                }else{
                   clientWriter.println( "No such product in the library");
                }
            }

        }else if(cmd.equals("2")){
            String IdProduct = clientReader.readLine();
            for(Product product:products){
                if(product.getId().equals(IdProduct)){
                    dataSession.put("product", product);
                    //не знам как да проверя колко са продуктите

                }
            }

        }else if(cmd.equals("3")){
            for(Product product:products){
                clientWriter.println(product);
            }
        }
    }


}