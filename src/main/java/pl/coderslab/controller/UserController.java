package pl.coderslab.controller;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

//   todo trzeba spraedzić czy id sie zgadza

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public String showUser(HttpSession session , @PathVariable Long id, Model model){

        return "redirect:/";
    }


    @GetMapping("/settings/{id}")
    public String settings(HttpSession session , @PathVariable Long id , Model model){

        User user = userRepository.findOne(id);
        User userToCheck = (User) session.getAttribute("user");
        if(user != null && userToCheck != null && user.getLogin().equals(userToCheck.getLogin())){
            model.addAttribute("user",user);
            return "user/settings";
        }
        return "redirect:/";
    }

    @GetMapping("/settings/{action}/{id}")
    public String updateUser(@PathVariable String action , @PathVariable Long id, Model model, HttpSession session , HttpServletRequest request) {

        User user = userRepository.findOne(id);
        User userToCheck = (User) session.getAttribute("user");
        if(user != null && userToCheck != null && user.getLogin().equals(userToCheck.getLogin())){

        model.addAttribute("user", user);
        model.addAttribute("formAction", request.getContextPath() + "/settings/"+action+"/"+id);
        switch(action){
            case "login":{
                model.addAttribute("login",new User());
                return "user/settings";
            }
            case "password":{
                model.addAttribute("password",new User());
                return "user/settings";
            }
            case "email": {
                model.addAttribute("email",new User());
                return "user/settings";
            }

        }}
    return "redirect:/";
    }

    @PostMapping("/settings/{action}/{id}")
    public String saveUser(@ModelAttribute User user, HttpServletRequest request,@PathVariable Long id,@PathVariable String action , HttpSession session) {


        User userToSave = userRepository.findOne(id);
        switch(action){
            case "login":{
                userToSave.setLogin(user.getLogin());
                userRepository.save(userToSave);
                session.setAttribute("user", userToSave);
                break;
            }
            case "password":{
                userToSave.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                userRepository.save(userToSave);
                session.setAttribute("user", userToSave);
                break;
            }
            case "email": {
                userToSave.setEmail(user.getEmail());
                userRepository.save(userToSave);
                session.setAttribute("user", userToSave);
                break;
            }
        }

        return "redirect:"+request.getContextPath()+"/settings/"+id;
    }


}
