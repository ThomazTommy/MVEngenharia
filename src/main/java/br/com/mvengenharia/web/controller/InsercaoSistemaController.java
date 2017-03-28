package br.com.mvengenharia.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Fase;
import br.com.mvengenharia.business.entities.InsercaoSistema;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.InsercaoSistemaService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class InsercaoSistemaController {


    @Autowired
    private InsercaoSistemaService insercaoSistemaService;
    
    @Autowired
    private InspecaoService inspecaoService;
       
    @Autowired
    private FuncionarioService funcionarioService;
        
    public InsercaoSistemaController() {
        super();
    }
    
	@RequestMapping(value = "/insercaoSistema/listaInspecaoParaInsercaoSistema", method = RequestMethod.GET)
	public ModelAndView showInspecaoParaInsercaoSistema() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insercaoSistema/listaInspecaoParaInsercaoSistema");		
		return mav;
	}
    
    @RequestMapping(value = "/insercaoSistema/{idInspecao}", method = RequestMethod.GET)
    public ModelAndView showInsercaoSistema(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("insercaoSistema/inspecaoInsercaoSistema");
    	mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
    	InsercaoSistema insercaoSistema = new InsercaoSistema();
    	insercaoSistema.setDataHoraInsercao(new Date());
    	mav.addObject("insercaoSistema", insercaoSistema);
        return mav;
    }      
   
    @RequestMapping(value="/insercaoSistema/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveInsercaoSistema(@PathVariable long idInspecao, InsercaoSistema insercaoSistema, final BindingResult bindingResult, final ModelMap model) {
        if (insercaoSistema.getDataHoraInsercao() == null){
        	bindingResult.rejectValue("dataHoraInsercao","dataHoraInsercao.invalido","Data Hora Cadastro inválida");
        }
        if (insercaoSistema.getDataHoraInsercaoSistemaCliente() == null){
        	bindingResult.rejectValue("dataHoraInsercaoSistemaCliente","dataHoraInsercaoSistemaCliente.invalido","Data Hora Inserção no Cliente inválida");
        }
    	
    	if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao)); 
            return "insercaoSistema/inspecaoInsercaoSistema";
        }
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<InsercaoSistema> listaInsercaoSistemas = insp.getInsercaoSistemas();
        for(InsercaoSistema rel : listaInsercaoSistemas)
        {
        	rel.setUltimo(false);
        	this.insercaoSistemaService.addOrUpdate(rel);
        }
        insercaoSistema.setFuncionarioResponsavelInsercao(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	insercaoSistema.setInspecao(insp);    
        insercaoSistema.setDataHoraInsercao(new Date());
    	insercaoSistema.setUltimo(true);
        this.insercaoSistemaService.addOrUpdate(insercaoSistema);
        Fase fase = new Fase();
        fase.setIdFase(9);
        insp.setFase(fase);
        this.inspecaoService.addOrUpdate(insp);
        model.clear();       
        return "redirect:/insercaoSistema/" + idInspecao;
    } 
    
    @RequestMapping(value="/insercaoSistema/iniciarNovaInsercaoSistema/{idInspecao}", method = RequestMethod.GET)
    public String iniciarInsercaoSistema(@PathVariable long idInspecao, final ModelMap model) {
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<InsercaoSistema> listaRels = insp.getInsercaoSistemas();
        for(InsercaoSistema rel : listaRels)
        {
        	rel.setUltimo(false);
        	this.insercaoSistemaService.addOrUpdate(rel);
        }        
       InsercaoSistema insercaoSistema = new InsercaoSistema();
       insercaoSistema.setUltimo(true);
       insercaoSistema.setInspecao(insp);
       this.insercaoSistemaService.addOrUpdate(insercaoSistema);
        return "redirect:/insercaoSistema/" + idInspecao;
    } 
}
