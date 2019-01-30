package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/dashboard/brand")
    public String brand(){
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
