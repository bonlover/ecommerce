package dev.gurung.models;

import java.util.Date;

public class Invoice {
    private Integer id;
    private User user;
    private Inventory inventory;
    private Date invoiceDate;

    public Invoice() {
    }

    public Invoice(User user, Inventory inventory) {
        this.user = user;
        this.inventory = inventory;
    }
    public Invoice(Integer id, User user, Inventory inventory) {
        this.id = id;
        this.user = user;
        this.inventory = inventory;
    }


    public Invoice(User user, Inventory inventory, Date invoiceDate) {
        this.user = user;
        this.inventory = inventory;
        this.invoiceDate = invoiceDate;
    }

    public Invoice(Integer id, User user, Inventory inventory, Date invoiceDate) {
        this.id = id;
        this.user = user;
        this.inventory = inventory;
        this.invoiceDate = invoiceDate;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", user=" + user +
                ", inventory=" + inventory +
                ", invoiceDate=" + invoiceDate +
                '}';
    }
}
