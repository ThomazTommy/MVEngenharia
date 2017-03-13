package br.com.mvengenharia.web.controller;

import java.util.Date;
import java.util.List;

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
import br.com.mvengenharia.business.entities.Designacao;
import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.entities.Inspecao;
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
    public ModelAndView showDesignacao(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("inspecao/inspecaoAgendamentoDesignacao");
    	mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
    	Designacao designacao = new Designacao();
    	Agendamento agenda = new Agendamento();
    	mav.addObject("designacao", designacao);
    	mav.addObject("agendamento", agenda);
        return mav;
    }      

    @RequestMapping(value = "/agendamento/listainspecao", method = RequestMethod.GET)
 		public ModelAndView showInspecao() {
 			ModelAndView mav = new ModelAndView();
 			mav.setViewName("agendamento/listaInspecao");			
 			return mav;
 		}
    @RequestMapping(value="/agendamento/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveAgendamento(@PathVariable long idInspecao, @Valid Agendamento agendamento, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao)); 
            model.addAttribute("designacao", new Designacao());
        	return "inspecao/inspecaoAgendamentoDesignacao";
        }
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Agendamento> listaAgds = insp.getAgendamentos();
        for(Agendamento agd : listaAgds)
        {
        	agd.setUltimo(false);
        	this.agendamentoService.addOrUpdate(agd);
        }
        agendamento.setDtAgendamento(new Date());
    	agendamento.setFuncionario(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	agendamento.setInspecao(this.inspecaoService.findOne(idInspecao));    
        agendamento.setUltimo(true);
        this.agendamentoService.addOrUpdate(agendamento);
        model.clear();       
        return "redirect:/agendamento/" + idInspecao;
    } 
    
    @RequestMapping(value="/agendamento/confirmar/{idInspecao}/{idAgendamento}", method = RequestMethod.GET)
    public String confirmarAgendamento(@PathVariable long idInspecao, @PathVariable long idAgendamento, final ModelMap model) {
        Agendamento agenda = this.agendamentoService.findOne(idAgendamento);
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Agendamento> listaAgds = insp.getAgendamentos();
        for(Agendamento agd : listaAgds)
        {
        	agd.setConfirmacao(false);
        	this.agendamentoService.addOrUpdate(agd);
        }
        agenda.setConfirmacao(true);
        agenda.setDtConfirmacao(new Date());
        agenda.setFuncionarioConfirmacao(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
        this.agendamentoService.addOrUpdate(agenda);
        insp.setDtAgendada(agenda.getDtAgendada());
        this.inspecaoService.addOrUpdate(insp);
        model.clear();       
        return "redirect:/agendamento/" + idInspecao;
    } 
    
    
    @RequestMapping(value="/agendamento/remover/{idInspecao}/{idAgendamento}")
    public String saveAgendamento(@PathVariable Long idInspecao, @PathVariable Long idAgendamento) {
        this.agendamentoService.remove(idAgendamento);
        return "redirect:/agendamento/" + idInspecao;
    }  


}
