package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.repository.BrandRepository;

@Controller
public class AdminController {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/dashboard/brand")
    public String brand(Model model){
        model.addAttribute("brands",brandRepository.findAll());
        return "admin/brand";
    }

    @GetMapping("/admin/dashboard/phone")
    public String phones(){
        return "admin/phone";
    }

    @GetMapping("/admin/dashboard/user")
    public String user(){
        return "admin/user";
    }

}
