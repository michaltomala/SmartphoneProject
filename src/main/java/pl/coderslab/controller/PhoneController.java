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
import pl.coderslab.repository.BrandRepository;
import pl.coderslab.repository.PhoneRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private BrandRepository brandRepository;


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
    public String savePhone(@Valid Phone phone , BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "phone/add";
        }
        phoneRepository.save(phone);
        return "redirect:"+request.getContextPath()+"/phone/list";
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
        model.addAttribute("formAction", request.getContextPath()+"/phone/add");
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
    }

//    przyszłościowo dodać tu też zdjęcie
    @GetMapping("/phone/list/{id}")
    public String showAllSmartphonesById(Model model , @PathVariable Long id){
        Phone phone = phoneRepository.findOne(id);
        model.addAttribute("phone",phone);
        return "phone/single";
    }


    @ModelAttribute("brandsList")
    public List<Brand> brandsList(){ return brandRepository.findAll(); }

}
