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
        if (session.getAttribute("userFromSession") != null) {
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
//       todo: warunek poniżej do sprawdzenia
        if (!(BCrypt.checkpw(user.getPassword(), userToCheck.getPassword()))) {
            model.addAttribute("errDB", "Hasło się nie zgadza,spróbuj jeszcze raz");
            return "user/login";
        }

        session.setAttribute("userFromSession", userToCheck);
        return "redirect:/home";
    }


    @GetMapping("/register")
    public String register(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        model.addAttribute("formAction", request.getContextPath() + "/register");
        return "user/register";
    }

    @PostMapping("/register")
    public String saveUser(Model model, HttpServletRequest request, @Validated({FullValidationUserGroup.class}) User user, BindingResult errors,HttpSession session) {

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
            session.setAttribute("userFromSession",user);
            return "redirect:/home";
        } else {
            model.addAttribute("pwdErr", "Hasła muszą być takie same!");
            return "user/register";

        }
    }

    //        todo zaktualizowac nazwy przycisków


    /**
     * I don't check if session is null - it doesn't matter (we can assing her null anyway)
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("userFromSession", null);
        return "redirect:/home";
    }
}

