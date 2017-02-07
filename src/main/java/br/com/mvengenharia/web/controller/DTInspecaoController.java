package br.com.mvengenharia.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.repositories.DTInspecaoRepository;
import br.com.mvengenharia.business.services.DTInspecaoService;

@Controller
public class DTInspecaoController {

	    @Autowired
	    private DTInspecaoService dtInspecaoService;   
	 

	    public DTInspecaoController() {
			super();
		}
	    

	    @RequestMapping(value = "dtinspecao/dtinspecao", method = RequestMethod.POST)
	    public ResponseEntity<DataTablesOutput<Inspecao>> getUsers(@Valid @RequestBody DataTablesInput input) {
	        ResponseEntity resp = new ResponseEntity(dtInspecaoService.findAll(input), HttpStatus.OK);
	        return resp;
	    }
	    
	    @RequestMapping(value = "dtinspecao/listainspecao", method = RequestMethod.GET)
		public ModelAndView showInspecao() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/listaInspecao");			
			return mav;
		}
	
}