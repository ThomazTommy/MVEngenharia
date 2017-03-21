package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Honorario;
import br.com.mvengenharia.business.services.HonorarioService;

@Controller
public class HonorarioController {


    @Autowired
    private HonorarioService honorarioService;
    
      
    public HonorarioController() {
        super();
    }
     
    @RequestMapping("/honorario")
    public String showHonorario(final Honorario honorario) {
          return "honorario/honorario";
    }      
    
    @RequestMapping(value="/honorario", params={"save"})
    public String saveHonorario(final Honorario honorario, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "honorario/honorario";
        }
        this.honorarioService.addOrUpdate(honorario);
        model.clear();
        return "redirect:/honorario";
    } 
    
    @RequestMapping(value="/honorario/remover/{id}")
    public String saveHonorario(@PathVariable Long id) {
        this.honorarioService.remove(id);
        return "redirect:/honorario";
    }  


}
