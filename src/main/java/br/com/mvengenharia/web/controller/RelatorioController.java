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

import br.com.mvengenharia.business.entities.Relatorio;
import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.NaoConformidade;
import br.com.mvengenharia.business.services.RelatorioService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;
import br.com.mvengenharia.business.services.NaoConformidadeService;

@Controller
public class RelatorioController {


    @Autowired
    private RelatorioService relatorioService;
    
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

      
    public RelatorioController() {
        super();
    }
    
    @RequestMapping(value = "/relatorio/listaInspecaoParaRelatar", method = RequestMethod.GET)
	public ModelAndView showInspecaoParaRelatar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("relatorio/listaInspecaoParaRelatar");
		return mav;
	}
    
    @RequestMapping(value = "/relatorio/{idInspecao}", method = RequestMethod.GET)
    public ModelAndView showRelatorio(@PathVariable long idInspecao) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("relatorio/inspecaoRelatorio");
    	mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
    	Relatorio relatorio = new Relatorio();
    	mav.addObject("relatorio", relatorio);
        return mav;
    }      
   
    @RequestMapping(value="/relatorio/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveRelatorio(@PathVariable long idInspecao, @Valid Relatorio relatorio, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inspecao", this.inspecaoService.findOne(idInspecao)); 
            return "relatorio/inspecaoRelatorio";
        }
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Relatorio> listaRelatorios = insp.getRelatorios();
        for(Relatorio rel : listaRelatorios)
        {
        	rel.setUltimo(false);
        	this.relatorioService.addOrUpdate(rel);
        }
        relatorio.setFuncionarioRelator(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
    	relatorio.setInspecao(this.inspecaoService.findOne(idInspecao));    
        relatorio.setDataFimRelatorio(new Date());
    	relatorio.setUltimo(true);
        this.relatorioService.addOrUpdate(relatorio);
        model.clear();       
        return "redirect:/relatorio/" + idInspecao;
    } 
    
    @RequestMapping(value="/relatorio/iniciarNovoRelatorio/{idInspecao}", method = RequestMethod.GET)
    public String iniciarRelatorio(@PathVariable long idInspecao, final ModelMap model) {
        Inspecao insp = this.inspecaoService.findOne(idInspecao);
        List<Relatorio> listaRels = insp.getRelatorios();
        for(Relatorio rel : listaRels)
        {
        	rel.setUltimo(false);
        	this.relatorioService.addOrUpdate(rel);
        }        
        Relatorio relatorio = new Relatorio();
        relatorio.setDataInicioRelatorio(new Date());
        relatorio.setFuncionarioRelator(this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
        relatorio.setUltimo(true);
        relatorio.setInspecao(insp);
        this.relatorioService.addOrUpdate(relatorio);
        return "redirect:/relatorio/" + idInspecao;
    } 
}
