package dev.gurung.services;

import dev.gurung.models.Cart;
import dev.gurung.models.Inventory;
import dev.gurung.models.User;
import dev.gurung.repositories.CartRepo;
import dev.gurung.repositories.InventoryRepo;
import dev.gurung.repositories.UserRepo;


import java.util.List;

public class CartService {

    private static CartRepo cartRepo = new CartRepo();
    private static UserRepo userRepo = new UserRepo();
    private static InventoryRepo inventoryRepo = new InventoryRepo();


    public Cart getCart(Integer id){
        Cart cart = cartRepo.getById(id);

        // check to make sure User object is not null
        return  (cart != null) ? cart : null;
    }

    // user carts
    public List<Cart> getUserCarts(Integer id){
        List<Cart> carts = cartRepo.getByUserId(id);

        return (carts != null)? carts : null;

    }

    public List<Cart> getAllCarts(){
        return cartRepo.getAll();
    }


    public boolean addToCart( int userId, String productName, int quantity){
        User user = userRepo.getById(userId);
        Inventory inventory = inventoryRepo.getByName(productName);

        Double totalPrice = inventory.getPrice()*quantity;

        Cart cart = cartRepo.add( new Cart(user, inventory, quantity, totalPrice));

        return (cart != null) ? true : false;

    }


    public boolean updateCart(int id, String productName, int quantity){

        Inventory inventory = inventoryRepo.getByName(productName);
        Double price = inventory.getPrice();
        Double totalPrice = (price * quantity);
        Cart cart = cartRepo.getById(id);

        if(cart.getId() == id){

            cart.setId(id);
            cart.setNumberProducts(quantity);
            cart.setTotalPrice(totalPrice);

            cartRepo.update(cart);
            return true;

        }
        return false;
    }

    public boolean removeCart(Integer id){
        Cart c = cartRepo.getById(id);

        if(c != null){
            cartRepo.delete(id);
            return true;

        }
        return false;
    }
}
