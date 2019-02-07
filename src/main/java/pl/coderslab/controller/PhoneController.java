package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Brand;
import pl.coderslab.entity.Phone;
import pl.coderslab.entity.User;
import pl.coderslab.repository.BrandRepository;
import pl.coderslab.repository.PhoneRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * CREATErud
     */

    @GetMapping("/admin/phone/add")
    public String addPhone(Model model, HttpServletRequest request) {
        model.addAttribute("phone", new Phone());
        model.addAttribute("formAction", request.getContextPath() + "/admin/phone/add");
        return "phone/add";
    }

    @PostMapping("/admin/phone/add")
    public String savePhone(@Valid Phone phone , BindingResult errors, HttpServletRequest request,Model model) {
        if (errors.hasErrors()) {
            return "phone/add";
        }

        Phone phoneToCheck = phoneRepository.findFirstByName(phone.getName());
        if (phoneToCheck != null && !phoneToCheck.getId().equals(phone.getId())){
            model.addAttribute("nameErr", "Taki telefon już istnieje!");
            return "phone/add";
        }

            phoneRepository.save(phone);
        return "redirect:"+request.getContextPath()+"/phone/list";
    }

    /**
     * Add phone to favorite user (user from session)
     */
    @GetMapping("/user/phone/add/{id}")
    public String addFavoriteSmartphone(@PathVariable Long id , HttpSession session){
        Phone phone = phoneRepository.findOne(id);
        User user = (User) session.getAttribute("user");
        user .addFavoriteSmartphone(phone);
        userRepository.save(user);
        phone.addUser(user);
        phoneRepository.save(phone);
        session.setAttribute("user",user);
        return "redirect:/phone/list";
    }

    /**
     * cREADud
     */

    /**
     * crUPDATEd
     */

    @GetMapping("/admin/phone/form/{id}")
    public String editPhone(Model model, HttpServletRequest request, @PathVariable Long id){
        Phone phone = phoneRepository.findOne(id);
        model.addAttribute("phone", phone);
        model.addAttribute("formAction", request.getContextPath()+"/admin/phone/add");
        return "phone/add";
    }
    /**
     * cruDELETE
     */

    @GetMapping("/admin/phone/delete/{id}")
    public String deletePhone(@ModelAttribute Phone phone, HttpServletRequest request){
        phoneRepository.delete(phone);

        return "redirect:"+request.getContextPath()+"/phone/list";
    }

    /**
     * findALL and list them
     */
    @GetMapping("/phone/list")
    public String showAll(Model model){
        model.addAttribute("phones",phoneRepository.findAll());
        return "phone/list";
//   todo zrobić tak,żeby w widoku pojawiało się maksymalnie 20 telefonów
    }


    @GetMapping("/phone/list/{id}")
    public String showAllSmartphonesById(Model model , @PathVariable Long id){
        Phone phone = phoneRepository.findOne(id);
        model.addAttribute("phone",phone);
        return "phone/single";
    }


    @ModelAttribute("brandsList")
    public List<Brand> brandsList(){ return brandRepository.findAll(); }

}
