package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Feriado;
import br.com.mvengenharia.business.services.FeriadoService;

@Controller
public class FeriadoController {


    @Autowired
    private FeriadoService feriadoService;
    
      
    public FeriadoController() {
        super();
    }
     
    @ModelAttribute("allFeriados")
    public Iterable<Feriado> populateFeriados() {
        return this.feriadoService.findAll();
    }
   
    
    @RequestMapping("/feriado")
    public String showFeriado(final Feriado feriado) {
          return "feriado/feriado";
    }      
    
    @RequestMapping(value="/feriado", params={"save"})
    public String saveFeriado(final Feriado feriado, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "feriado/feriado";
        }
        this.feriadoService.addOrUpdate(feriado);
        model.clear();
        return "redirect:/feriado";
    } 
    
    @RequestMapping(value="/feriado/remover/{id}")
    public String saveFeriado(@PathVariable Long id) {
        this.feriadoService.remove(id);
        return "redirect:/feriado";
    }  


}
