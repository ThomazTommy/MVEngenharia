package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.services.EstadoService;

@Controller
public class EstadoController {


    @Autowired
    private EstadoService estadoService;
      
    
    public EstadoController() {
        super();
    }
     
    @ModelAttribute("allEstados")
    public Iterable<Estado> populateEstados() {
        return this.estadoService.findAll();
    }
    
    @RequestMapping("/estado")
    public String showEstado(final Estado estado) {
          return "estado/estado";
    }      
    
    @RequestMapping(value="/estado", params={"save"})
    public String saveEstado(final Estado estado, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "estado/estado";
        }
        this.estadoService.addOrUpdate(estado);
        model.clear();
        return "redirect:/estado";
    } 
    
    @RequestMapping(value="/estado/remover/{id}")
    public String saveEstado(@PathVariable Long id) {
        this.estadoService.remove(id);
        return "redirect:/estado";
    }  


}
