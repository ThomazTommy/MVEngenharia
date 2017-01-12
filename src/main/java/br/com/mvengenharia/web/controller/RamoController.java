package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Ramo;
import br.com.mvengenharia.business.services.RamoService;

@Controller
public class RamoController {


    @Autowired
    private RamoService ramoService;
    
      
    public RamoController() {
        super();
    }
     
    @ModelAttribute("allRamos")
    public Iterable<Ramo> populateRamos() {
        return this.ramoService.findAll();
    }
   
    
    @RequestMapping("/ramo")
    public String showRamo(final Ramo ramo) {
          return "ramo/ramo";
    }      
    
    @RequestMapping(value="/ramo", params={"save"})
    public String saveRamo(final Ramo ramo, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "ramo/ramo";
        }
        this.ramoService.addOrUpdate(ramo);
        model.clear();
        return "redirect:/ramo";
    } 
    
    @RequestMapping(value="/ramo/remover/{id}")
    public String saveRamo(@PathVariable Long id) {
        this.ramoService.remove(id);
        return "redirect:/ramo";
    }  


}
