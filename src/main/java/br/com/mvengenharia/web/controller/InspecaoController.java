package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class InspecaoController {


    @Autowired
    private InspecaoService inspecaoService;
    
      
    public InspecaoController() {
        super();
    }
     
    @ModelAttribute("allInspecaos")
    public Iterable<Inspecao> populateInspecaos() {
        return this.inspecaoService.findAll();
    }
   
    
    @RequestMapping("/inspecao")
    public String showInspecao(final Inspecao inspecao) {
          return "inspecao/inspecao";
    }      
    
    @RequestMapping(value="/inspecao", params={"save"})
    public String saveInspecao(final Inspecao inspecao, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "inspecao/inspecao";
        }
        this.inspecaoService.addOrUpdate(inspecao);
        model.clear();
        return "redirect:/inspecao";
    } 
    
    @RequestMapping(value="/inspecao/remover/{id}")
    public String saveInspecao(@PathVariable Long id) {
        this.inspecaoService.remove(id);
        return "redirect:/inspecao";
    }  


}
