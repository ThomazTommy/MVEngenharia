package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.CustoInspecao;
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
    
    @RequestMapping(value="/custoInspecao/{idInspecao}", params={"save"}, method = RequestMethod.POST)
    public String saveCustoInspecao(@PathVariable Long idInspecao, final Inspecao inspecao, final BindingResult bindingResult, final ModelMap model) {
    	CustoInspecao custoInspecao = inspecao.getCustoInspecao();
    	Inspecao insp = this.inspecaoService.findOne(idInspecao);
    	custoInspecao.setCustoInformado(true);
    	custoInspecao.setInspecao(insp);
    	this.custoInspecaoService.addOrUpdate(custoInspecao);
    	insp.setQtdBlocos(inspecao.getQtdBlocos());
    	insp.setInspecaoAtividadeApurada(inspecao.getInspecaoAtividadeApurada());
    	this.inspecaoService.addOrUpdate(insp);
    	model.clear();
        return "redirect:/dtinspecao/listaInspecaoPorFuncionarioDesignado/" + SecurityContextHolder.getContext().getAuthentication().getName();
    }   

}
