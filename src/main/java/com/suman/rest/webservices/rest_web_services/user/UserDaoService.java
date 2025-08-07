package com.suman.rest.webservices.rest_web_services.user;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
@Component
public class UserDaoService {
    // JPA/Hibernate -> Database
    //UserDaoService -> Static List 

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static{
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Suman", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

public User findOne(int id) {
    Predicate<? super User> predicate = user -> user.getId().equals(id);
    return users.stream()
            .filter(predicate)
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
}

public void deletebyId(int id) {
    Predicate<? super User> predicate = user -> user.getId().equals(id);

    if(!users.removeIf(predicate))
        new UserNotFoundException("User with id " + id + " not found");
}
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }


}
