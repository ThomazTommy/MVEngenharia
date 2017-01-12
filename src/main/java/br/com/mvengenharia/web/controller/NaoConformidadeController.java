package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Gravidade;
import br.com.mvengenharia.business.entities.NaoConformidade;
import br.com.mvengenharia.business.services.GravidadeService;
import br.com.mvengenharia.business.services.NaoConformidadeService;

@Controller
public class NaoConformidadeController {


    @Autowired
    private NaoConformidadeService naoConformidadeService;
    
    @Autowired
    private GravidadeService gravidadeService;
    
      
    public NaoConformidadeController() {
        super();
    }
     
    @ModelAttribute("allNaoConformidades")
    public Iterable<NaoConformidade> populateNaoConformidades() {
        return this.naoConformidadeService.findAll();
    }
    
    @ModelAttribute("allGravidades")
    public Iterable<Gravidade> populateGravidades() {
        return this.gravidadeService.findAll();
    }
   
    
    @RequestMapping("/naoConformidade")
    public String showNaoConformidade(final NaoConformidade naoConformidade) {
          return "naoConformidade/naoConformidade";
    }      
    
    @RequestMapping(value="/naoConformidade", params={"save"})
    public String saveNaoConformidade(final NaoConformidade naoConformidade, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "naoConformidade/naoConformidade";
        }
        this.naoConformidadeService.addOrUpdate(naoConformidade);
        model.clear();
        return "redirect:/naoConformidade";
    } 
    
    @RequestMapping(value="/naoConformidade/remover/{id}")
    public String saveNaoConformidade(@PathVariable Long id) {
        this.naoConformidadeService.remove(id);
        return "redirect:/naoConformidade";
    }  


}
