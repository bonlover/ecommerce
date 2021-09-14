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
            try {
                if(result.matches(".*\\d.*")){

                    switch (result) {
                        case "1":
                            inventoryService.getAllInventories().forEach(System.out::println);
                            break;
                        case "2":
                            AdminMenuView.display();
                            System.out.println("................................................................\n");

                            break;

                        case "3":
                            CustomerMenuView.display();
                            System.out.println("................................................................\n");

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

                            if (!isCreated) {
                                MainMenuView.display();
                            }else {
                                System.out.println("Successfully register");
                                System.out.println("..................................................................\n");
                                CustomerDashboard.display();
                            }
                            break;

                        case "0":
                            running = false;
                            break;
//                        default:
//                            System.out.println("Invalid Input");

                    }

                }
                throw new ScannerInputException("Invalid Input  '" +result + "', Enter Number from Choice!");

            }catch (ScannerInputException ex){
                System.out.println(ex.getMessage());
                System.out.println("................................................................\n");
            }


        }
    }
}
