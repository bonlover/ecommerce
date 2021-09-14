package dev.gurung.views;

import dev.gurung.exceptions.ScannerInputException;
import dev.gurung.services.InventoryService;
import dev.gurung.services.UserService;

import java.util.Scanner;

public class MainMenuView {
    private static UserService userService = new UserService();
    private static InventoryService inventoryService = new InventoryService();

    public static void display(){


        //loop the following
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {

            /// need to display a menu
            System.out.println("Choose a number to interact with App:");
            System.out.println("1: View all Product");
            System.out.println("2: Admin Menu ");
            System.out.println("3: Customer menu ");
            System.out.println("4: New customer sign up");
            System.out.println("0: Exit");

            System.out.println("\nEnter your number:");

            String result = scanner.nextLine();

            switch (result) {
                case "1":
                    inventoryService.getAllInventories().forEach(System.out::println);
                    break;
                case "2":
                    AdminMenuView.display();
                    break;

                case "3":
                    CustomerMenuView.display();
                    break;

                case "4":
                    System.out.println("firstName:");
                    String firstName = scanner.nextLine();
                    System.out.println("lastName:");
                    String lastName = scanner.nextLine();
                    System.out.println("email:");
                    String email = scanner.nextLine();
                    System.out.println("password:");
                    String password = scanner.nextLine();

                    // We need a login service to check if email and password match credentials stored in the database
                    boolean isCreated = userService.signup(firstName, lastName, email, password);

                    if (isCreated) {
                        System.out.println("Successfully register");
                        CustomerDashboard.display();
                    } else {
                        System.out.println("Something went wrong, try Again.");
                    }

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
