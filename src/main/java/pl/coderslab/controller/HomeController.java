package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("")
    public String redirectHome(){
        return "redirect:/home";
    }
//todo zrobić widoku ukryty link dla zalogowanego użytkownika i dwa ukryte linki dla zalogowanego admina
    @GetMapping("/home")
    public String home(HttpSession session){
//        if(session)

        return "home";

    }

}
