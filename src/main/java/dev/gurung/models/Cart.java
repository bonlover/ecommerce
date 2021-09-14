package dev.gurung.models;

public class Cart {
    private  Integer id;
    private User user;
    private Inventory inventory;
    private Integer numberProducts;
    private Double TotalPrice;

    public Cart() {
    }

    public Cart(Integer id, Integer numberProducts, Double totalPrice) {
        this.id = id;
        this.numberProducts = numberProducts;
        TotalPrice = totalPrice;
    }

    public Cart(User user, Inventory inventory, Integer numberProducts, Double totalPrice) {
        this.user = user;
        this.inventory = inventory;
        this.numberProducts = numberProducts;
        TotalPrice = totalPrice;
    }

    public Cart(Integer id, User user, Inventory inventory, Integer numberProducts, Double totalPrice) {
        this.id = id;
        this.user = user;
        this.inventory = inventory;
        this.numberProducts = numberProducts;
        TotalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getNumberProducts() {
        return numberProducts;
    }

    public void setNumberProducts(Integer numberProducts) {
        this.numberProducts = numberProducts;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", inventory=" + inventory +
                ", numberProducts=" + numberProducts +
                ", TotalPrice=" + TotalPrice +
                '}';
    }
}
