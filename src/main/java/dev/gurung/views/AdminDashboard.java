package dev.gurung.views;

import dev.gurung.models.User;
import dev.gurung.services.CartService;
import dev.gurung.services.InventoryService;
import dev.gurung.services.InvoiceService;
import dev.gurung.services.UserService;

import java.util.Scanner;

public class AdminDashboard {

    private static UserService userService = new UserService();
    private static InventoryService inventoryService = new InventoryService();
    private static CartService cartService = new CartService();
    private static InvoiceService invoiceService = new InvoiceService();

    public static void display() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            //display
            System.out.println("Choose a number:");
            System.out.println("1: View Customer Information:");
            System.out.println("2: View all product: ");
            System.out.println("3: Add New product :");
            System.out.println("4: Update product :");
            System.out.println("5: Delete product :");
            System.out.println("6: View all Cart's data");
            System.out.println("7: View all Invoices statement");
            System.out.println("0: back");

            //inputs

            System.out.println("\nEnter your number:");
            String result = scanner.nextLine();


            //do something with inputs
            switch (result){
                case "1":
//                    System.out.println(userService.getAllCustomers());
                    userService.getAllCustomers().forEach(System.out::println);
                    break;

                case "2":
                    inventoryService.getAllInventories().forEach(System.out::println);
                    break;

                case "3":
                    scanner.nextLine();
                    System.out.println("product name: ");
                    String product = scanner.nextLine();
                    System.out.println("quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.println("price:");
                    Double price = scanner.nextDouble();
                    System.out.println("user id:");
                    int user_id = scanner.nextInt();

                    User user = userService.getUser(user_id);
                    boolean isCreated = inventoryService.addProduct(product, quantity, price, user);

                    if(isCreated) {
                        System.out.println("Add products Successfully.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }

                    break;

                case "4":
                    scanner.nextLine();
                    System.out.println("product id : ");
                    int productID = scanner.nextInt();
                    System.out.println("quantity: ");
                    int editQuantity = scanner.nextInt();
                    System.out.println("price:");
                    Double editPrice = scanner.nextDouble();


                    // We need a login service to check if email and password match credentials stored in the database
                    boolean isEdited = inventoryService.updateProduct(productID, editQuantity, editPrice);

//                    System.out.println(userService.updateProfile());

                    if(isEdited) {
                        System.out.println("Successfully Updated.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }

                    break;

                case "5":
                    scanner.nextLine();
                    System.out.println("product id : ");
                    int productId = scanner.nextInt();

                    boolean isDeleted = inventoryService.deleteProduct(productId);

                    if(isDeleted) {
                        System.out.println("Successfully Deleted.");
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }

                    break;
                case "6":
                    cartService.getAllCarts().forEach(System.out::println);
                    break;

                case "7":
                    invoiceService.getAllInvoices().forEach(System.out::println);
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
