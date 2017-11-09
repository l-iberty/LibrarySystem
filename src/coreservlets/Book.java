package coreservlets;

public class Book {
    private String id = "";
    private String name = "";
    private String author = "";
    private double price = 0.0;
    private int numItems = 0; // 库存
    private int numSales = 0; // 销量

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumItems() {
        return numItems;
    }

    public int getNumSales() {
        return numSales;
    }

    public double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void setNumSales(int numSales) {
        this.numSales = numSales;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumItemsIncrement(int increment) {
        this.numItems += increment;
    }

    public void setNumSalesIncrement(int increment) {
        this.numSales += increment;
    }

}
