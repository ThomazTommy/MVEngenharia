package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.services.AtividadeService;

@Controller
public class AtividadeController {


    @Autowired
    private AtividadeService atividadeService;
    
      
    public AtividadeController() {
        super();
    }
     
    @ModelAttribute("allAtividades")
    public Iterable<Atividade> populateAtividades() {
        return this.atividadeService.findAll();
    }
   
    
    @RequestMapping("/atividade")
    public String showAtividade(final Atividade atividade) {
          return "atividade/atividade";
    }      
    
    @RequestMapping(value="/atividade", params={"save"})
    public String saveAtividade(final Atividade atividade, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "atividade";
        }
        this.atividadeService.addOrUpdate(atividade);
        model.clear();
        return "redirect:/atividade";
    } 
    
    @RequestMapping(value="/atividade/remover/{id}")
    public String saveAtividade(@PathVariable Long id) {
        this.atividadeService.remove(id);
        return "redirect:/atividade";
    }  


}
