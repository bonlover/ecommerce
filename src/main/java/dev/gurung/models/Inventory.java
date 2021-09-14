package dev.gurung.models;

public class Inventory {
    private Integer id;
    private String productName;
    private Integer quantity;
    private Double price;
    private User user;

    public Inventory() {
    }

    public Inventory(Integer quantity) {
        this.quantity = quantity;
    }

    public Inventory(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Inventory(Integer id, Integer quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Inventory(Integer id, String productName, Integer quantity, Double price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Inventory(String productName, Integer quantity, Double price, User user) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }

    public Inventory(Integer id, String productName, Integer quantity, Double price, User user) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
