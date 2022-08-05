package com.company.ordersystem.controller.admin;

import com.company.ordersystem.entity.user.Authority;
import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin/")
@Controller
public class AdminController {

    @Autowired
    IUserService userService;


    @RequestMapping ("user/new/")
    public String newUserForm(Model model){
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("actionDescription", "NOWY UŻYTKOWNIK");
        model.addAttribute("action", "/admin/save/");

        return "/admin/user-form";
    }

    @RequestMapping(value = "user/update/{username}")
    public String updateUserForm(@PathVariable String username, Model model) {
        User user = userService.get(username);

        model.addAttribute("user", user);
        model.addAttribute("action", "/product/update/");
        model.addAttribute("actionDescription", "EDYCJA DANYCH UŻYTKOWNIKA: " + user.getName());

        return "/admin/user-form";
    }

    @RequestMapping(value = "user/delete/{username}")
    public String delete(@PathVariable String username, Model model) {
        User user = userService.get(username);

        boolean result = userService.delete(user);
        String description = "Usunięcie produktu: " + user.getUsername() + " ";

        model.addAttribute("description", description);
        model.addAttribute("result", result);

        return "result";
    }

    @RequestMapping(value = "user/list/")
    public String list(Model model) {
        List<User> users = userService.getAll();

        model.addAttribute("users", users);
        model.addAttribute("actionDescription", "LISTA UŻYTKOWNIKÓW");

        return "/admin/user-list";
    }

    @RequestMapping("/save/")
    public String save(@ModelAttribute("user") User user, Model model) {

        String description = "Dodanie nowego użytkownika " + user.getName() + " ";
        user.setAuthority(new Authority(user.getUsername(), "ROLE_USER"));
        user.setEnabled(1);

        return saveOrUpdate(user, description, model);
    }

    private String saveOrUpdate(User user, String description, Model model) {
        boolean result = userService.saveOrUpdate(user);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }
}
