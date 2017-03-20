package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Fase;
import br.com.mvengenharia.business.services.FaseService;

@Controller
public class FaseController {


    @Autowired
    private FaseService faseService;
    
      
    public FaseController() {
        super();
    }
     
    @ModelAttribute("allFases")
    public Iterable<Fase> populateFases() {
        return this.faseService.findAll();
    }
   
    
    @RequestMapping("/fase")
    public String showFase(final Fase fase) {
          return "fase/fase";
    }      
    
    @RequestMapping(value="/fase", params={"save"})
    public String saveFase(final Fase fase, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "fase/fase";
        }
        this.faseService.addOrUpdate(fase);
        model.clear();
        return "redirect:/fase";
    } 
    
    @RequestMapping(value="/fase/remover/{id}")
    public String saveFase(@PathVariable Long id) {
        this.faseService.remove(id);
        return "redirect:/fase";
    }  


}
