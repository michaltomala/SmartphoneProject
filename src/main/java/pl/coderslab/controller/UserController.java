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
import pl.coderslab.entity.Phone;
import pl.coderslab.entity.User;
import pl.coderslab.repository.PhoneRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class UserController {

//   todo trzeba spraedzić czy id sie zgadza

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    Validator validator;

    @GetMapping("/user/{id}")
    public String showUser(HttpSession session , @PathVariable Long id, Model model){
        User user = userRepository.findOne(id);
        User userToCheck = (User) session.getAttribute("userFromSession");
        if(!user.getId().equals(userToCheck.getId())){
            return "redirect:/login";
        }
        List<Phone> phones = user.getPhones();

        model.addAttribute("phones",phones);
        return "user/user";

//        TODO: zrobić w widoku dodawanie smartphona do ulubionych - tam zrobić tą zaawansowaną wyszukiwarke
//        przy pomocy algorytmu
    }

    @GetMapping("/user/phone/delete/{id}")
    public String deleteFavoriteSmartphone(@PathVariable Long id , HttpSession session){
        Phone phone = phoneRepository.findOne(id);
        User user = (User) session.getAttribute("userFromSession");
        phone.deleteUser(user);
        phoneRepository.save(phone);
        session.setAttribute("userFromSession",user);
        return "redirect:/user/"+user.getId();
    }


    @GetMapping("/settings/{id}")
    public String settings(HttpSession session , @PathVariable Long id , Model model){

        User user = userRepository.findOne(id);
        User userToCheck = (User) session.getAttribute("userFromSession");
        if(user != null && userToCheck != null && user.getLogin().equals(userToCheck.getLogin())){
            model.addAttribute("user",user);
            return "user/settings";
        }
        return "redirect:/";
    }

    @GetMapping("/settings/{action}/{id}")
    public String updateUser(@PathVariable String action , @PathVariable Long id, Model model, HttpSession session , HttpServletRequest request) {


        User user = userRepository.findOne(id);
        User userToCheck = (User) session.getAttribute("userFromSession");
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

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
                for (ConstraintViolation<User> err:violations){
                    Path property = err.getPropertyPath();
                    switch(action){
                        case "login":{
                            if(property.toString().equals("login") ){
                                if(user.getLogin().equals("")){
                                    model.addAttribute("pwdErr", "Musisz podać nazwę !");
                                    model.addAttribute("login", "login");
                                    model.addAttribute("user",session.getAttribute("userFromSession"));
                                    return "user/settings";
                                }
                            }
                            break;
                        }
                        case "password": {
                            if (property.toString().equals("password")) {
                                if (user.getPassword().length() < 8) {
                                    model.addAttribute("pwdErr", "Hasło musi mieć minimum 6 znaków");
                                    model.addAttribute("password", "password");
                                    model.addAttribute("user", session.getAttribute("userFromSession"));
                                    return "user/settings";
                                }
                            }
                            break;
                        }
                        case "email": {
                            if(user.getEmail().equals("")){
                                model.addAttribute("pwdErr", "Musisz podać nazwę !");
                                model.addAttribute("email", "email");
                                model.addAttribute("user",session.getAttribute("userFromSession"));
                                return "user/settings";
                            }
                            break;
                        }
                    }
                }
        }

//      Dodatkowe walidacje

        switch(action){
            case "login":{
//      Sprawdzenie czy login usera już istnieje
                if(userRepository.findFirstByLogin(user.getLogin()) != null) {
                    model.addAttribute("pwdErr", "Taki użytkownik już istnieje !");
                    model.addAttribute("login", "login");
                    model.addAttribute("user",session.getAttribute("userFromSession"));
                    return "user/settings";
                }
                break;
            }
            case "password":{
//      Sprawdzenie czy powtórzone hasło jest takie samo
                if (!user.getPassword().equals(user.getRepeatedPassword())) {
                    model.addAttribute("pwdErr", "Hasła muszą być takie same!");
                    model.addAttribute("password", "password");
                    model.addAttribute("user", session.getAttribute("userFromSession"));
                    return "user/settings";
                }
                break;
            }
            case "email": {
//       Sprawdzenie czy mail usera już istnieje
                if(userRepository.findFirstByEmail(user.getEmail()) != null){
                    model.addAttribute("pwdErr", "Taki email już istnieje !");
                    model.addAttribute("email", "email");
                    model.addAttribute("user",session.getAttribute("userFromSession"));
                    return "user/settings";
                } else{
//      Sprawdzenie czy email jest email
                    Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(user.getEmail());

                    if(!matcher.find()){
                        model.addAttribute("pwdErr", "Niepoprawny format!");
                        model.addAttribute("email", "email");
                        model.addAttribute("user",session.getAttribute("userFromSession"));
                        return "user/settings";
                    }
                }
                break;
            }
        }




// Zapisanie usera
        User userToSave = userRepository.findOne(id);
        switch(action){
            case "login":{
                userToSave.setLogin(user.getLogin());
                userRepository.save(userToSave);
                session.setAttribute("userFromSession", userToSave);
                break;
            }
            case "password":{
                userToSave.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                userRepository.save(userToSave);
                session.setAttribute("userFromSession", userToSave);
                break;
            }
            case "email": {
                userToSave.setEmail(user.getEmail());
                userRepository.save(userToSave);
                session.setAttribute("userFromSession", userToSave);
                break;
            }
        }

        return "redirect:"+request.getContextPath()+"/settings/"+id;
    }

}
