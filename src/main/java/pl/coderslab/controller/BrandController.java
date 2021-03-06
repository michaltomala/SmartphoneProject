package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
// todo zabezpieczenie przed sql injectionem
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * CREATErud
     */
    @GetMapping("/admin/brand/add")
    public String addBrand(Model model, HttpServletRequest request) {
        model.addAttribute("brand", new Brand());
        model.addAttribute("formAction", request.getContextPath() + "/admin/brand/add");
        return "brand/add";
    }

    @PostMapping("/admin/brand/add")
    public String saveBrand(@Valid Brand brand , BindingResult errors, HttpServletRequest request , Model model) {
        if (errors.hasErrors()) {
            return "brand/add";
        }

        Brand brandToCheck = brandRepository.findFirstByName(brand.getName());
        if (brandToCheck != null && !brandToCheck.getId().equals(brand.getId())) {
            model.addAttribute("nameErr", "Taka marka już istnieje !");
            return "brand/add";
        }
        brandRepository.save(brand);
        return "redirect:"+request.getContextPath()+"/brand/list";
    }

    /**
     * cREADud
     */

    /**
     * crUPDATEd
     */

    @GetMapping("/admin/brand/form/{id}")
    public String editBrand(Model model, HttpServletRequest request, @PathVariable Long id){
        Brand brand = brandRepository.findOne(id);
        model.addAttribute("brand", brand);
        model.addAttribute("formAction", request.getContextPath()+"/admin/brand/add");
        return "brand/add";
    }

    /**
     * cruDELETE
     */

    @GetMapping("/admin/brand/delete/{id}")
    public String deleteBrand(@ModelAttribute Brand brand, HttpServletRequest request){
        brandRepository.delete(brand);

        //todo ma usunąć kategorię tylko w przypadku gdy nie ma powiązanych telefonów
        return "redirect:"+request.getContextPath()+"/brand/list";
    }

    /**
     * findALL and list them
     */
    @GetMapping("/brand/list")
    public String showAll(Model model){
        model.addAttribute("brands",brandRepository.findAll());
        return "brand/list";
    }

    /**
     *  findALL phones by Brand
     */


    @GetMapping("/brand/list/{id}")
    public String showAllSmartphonesById(Model model , @PathVariable Long id){
        Brand brand = brandRepository.findOne(id);
        model.addAttribute("brand",brand);
        model.addAttribute("phones",phoneRepository.findAllByBrand(brand));
        return "brand/single";
    }

    @GetMapping("/admin/brand/addphone/{id}")
    public String addPhoneByBrand(Model model , @PathVariable Long id , HttpServletRequest request){
        Brand brand = brandRepository.findOne(id);
        Phone phone = new Phone();
        phone.setBrand(brand);
        model.addAttribute("phone", phone);
        model.addAttribute("formAction", request.getContextPath() + "/admin/phone/add");
        return "phone/add";
    }

    @ModelAttribute("brandsList")
    public List<Brand> brandsList(){ return brandRepository.findAll(); }


}
