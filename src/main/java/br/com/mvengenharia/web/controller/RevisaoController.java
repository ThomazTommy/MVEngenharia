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

import br.com.mvengenharia.business.entities.Revisao;
import br.com.mvengenharia.business.entities.Designacao;
import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.NaoConformidade;
import br.com.mvengenharia.business.services.RevisaoService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;
import br.com.mvengenharia.business.services.NaoConformidadeService;

@Controller
public class RevisaoController {


    @Autowired
    private RevisaoService revisaoService;
    
    @Autowired
    private InspecaoService inspecaoService;
    
    @Autowired
    private NaoConformidadeService naoConformidadeService;
    
    @Autowired
    private FuncionarioService funcionarioService;
    
    @ModelAttribute("allFuncionarios")
	public Iterable<Funcionario> populateFuncionarios() {
		return this.funcionarioService.findAll();
	}
    
    @ModelAttribute("allNaoConformidades")
  	public Iterable<NaoConformidade> populateNaoConformidades() {
  		return this.naoConformidadeService.findAll();
  	}

      
    public RevisaoController() {
        super();
    }
    
    @RequestMapping(value = "/revisao/{idInspecao}", method = RequestMethod.GET)
    public ModelAndView showRevisao(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("inspecao/inspecaoRevisao");
    	mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
    	Revisao revisao = new Revisao();
    	mav.addObject("revisao", revisao);
        return mav;
    }      
   
    @RequestMapping(value="/revisao/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveRevisao(@PathVariable long idInspecao, @Valid Revisao revisao, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao)); 
            return "inspecao/inspecaoRevisao";
        }
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Revisao> listaRevisaos = insp.getRevisaos();
        for(Revisao rel : listaRevisaos)
        {
        	rel.setUltimo(false);
        	this.revisaoService.addOrUpdate(rel);
        }
        revisao.setFuncionarioRevisor(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	revisao.setInspecao(this.inspecaoService.findOne(idInspecao));    
        revisao.setDataFimRevisao(new Date());
    	revisao.setUltimo(true);
        this.revisaoService.addOrUpdate(revisao);
        model.clear();       
        return "redirect:/revisao/" + idInspecao;
    } 
    
    @RequestMapping(value="/revisao/iniciarNovaRevisao/{idInspecao}", method = RequestMethod.GET)
    public String iniciarRevisao(@PathVariable long idInspecao, final ModelMap model) {
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Revisao> listaRels = insp.getRevisaos();
        for(Revisao rel : listaRels)
        {
        	rel.setUltimo(false);
        	this.revisaoService.addOrUpdate(rel);
        }        
        Revisao revisao = new Revisao();
        revisao.setDataInicioRevisao(new Date());
        revisao.setFuncionarioRevisor(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
        revisao.setUltimo(true);
        revisao.setInspecao(insp);
        this.revisaoService.addOrUpdate(revisao);
        return "redirect:/revisao/" + idInspecao;
    } 
}
