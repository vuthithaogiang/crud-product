package crud.product.product;

public class Product {
    private String name;
    private float price;
    private int quantity;
    private String description;
    private String category;


    public Product(String name, float price, int quantity, String category, String description){
        this.name= name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
    }
    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
