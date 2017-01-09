package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Permissoes;
import br.com.mvengenharia.business.services.PermissoesService;

@Controller
public class PermissoesController {


    @Autowired
    private PermissoesService permissoesService;
    
      
    public PermissoesController() {
        super();
    }
     
    @ModelAttribute("allPermissoes")
    public Iterable<Permissoes> populatePermissoes() {
        return this.permissoesService.findAll();
    }
   
    
    @RequestMapping("/permissoes")
    public String showPermissoes(final Permissoes permissoes) {
          return "permissoes/permissoes";
    }      
    
    @RequestMapping(value="/permissoes", params={"save"})
    public String savePermissoes(final Permissoes permissoes, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "permissoes";
        }
        this.permissoesService.addOrUpdate(permissoes);
        model.clear();
        return "redirect:/permissoes";
    } 
    
    @RequestMapping(value="/permissoes/remover/{id}")
    public String savePermissoes(@PathVariable Long id) {
        this.permissoesService.remove(id);
        return "redirect:/permissoes";
    }  


}
