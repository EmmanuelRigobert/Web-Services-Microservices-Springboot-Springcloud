package com.emmanuel.rest.webservices.restful_web_services.user; // This is the package that the class is in which allows us to organize our classes. Here we have access to the User class

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// A DAO (Data Access Object) is an object that provides an abstract interface to some type of database or other persistence mechanism.

@Component // This annotation is used to indicate that the class is a Spring Bean
public class UserDaoService {
    // This class is used to manage the user data

    //We use a static list to store the users instead of a database(JPA/Hibernate) for simplicity
    private static List<User> users = new ArrayList<>(); // Users list from the User class
    private static int usersCount = 0;
    //initialize the users
    static {
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount, "Jack", LocalDate.now().minusYears(17)));
        users.add(new User(++usersCount, "Larissa", LocalDate.now().minusYears(18)));
        users.add(new User(++usersCount, "John", LocalDate.now().minusYears(34)));
        users.add(new User(++usersCount, "Emmanuel", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "James", LocalDate.now().minusYears(40)));
        users.add(new User(++usersCount, "Jenny", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Josh", LocalDate.now().minusYears(22)));
        users.add(new User(++usersCount, "Ama", LocalDate.now().minusYears(19)));
        users.add(new User(++usersCount, "Jude", LocalDate.now().minusYears(21)));
        users.add(new User(++usersCount, "Jill", LocalDate.now().minusYears(18)));
        users.add(new User(++usersCount, "John", LocalDate.now().minusYears(34)));
        users.add(new User(++usersCount, "Jane", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Bright", LocalDate.now().minusYears(40)));
        users.add(new User(++usersCount, "Beyonce", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "David", LocalDate.now().minusYears(22)));
        users.add(new User(++usersCount, "Nero", LocalDate.now().minusYears(19)));


    }
    public List<User> findAll() { // This method will return all the users
        return users;
    }
    public User findOne(int id) { // This method will return a user with a specific id
        /*for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }// Response in case of user not found is not handled. Throws 200 status code instead of 404
        return null; // If the user is not found, return null */
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) { // This method will save a user
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        /*Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        } // Response in case of user not found is not handled. Throws 200 status code instead of 404
        return null; // If the user is not found, return null */
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
