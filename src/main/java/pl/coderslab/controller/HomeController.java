package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("")
    public String redirectHome(){
        return "redirect:/home";
    }
//  todo połączyć header i footer do wszystkich linków
//   zrobić user controller i ustawienia usera

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
