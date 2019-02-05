package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.BrandRepository;
import pl.coderslab.repository.PhoneRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.validator.AdminValidationUserGroup;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/dashboard/user")
    public String user(Model model){
        return "admin/user";
//        todo edycja i usuniecie usera
    }


    @GetMapping("/admin/dashboard/user/{id}")
    public String edit(@PathVariable Long id, Model model , HttpServletRequest request){
        User user = userRepository.findOne(id);
        model.addAttribute("editingUser",user);
        model.addAttribute("admin",userRepository.findOne((long) 1));
        model.addAttribute("formAction", request.getContextPath() + "/admin/dashboard/user/"+id);
        return "admin/user";
    }

    @PostMapping("/admin/dashboard/user/{id}")
    public String update(@Validated({AdminValidationUserGroup.class}) User user , BindingResult errors, HttpServletRequest req, Model model, HttpSession session){
        if (errors.hasErrors()) {

//            List<ObjectError> allErrors = errors.getAllErrors();
//            for(ObjectError err : allErrors){
//                System.out.println(err.toString());
//            }
//            todo: wyświetlić błędy walidacji w widoku
            model.addAttribute("errors",errors);
            model.addAttribute("user",session.getAttribute("user"));
            model.addAttribute("editingUser",user);
            return "admin/user";
//          todo zrobić w widoku user numeracje po kolei
//            todo zrobic porzadek z nazwami błedów w widokach (żeby nie było wszędzie pwdErro)
        }

        User checkUser = userRepository.findFirstByLogin(user.getLogin());
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
            model.addAttribute("loginErr", "Taki użytkownik już istnieje !");
            model.addAttribute("user",session.getAttribute("user"));
            model.addAttribute("editingUser",user);
            return "admin/user";
        }
        checkUser = userRepository.findFirstByEmail(user.getEmail());
        if (checkUser != null && !checkUser.getId().equals(user.getId())) {
            model.addAttribute("emailErr", "Email musi być unikalny !");
            model.addAttribute("user",session.getAttribute("user"));
            model.addAttribute("editingUser",user);
            return "admin/user";
        }

        userRepository.save(user);
        //todo wynieść to do serwisu

        return "redirect:"+req.getContextPath()+"/admin/dashboard/user";
    }


    @GetMapping("/admin/dashboard/confirm/{id}")
    public String confirm(Model model,HttpServletRequest request,@PathVariable Long id){
        User user = userRepository.findOne(id);

        model.addAttribute("deletingUser",user);
        model.addAttribute("confirm",user);
        return "admin/user";
    }

    @GetMapping("/admin/dashboard/delete/{id}")
    public String delete(User user,HttpServletRequest request){
        userRepository.delete(user);
        return "redirect:"+request.getContextPath()+"/admin/dashboard/user";
    }

// todo dokończyć edycje - w encji User należy dodać osobny walidator i robić osobną walidację +
//   problem z niezapisywaniem pola admina


    @ModelAttribute("users")
    public List<User> userList(){ return userRepository.findAll(); }

    @ModelAttribute("admin")
    public User admin(){return userRepository.findOne((long) 1); }


}
