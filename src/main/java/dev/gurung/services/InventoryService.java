package dev.gurung.services;

import dev.gurung.models.Inventory;
import dev.gurung.models.User;
import dev.gurung.repositories.InventoryRepo;

import java.util.List;

public class InventoryService {
    private static InventoryRepo inventoryRepo = new InventoryRepo();

    public List<Inventory> getAllInventories(){
        return inventoryRepo.getAll();
    }

    public Boolean addProduct( String productName, int quantity, Double price, User user){

        Inventory i = inventoryRepo.add( new Inventory(productName, quantity, price, user));

        if(i != null){

            return true;
        }
        return  false;
    }


    public boolean updateProduct(Integer id, int quantity, Double price){
        Inventory inventory = inventoryRepo.getById(id);

        if(inventory.getId() == id){
            inventory.setId(id);
            inventory.setQuantity(quantity);
            inventory.setPrice(price);

            inventoryRepo.update(inventory);
            return true;

        }
        return false;
    }

    public boolean deleteProduct(Integer id){
        Inventory inventory = inventoryRepo.getById(id);

        if(inventory.getId() == id){
            inventoryRepo.delete(id);
            return true;

        }
        return false;
    }
}
