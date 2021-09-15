package dev.gurung.services;

import dev.gurung.exceptions.EmailAlreadyExistsException;
import dev.gurung.exceptions.InValidEmailFormatException;
import dev.gurung.models.User;
import dev.gurung.repositories.UserRepo;

import java.util.List;

public class UserService {
    private static UserRepo userRepo = new UserRepo();

    public User getUser(Integer id){
        User user = userRepo.getById(id);

        // check to make sure User object is not null
        if (user != null) {
            // now check to make sure it matches
            if (id == user.getId() && user.getRole().equals("Admin")) {
                return user;
            }
        }
        return null;
    }


    public User login(String email, String password) {

        User user = userRepo.getByEmail(email); // more of the Sole Responsibility Principle at work

        // check to make sure User object is not null
        if (user != null) {
            // now check to make sure it matches
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean signup(String firstName, String lastName, String email, String password){

        try{
            boolean isEmail = EmailValidationService.IsValidEmail(email);
            if(isEmail == true){

                try{
                    User existEmail = userRepo.getByEmail(email);

                    if(existEmail == null){
                        userRepo.add( new User(firstName, lastName, email, password));
                        return true;
                    }
                    throw new EmailAlreadyExistsException("Email: '" +email +"'  already taken, try other!");
                }catch (EmailAlreadyExistsException e){
                    System.out.println(e.getMessage());
                    System.out.println("................................................................\n");
                    return false;
                }

            }

            throw  new InValidEmailFormatException("Invalid email format");
        }catch (InValidEmailFormatException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public List<User> getAllCustomers(){
        return userRepo.getAll();
    }

    public boolean updateProfile(Integer id, String firstName,String lastName){
        User user = userRepo.getById(id);

        if(user.getId() == id){
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            userRepo.update(user);
            return true;
        }
        return false;
    }
}
