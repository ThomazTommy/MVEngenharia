package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Agendamento;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.AgendamentoService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class AgendamentoController {


    @Autowired
    private AgendamentoService agendamentoService;
    
    @Autowired
    private InspecaoService inspecaoService;
    
      
    public AgendamentoController() {
        super();
    }
     
    @RequestMapping("/agendamento/{idInspecao}")
    public ModelAndView showAgendamento(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("agendamento/agendamento");
    	mav.addObject("Inspecao", inspecaoService.findOne(idInspecao));    	
          return mav;
    }      
    
    @RequestMapping(value="/agendamento", params={"save"})
    public String saveAgendamento(final Agendamento agendamento, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "agendamento/agendamento";
        }
        this.agendamentoService.addOrUpdate(agendamento);
        model.clear();
        return "redirect:/agendamento";
    } 
    
    @RequestMapping(value="/agendamento/remover/{idInspecao}/{idAgendamento}")
    public String saveAgendamento(@PathVariable Long idInspecao, @PathVariable Long idAgendamento) {
        this.agendamentoService.remove(idAgendamento);
        return "redirect:/agendamento/" + idInspecao;
    }  


}
