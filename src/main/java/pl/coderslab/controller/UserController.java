package pl.coderslab.controller;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {

//   todo trzeba spraedzić czy id sie zgadza

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Validator validator;

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
    public String saveUser(Model model,@ModelAttribute User user, HttpServletRequest request,@PathVariable Long id,@PathVariable String action , HttpSession session) {

//        todo sprawdzenie powtórzenia hasła +
//        todo dokonczyc walidacja !

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            for (ConstraintViolation<User> err:violations){
                Path property = err.getPropertyPath();
                if(property.toString().equals("login") ){
                    if(user.getLogin().equals("")){
                        model.addAttribute("pwdErr", "Musisz podać nazwę !");
                        model.addAttribute("login", "login");
                        model.addAttribute("user",session.getAttribute("user"));
                        return "user/settings";
                    }
                    if(userRepository.findFirstByLogin(user.getLogin()) != null)
                    model.addAttribute("pwdErr", "Taki użytkownik już istnieje !");
                    return "user/settings";



                }else if(err.getPropertyPath().equals("password")){




                }else if(err.getPropertyPath().equals("email")){
                    if(user.getEmail() == null){
                        model.addAttribute("pwdErr", "Musisz podać nazwę !");
                        return "user/settings";
                    }
                    model.addAttribute("pwdErr", "Musisz podać nazwę !");
                    return "user/settings";
                }

            }
        }


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
