package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Cobertura;
import br.com.mvengenharia.business.services.CoberturaService;

@Controller
public class CoberturaController {


    @Autowired
    private CoberturaService coberturaService;
    
      
    public CoberturaController() {
        super();
    }
     
    @ModelAttribute("allCoberturas")
    public Iterable<Cobertura> populateCoberturas() {
        return this.coberturaService.findAll();
    }
   
    
    @RequestMapping("/cobertura")
    public String showCobertura(final Cobertura cobertura) {
          return "cobertura/cobertura";
    }      
    
    @RequestMapping(value="/cobertura", params={"save"})
    public String saveCobertura(final Cobertura cobertura, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "cobertura/cobertura";
        }
        this.coberturaService.addOrUpdate(cobertura);
        model.clear();
        return "redirect:/cobertura";
    } 
    
    @RequestMapping(value="/cobertura/remover/{id}")
    public String saveCobertura(@PathVariable Long id) {
        this.coberturaService.remove(id);
        return "redirect:/cobertura";
    }  


}
