package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping("")
    public String redirectHome(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "Home Page";
    }

}
