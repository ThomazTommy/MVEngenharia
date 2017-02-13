package br.com.mvengenharia.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Agendamento;
import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.services.AgendamentoService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class AgendamentoController {


    @Autowired
    private AgendamentoService agendamentoService;
    
    @Autowired
    private InspecaoService inspecaoService;
    
    @Autowired
    private FuncionarioService funcionarioService;
    
    @ModelAttribute("allFuncionarios")
	public Iterable<Funcionario> populateFuncionarios() {
		return this.funcionarioService.findAll();
	}

      
    public AgendamentoController() {
        super();
    }
     
    @RequestMapping(value = "/agendamento/{idInspecao}", method = RequestMethod.GET)
    public ModelAndView showAgendamento(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("agendamento/agendamento");
    	mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
    	Agendamento agenda = new Agendamento();
    	agenda.setDtAgendamento(new Date());
    	agenda.setFuncionario(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	agenda.setInspecao(this.inspecaoService.findOne(idInspecao));
    	mav.addObject("agendamento", agenda);
        return mav;
    }      
    
    @RequestMapping(value="/agendamento/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveAgendamento(@PathVariable long idInspecao, @Valid Agendamento agendamento, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao));         
        	return "agendamento/agendamento";
        }
        this.agendamentoService.addOrUpdate(agendamento);
        model.clear();       
        return "redirect:/agendamento/" + idInspecao;
    } 
    
    @RequestMapping(value="/agendamento/remover/{idInspecao}/{idAgendamento}")
    public String saveAgendamento(@PathVariable Long idInspecao, @PathVariable Long idAgendamento) {
        this.agendamentoService.remove(idAgendamento);
        return "redirect:/agendamento/" + idInspecao;
    }  


}