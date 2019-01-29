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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model , HttpServletRequest request,HttpSession session){
        if(session.getAttribute("user") !=null ){
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath()+"/login");
        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model,HttpSession session, HttpServletRequest request,@Valid User user , BindingResult errors){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
            if(errors.hasErrors()){
                return "user/login";
            }
        User userToSave = userRepository.findFirstByLogin(login);
        if(userToSave == null ){
            model.addAttribute("errDB", "Nie ma takiego użytkownika");
            return "user/login";
        }
        if(!(BCrypt.checkpw(password, userToSave.getPassword()))){
            model.addAttribute("errDB", "Hasło się nie zgadza,spróbuj jeszcze raz");
            return "user/login";
        }

        session.setAttribute("user",user);
        return "redirect:/home";
    }


    @GetMapping("/register")
    public String register(Model model,HttpServletRequest request){
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath()+"/register");
        return "user/register";
    }

// todo nie wyświetla się walidowanie emaila
// todo nie wyświetla że taki użytkownik już istnieje
// todo nie wyświetla się walidowanie powtórzonego hasła

// todo w loginie nie wyświetla się że złe hasło
// todo z czego korzystamy w loginie na postmapping - obiekt User vs request
// todo w post mapping wykorzystanie tego samego błedu?

    @PostMapping("/register")
    public String saveUser (Model model, HttpServletRequest request , @Valid User user, BindingResult errors){

        if(errors.hasErrors()){
            return "user/register";
        }

        if(user.getPassword().equals(user.getRepeatedPassword())){
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "redirect:/home";
        } else {
            model.addAttribute("pwdErr", "Passwords don't matches");
            return "user/register";

        }

    }

    public boolean isLogedIn(HttpSession sess){
        return (sess.getAttribute("user") != null);
    }



    /**
     * I don't check if session is null - it doesn't matter (we can assing her null anyway)
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/home";
    }
}

