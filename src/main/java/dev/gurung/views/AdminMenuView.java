package dev.gurung.views;

import dev.gurung.models.User;
import dev.gurung.services.UserService;

import java.util.Scanner;

public class AdminMenuView {

    private static UserService userService = new UserService();

    public static void display() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            //display
            System.out.println("Choose a number:");
            System.out.println("1: login ");
            System.out.println("0: back");

            //inputs

            System.out.println("\nEnter your number:");
            String result = scanner.nextLine();


            //do something with inputs
            switch (result){
                case "1":
                    scanner.nextLine();
                    System.out.println("Please enter your email:");
                    String email = scanner.nextLine();

                    System.out.println("Please enter your password:");
                    String password = scanner.nextLine();


                    // We need a login service to check if email and password match credentials stored in the database
                    User user = userService.login(email, password);

                    if (user != null  && user.getRole().equals("Admin")) {

                        System.out.println("Welcome " + user.getFirstName().toUpperCase() + ",You are in Admin Dashboard.");
                        System.out.println( "Your id : " +user.getId());
                        AdminDashboard.display();

                    }
                    else if(user == null || !user.getRole().equals( "Admin") ) {
                         System.out.println("Your Not Authorized");
                    }
                    else {
                        System.out.println("Credentials do not match. ");
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
