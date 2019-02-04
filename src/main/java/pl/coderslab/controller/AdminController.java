package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.BrandRepository;
import pl.coderslab.repository.PhoneRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        model.addAttribute("formAction", request.getContextPath() + "/admin/dashboard/user/"+id);
        return "admin/user";
    }

    @PostMapping("/admin/dashboard/user/{id}")
    public String update(@Validated({FullValidationUserGroup.class}) User user , BindingResult errors, HttpServletRequest req){
        if (errors.hasErrors()) {
            return "admin/user";
        }
        userRepository.save(user);
//  todo zmiana napisu w przuciskach (w widokach)
        return "redirect:"+req.getContextPath()+"/admin/dashboard/user";
    }
    @GetMapping("/admin/dashboard/confirm/{id}")
    public String confirm(Model model,HttpServletRequest request,@PathVariable Long id){
        User user = userRepository.findOne(id);
        model.addAttribute("confirm",user);
        return "admin/user";
    }

    @GetMapping("/admin/dashboard/delete/{id}")
    public String delete(User user,HttpServletRequest request){
        userRepository.delete(user);
        return "redirect:"+request.getContextPath()+"/admin/dashboard/user";
    }

// todo dokończyć edycje - w encji User należy dodać osobny walidator i robić osobną walidację +
//  dodać 2 zabezpieczenia z loginController
//  admin nie może edytować pola isAdmin oraz siebie usunąć (otoczyć całego li osobnym ifem czy jest adminem)
//   - nie wyświetlać go
//  usuwanie usera nie działa


    @ModelAttribute("users")
    public List<User> userList(){ return userRepository.findAll(); }
}
