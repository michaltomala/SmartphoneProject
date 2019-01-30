package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.repository.BrandRepository;
import pl.coderslab.repository.PhoneRepository;
import pl.coderslab.repository.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/dashboard/user")
    public String user(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "admin/user";
//        todo edycja i usuniecie usera
    }

}
