public class Product {
    private String name;
    private String id;
    private String shop;
    private double price;

    Product(String name,String id, String shop, double price){
        this.name = name;
        this.id = id;
        this.shop = shop;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }
    public String getShop(){
        return shop;
    }
    public double getPrice(){
        return price;
    }
}
