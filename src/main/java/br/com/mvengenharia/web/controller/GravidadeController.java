package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Gravidade;
import br.com.mvengenharia.business.services.GravidadeService;

@Controller
public class GravidadeController {


    @Autowired
    private GravidadeService gravidadeService;
    
      
    public GravidadeController() {
        super();
    }
     
    @ModelAttribute("allGravidades")
    public Iterable<Gravidade> populateGravidades() {
        return this.gravidadeService.findAll();
    }
   
    
    @RequestMapping("/gravidade")
    public String showGravidade(final Gravidade gravidade) {
          return "gravidade/gravidade";
    }      
    
    @RequestMapping(value="/gravidade", params={"save"})
    public String saveGravidade(final Gravidade gravidade, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "gravidade/gravidade";
        }
        this.gravidadeService.addOrUpdate(gravidade);
        model.clear();
        return "redirect:/gravidade";
    } 
    
    @RequestMapping(value="/gravidade/remover/{id}")
    public String saveGravidade(@PathVariable Long id) {
        this.gravidadeService.remove(id);
        return "redirect:/gravidade";
    }  


}
