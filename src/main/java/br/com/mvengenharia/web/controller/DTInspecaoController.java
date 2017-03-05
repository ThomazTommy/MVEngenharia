package br.com.mvengenharia.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.DTInspecaoService;

@Controller
public class DTInspecaoController {

	    @Autowired
	    private DTInspecaoService dtInspecaoService;   
	 

	    public DTInspecaoController() {
			super();
		}
	    
	    @RequestMapping(value = "/dtinspecao/dtinspecao", method = RequestMethod.POST)
	    public ResponseEntity<DataTablesOutput<Inspecao>> getInspecoes(@Valid @RequestBody DataTablesInput input) {
	        ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(dtInspecaoService.findAll(input), HttpStatus.OK);
	        return resp;
	    }
	    
	    
	    @RequestMapping(value = "/dtinspecao/listainspecao", method = RequestMethod.GET)
		public ModelAndView showInspecao() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecao");			
			return mav;
		}
	    
	    @RequestMapping(value = "/dtinspecao/listaInspecaoPorFuncionarioDesignado/{cpf}", method = RequestMethod.GET)
		public ModelAndView showInspecaoPorFuncionario(@PathVariable String cpf) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecaoPorFuncionarioDesignado");
			List<Inspecao> listaInspecoes = this.dtInspecaoService.findByFuncionarioDesignado(cpf);
			mav.addObject("listaInspecoes", listaInspecoes);
			return mav;
		}
	    
	    
	    @RequestMapping(value = "/dtinspecao/listaInspecaoParaRelatar", method = RequestMethod.GET)
		public ModelAndView showInspecaoParaRelatar() {
	    	Long status = Long.valueOf("1");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecaoParaRelatar");
			List<Inspecao> listaInspecoes = this.dtInspecaoService.findByStatus(status);
			mav.addObject("listaInspecoes", listaInspecoes);
			return mav;
		}
	    
	    @RequestMapping(value = "/dtinspecao/listaInspecaoParaRevisar", method = RequestMethod.GET)
		public ModelAndView showInspecaoParaRevisar() {
	    	Long status = Long.valueOf("1");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecaoParaRevisar");
			List<Inspecao> listaInspecoes = this.dtInspecaoService.findByStatus(status);
			mav.addObject("listaInspecoes", listaInspecoes);
			return mav;
		}
	
	    @RequestMapping(value = "/dtinspecao/listaInspecaoParaInsercaoSistema", method = RequestMethod.GET)
		public ModelAndView showInspecaoParaInsercaoSistema() {
	    	Long status = Long.valueOf("1");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecaoParaInsercaoSistema");
			List<Inspecao> listaInspecoes = this.dtInspecaoService.findByStatus(status);
			mav.addObject("listaInspecoes", listaInspecoes);
			return mav;
		}
	    
	    @RequestMapping(value = "/dtinspecao/listaInspecaoParaAprovacaoSistema", method = RequestMethod.GET)
		public ModelAndView showInspecaoParaAprovacaoSistema() {
	    	Long status = Long.valueOf("1");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecaoParaAprovacaoSistema");
			List<Inspecao> listaInspecoes = this.dtInspecaoService.findByStatus(status);
			mav.addObject("listaInspecoes", listaInspecoes);
			return mav;
		}

	    
}
