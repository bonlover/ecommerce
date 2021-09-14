package dev.gurung.services;

import dev.gurung.models.Cart;
import dev.gurung.models.Inventory;
import dev.gurung.models.Invoice;
import dev.gurung.models.User;

import dev.gurung.repositories.InventoryRepo;
import dev.gurung.repositories.InvoiceRepo;
import dev.gurung.repositories.UserRepo;

import java.util.List;

public class InvoiceService {
    private static InventoryRepo inventoryRepo = new InventoryRepo();
    private static InvoiceRepo invoiceRepo = new InvoiceRepo();
    private static UserRepo userRepo = new UserRepo();


    public Boolean buyProduct( int buyer_id, String product, int quantity) {

        User user = userRepo.getById(buyer_id);
        Inventory inventory = inventoryRepo.getByName(product);
        int updateQuantity = inventory.getQuantity() - quantity;

        try {

            if (product.equals(inventory.getProductName()) && (inventory.getQuantity() - quantity) > 0) {

                Invoice i = new Invoice(user, inventory);

                inventory.setId(inventory.getId());
                inventory.setQuantity(updateQuantity);

                invoiceRepo.add(i);
                inventoryRepo.update(inventory);
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(updateQuantity < 0){
            System.out.println("Product is out of stock:" +updateQuantity);
        }
        return false;


    }

    // user invoices
    public List<Invoice> getUserInvoices(Integer id){
        List<Invoice> invoices = invoiceRepo.getInvoiceByUserId(id);

        return (invoices != null)? invoices : null;

    }

    public List<Invoice> getAllInvoices(){
        return invoiceRepo.getAll();
    }



}




