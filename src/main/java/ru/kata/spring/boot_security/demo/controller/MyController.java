package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class MyController {
    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/employees")
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", userService.getAllUsers());

        return "employee-list";
    }

    @GetMapping("/employee-create")
    public String createEmployeeForm(User employee, Model model) {
        model.addAttribute("employee", employee);

        return "employee-update";
    }
    @GetMapping("employee-delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/employees";
    }

    @GetMapping("/employee-update/{id}")
    public String updateEmployeeForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", userService.getUserById(id));

        return "employee-update";
    }

    @PostMapping("/employee-update")
    public String updateEmployee(User user) {
        userService.saveUser(user);

        return "redirect:/employees";
    }
}