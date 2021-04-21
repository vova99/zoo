package com.charlie.zoo.controller.admin;

import com.charlie.zoo.entity.Client;
import com.charlie.zoo.entity.Users;
import com.charlie.zoo.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminUsersController {
    private final UsersService usersService;

    @GetMapping
    public String getProducer(Model model){
        model.addAttribute("users",usersService.findAll());
        return "admin/";
    }

    @PostMapping({"/add","/edit"})
    public String addProducer(Users users){
        usersService.save(users);
        return "redirect:/admin/clients";
    }

    @PostMapping("/delete")
    public String deleteProducer(int id){
        usersService.deleteByID(id);
        return "redirect:/admin/clients";
    }
}
