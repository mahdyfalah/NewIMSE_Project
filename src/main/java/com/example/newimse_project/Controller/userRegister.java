package com.example.newimse_project.Controller;

import com.example.newimse_project.Model.User;
import com.example.newimse_project.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class userRegister {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/chooseUser")
    public String user(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "chooseUser";
    }



}
