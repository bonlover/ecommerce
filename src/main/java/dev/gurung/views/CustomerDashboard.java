package dev.gurung.views;

import dev.gurung.services.CartService;
import dev.gurung.services.InventoryService;
import dev.gurung.services.InvoiceService;
import dev.gurung.services.UserService;

import java.util.Scanner;

public class CustomerDashboard {
    private static UserService userService = new UserService();
    private static InventoryService inventoryService = new InventoryService();
    private static InvoiceService invoiceServer = new InvoiceService();
    private static CartService cartService = new CartService();

    public static void display() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            //display
            System.out.println("Choose a number: ");
            System.out.println("1: view products");
            System.out.println("2: buy product");
            System.out.println("3: Add product in a Cart");
            System.out.println("4: Delete the cart");
            System.out.println("5: Edit profile");
            System.out.println("6: View all user products in a Cart");
            System.out.println("7: Edit product in a Cart");
            System.out.println("8: View statement of invoices");
            System.out.println("0: back");

            //inputs
            System.out.println("\nEnter your number:");

            String result = scanner.nextLine();


            //do something with inputs
            switch (result){
                case "1":
                    inventoryService.getAllInventories().forEach(System.out::println);
                    break;

                case "2":
                    scanner.nextLine();
                    System.out.println("product name : ");
                    String productName = scanner.nextLine();
                    System.out.println("Buyer id : ");
                    int buyer_id = scanner.nextInt();
                    System.out.println("quantity: ");
                    int editQuantity = scanner.nextInt();


                    // We need a login service to check if email and password match credentials stored in the database
//                    boolean isBuy = inventoryService.buyProduct(buyer_id, productName, editQuantity);
                    boolean isBuy = invoiceServer.buyProduct(buyer_id, productName, editQuantity);

//                    System.out.println(userService.updateProfile());

                    if(isBuy) {
                        System.out.println("Successfully buy a product.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }
                    break;

                case "3":
                    scanner.nextLine();
                    System.out.println("Buyer id : ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("product name : ");
                    String product = scanner.nextLine();
                    System.out.println("Number quantity: ");
                    int items = scanner.nextInt();


                    boolean isCarted = cartService.addToCart(userId, product, items);


                    if(isCarted) {
                        System.out.println("Successfully add a product in cart.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }
                    break;

                case "4":
                    scanner.nextLine();
                    System.out.println("Cart id : ");
                    int cartId = scanner.nextInt();

                    boolean isDeleted = cartService.removeCart(cartId);

                    if(isDeleted) {
                        System.out.println("Successfully Deleted a Cart's data.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }
                    break;

                case "5":
                    scanner.nextLine();

                    System.out.println("firstName:");
                    String firstName = scanner.nextLine();
                    System.out.println("lastName:");
                    String lastName = scanner.nextLine();
                    System.out.println("Customer id : ");
                    int id = scanner.nextInt();

                    boolean isUpdated = userService.updateProfile(id, firstName, lastName);

                    if(isUpdated) {
                        System.out.println("Successfully Updated.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }
                    break;

                case "6":
                    scanner.nextLine();
                    System.out.println("Your id : ");
                    int yourId = scanner.nextInt();
                    cartService.getUserCarts(yourId).forEach(System.out::println);
                    break;

                case "7":

                    scanner.nextLine();
                    System.out.println("Cart Id:");
                    int cart_Id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("product Name:");
                    String product_name = scanner.nextLine();
                    System.out.println("Number of quantity : ");
                    int quantity = scanner.nextInt();

                    boolean isCartUpdated = cartService.updateCart(cart_Id, product_name, quantity);

                    if(isCartUpdated) {
                        System.out.println("Successfully Updated.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }
                    break;

                case "8":
                    scanner.nextLine();
                    System.out.println("Your id : ");
                    int uId = scanner.nextInt();
                    invoiceServer.getUserInvoices(uId).forEach(System.out::println);
                    break;

                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}
