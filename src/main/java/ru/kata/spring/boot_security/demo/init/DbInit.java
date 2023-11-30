/*package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DbInit {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public DbInit(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleOfAdmin = new Role("ADMIN");
        Role roleOfUser = new Role("USER");
        List<Role> listOfRoles = new ArrayList<>() {
            {
                add(roleOfAdmin);
                add(roleOfUser);
            }
        };
        roleService.saveRole(roleOfAdmin);
        roleService.saveRole(roleOfUser);
        User user1 = new User("admin", "admin", "admin",
                Long.valueOf("777"), listOfRoles);
        userService.saveUser(user1);
        User user2 = new User("ivan", "ivanov", "ivan",
                Long.valueOf("2000"), listOfRoles);
        userService.saveUser(user2);
        User user3 = new User("user", "user", "user",
                Long.valueOf("1995"), Collections.singletonList(roleOfUser));
        userService.saveUser(user3);
    }
}*/