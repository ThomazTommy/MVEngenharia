package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.CustoInspecao;
import br.com.mvengenharia.business.entities.Fase;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.AtividadeService;
import br.com.mvengenharia.business.services.CustoInspecaoService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class CustoInspecaoController {


    @Autowired
    private CustoInspecaoService custoInspecaoService;
    
    @Autowired
    private InspecaoService inspecaoService;
    
    @Autowired
    private AtividadeService atividadeService;
      
    public CustoInspecaoController() {
        super();
    }        
    
    @ModelAttribute("allAtividades")
	public Iterable<Atividade> populateAtividades() {
		return this.atividadeService.findAll();
	}
    
    @RequestMapping(value="/custoInspecao/editar/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveCustoInspecao(@PathVariable Long idInspecao, final Inspecao inspecao, final BindingResult bindingResult, final ModelMap model) {
    	CustoInspecao custoInspecao = inspecao.getCustoInspecao();
    	Inspecao insp = this.inspecaoService.findOne(idInspecao);
    	if(insp.getCustoInspecao() != null)
    	{
    	custoInspecao.setIdCustoInspecao(insp.getCustoInspecao().getIdCustoInspecao());
    	}
    	custoInspecao.setCustoInformado(true);
    	custoInspecao.setInspecao(insp);
    	
    	insp.setCustoInspecao(custoInspecao);
    	this.inspecaoService.addOrUpdate(insp);
    	model.clear();
        return "redirect:/custoInspecao/detalheInspecao/" + idInspecao;
    } 
    

    @RequestMapping(value = "/custoInspecao/editar/{idInspecao}", method = RequestMethod.GET)
	public ModelAndView custoInspecaoEdicao(@PathVariable Long idInspecao) {
		Inspecao insp =  this.inspecaoService.findOne(idInspecao);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("custoInspecao/custoInspecaoForm");
		mav.addObject("inspecao",insp);
		return mav;
	}
    
    
    
    @RequestMapping(value = "/custoInspecao/detalheInspecao/{idInspecao}")
	public ModelAndView custoInspecaoDetalhe(@PathVariable Long idInspecao) {
		Inspecao insp =  this.inspecaoService.findOne(idInspecao);
		if(insp.getCustoInspecao() == null)
		{
			insp.setCustoInspecao(new CustoInspecao());
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("custoInspecao/custoInspecao");
		mav.addObject("inspecao",insp);
		return mav;
	}
    
    @RequestMapping(value = "/custoInspecao")
	public ModelAndView custoInspecao() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("custoInspecao/listaInspecaoParaCustoInspecao");
		return mav;
	}

}
