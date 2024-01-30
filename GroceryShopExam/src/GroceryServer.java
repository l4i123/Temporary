import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroceryServer implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private HashMap<String, Product> sessionData;
    private HashMap<String, Product> cart;
    private ArrayList<Product> products;


    public GroceryServer(Socket clientSocket) {
        products = new ArrayList<>();
        products.add(new Product("Rollers", "2525", "SportShop", 90));
        products.add(new Product("Guitar", "1111", "MusicShop", 600));
        products.add(new Product("Mochi", "3214", "JapaneseShop", 1000));
        this.clientSocket = clientSocket;
        sessionData = new HashMap<String, Product>();
        cart = new HashMap<String, Product>();
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String clientRequest = reader.readLine();
                if (clientRequest != null) {
                    choiceHandler(clientRequest);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void choiceHandler(String request) {
        if (request.equalsIgnoreCase("1")) {
            writer.println("Enter product name");
            String readerRequest = getProductInfo();
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(readerRequest)) {
                    writer.println("Shop:" + product.getShop() + "price" + product.getPrice());
                } else {
                    writer.println("No such product existing in the shop library");
                }
            }
        } else if (request.equalsIgnoreCase("2")) {
            writer.println("Enter product Id");
            String readerRequest = getProductInfo();
            for (Product product : products) {
                if (product.getId().equals(readerRequest)) {
                    addProduct(product.getShop(), product);
                }
            }
            //съжалявам отказах се
        } else if (request.equalsIgnoreCase("3")) {
            for( Product product : sessionData.values()) {
                System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice() + ", Store: " + product.getShop());
           }



        }
    }

    public String getProductInfo() {
        String readerRequest = null;
        try {
            readerRequest = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readerRequest;

    }

    public void addProduct(String productShop, Product product) {
        if (!sessionData.containsKey(productShop)) {
            sessionData.put(productShop, product);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product with shop " + productShop + " already exists.");
        }
    }


}