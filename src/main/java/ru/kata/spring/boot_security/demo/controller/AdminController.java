package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String goAdminPageListOfUsers(Model model) {
        model.addAttribute("allUsers", userService.getListOfUsers());
        return "allUsers";
    }

    @GetMapping("/admin/new")
    public String getFormForCreateUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getListOfRoles());
        return "new";
    }

    @PostMapping("/admin/addUser")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("allRoles", roleService.getListOfRoles());
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String getUserForUpdate(@RequestParam(value = "id") Long id,
                                   Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getListOfRoles());
        return "update";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("allRoles", roleService.getListOfRoles());
            return "update";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String getUserForDelete(@RequestParam(value = "id") Long id,
                                   Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "delete";
    }

    @PostMapping("/admin/deleteUser")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUserById(user.getId());
        return "redirect:/admin";
    }
}