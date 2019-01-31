package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

//   todo trzeba spraedzić czy id sie zgadza


    @GetMapping("/user/{id}")
    public String showUser(HttpSession session , @PathVariable Long id){
        return "user/user";
    }


    @GetMapping("/settings/{id}")
    public String settings(HttpSession session , @PathVariable Long id){
        return "user/settings";
    }

}
