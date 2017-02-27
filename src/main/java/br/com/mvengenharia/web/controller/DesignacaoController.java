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
import br.com.mvengenharia.business.services.DesignacaoService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class DesignacaoController {


    @Autowired
    private DesignacaoService designacaoService;
    
    @Autowired
    private InspecaoService inspecaoService;
    
    @Autowired
    private FuncionarioService funcionarioService;
    
    @ModelAttribute("allFuncionarios")
	public Iterable<Funcionario> populateFuncionarios() {
		return this.funcionarioService.findAll();
	}
    
    public DesignacaoController() {
        super();
    }
     

    @RequestMapping(value = "/designacao/{idInspecao}", method = RequestMethod.GET)
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
    
    @RequestMapping(value="/designacao/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveDesignacao(@PathVariable long idInspecao, @Valid Designacao designacao, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao));   
            model.addAttribute("agendamento", new Agendamento());
        	return "inspecao/inspecaoAgendamentoDesignacao";
        }
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Designacao> listaDsgs = insp.getDesignacoes();
        for(Designacao dsg : listaDsgs)
        {
        	dsg.setUltima(false);
        	this.designacaoService.addOrUpdate(dsg);
        }
        designacao.setUltima(true);
        designacao.setDataDesignacao(new Date());
    	designacao.setFuncionarioDesignador(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	designacao.setInspecao(this.inspecaoService.findOne(idInspecao));
    	this.designacaoService.addOrUpdate(designacao);
        model.clear();       
        return "redirect:/designacao/" + idInspecao;
    } 
        
    @RequestMapping(value="/designacao/remover/{idInspecao}/{idDesignacao}")
    public String removeDesignacao(@PathVariable Long idInspecao, @PathVariable Long idDesignacao) {
        this.designacaoService.remove(idDesignacao);
        return "redirect:/designacao/" + idInspecao;
    }  

}
