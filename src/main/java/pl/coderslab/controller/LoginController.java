package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/login");
        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession session, @Valid User user, BindingResult errors) {
        if (errors.hasErrors()) {
            return "user/login";
        }
        User userToCheck = userRepository.findFirstByLogin(user.getLogin());
        if (userToCheck == null) {
            model.addAttribute("errDB", "Nie ma takiego użytkownika");
            return "user/login";
        }
        if (!(BCrypt.checkpw(user.getPassword(), userToCheck.getPassword()))) {
            model.addAttribute("errDB", "Hasło się nie zgadza,spróbuj jeszcze raz");
            return "user/login";
        }

        session.setAttribute("user", userToCheck);
        return "redirect:/home";
    }

//  todo przerobić błedy na polskie znaki

    @GetMapping("/register")
    public String register(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/register");
        return "user/register";
    }

    @PostMapping("/register")
    public String saveUser(Model model, HttpServletRequest request, @Validated({FullValidationUserGroup.class}) User user, BindingResult errors) {

        if (errors.hasErrors()) {
            return "user/register";
        }

        if (user.getPassword().equals(user.getRepeatedPassword())) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

            User checkUser = userRepository.findFirstByLogin(user.getLogin());
            if (checkUser != null) {
                model.addAttribute("pwdErr", "Taki użytkownik już istnieje !");
                return "user/register";
            }
            checkUser = userRepository.findFirstByEmail(user.getEmail());
            if (checkUser != null) {
                model.addAttribute("pwdErr", "Email musi być unikalny !");
                return "user/register";
            }
            userRepository.save(user);
            //todo autologin po rejestracji
            return "redirect:/home";
        } else {
            model.addAttribute("pwdErr", "Hasła muszą być takie same!");
            return "user/register";

        }
    }

    public boolean isLogedIn(HttpSession sess) {
        return (sess.getAttribute("user") != null);
    }


    /**
     * I don't check if session is null - it doesn't matter (we can assing her null anyway)
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/home";
    }
}

