package br.com.mvengenharia.web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.AprovacaoSistema;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.AprovacaoSistemaService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class AprovacaoSistemaController {


    @Autowired
    private AprovacaoSistemaService aprovacaoSistemaService;
    
    @Autowired
    private InspecaoService inspecaoService;
       
    @Autowired
    private FuncionarioService funcionarioService;
        
    public AprovacaoSistemaController() {
        super();
    }
    
    @RequestMapping(value = "/aprovacaoSistema/listaInspecaoParaAprovacaoSistema", method = RequestMethod.GET)
	public ModelAndView showInspecaoParaAprovacaoSistema() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("aprovacaoSistema/listaInspecaoParaAprovacaoSistema");
		return mav;
	}
    
    @RequestMapping(value = "/aprovacaoSistema/{idInspecao}", method = RequestMethod.GET)
    public ModelAndView showAprovacaoSistema(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("aprovacaoSistema/inspecaoAprovacaoSistema");
    	mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
    	AprovacaoSistema aprovacaoSistema = new AprovacaoSistema();
    	aprovacaoSistema.setDataHoraAprovacao(new Date());
    	mav.addObject("aprovacaoSistema", aprovacaoSistema);
        return mav;
    }      
   
    @RequestMapping(value="/aprovacaoSistema/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveAprovacaoSistema(@PathVariable long idInspecao, @Valid AprovacaoSistema aprovacaoSistema, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao)); 
            return "aprovacaoSistema/inspecaoAprovacaoSistema";
        }
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<AprovacaoSistema> listaAprovacaoSistemas = insp.getAprovacaoSistemas();
        for(AprovacaoSistema rel : listaAprovacaoSistemas)
        {
        	rel.setUltimo(false);
        	this.aprovacaoSistemaService.addOrUpdate(rel);
        }
        aprovacaoSistema.setFuncionarioAprovador(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	aprovacaoSistema.setInspecao(insp);    
        aprovacaoSistema.setDataHoraAprovacao(new Date());
    	aprovacaoSistema.setUltimo(true);
        this.aprovacaoSistemaService.addOrUpdate(aprovacaoSistema);
        model.clear();       
        return "redirect:/aprovacaoSistema/" + idInspecao;
    } 
    
    @RequestMapping(value="/aprovacaoSistema/iniciarNovaAprovacaoSistema/{idInspecao}", method = RequestMethod.GET)
    public String iniciarAprovacaoSistema(@PathVariable long idInspecao, final ModelMap model) {
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<AprovacaoSistema> listaRels = insp.getAprovacaoSistemas();
        for(AprovacaoSistema rel : listaRels)
        {
        	rel.setUltimo(false);
        	this.aprovacaoSistemaService.addOrUpdate(rel);
        }        
        AprovacaoSistema aprovacaoSistema = new AprovacaoSistema();
        aprovacaoSistema.setUltimo(true);
        aprovacaoSistema.setInspecao(insp);
        this.aprovacaoSistemaService.addOrUpdate(aprovacaoSistema);
        return "redirect:/aprovacaoSistema/" + idInspecao;
    } 
}
