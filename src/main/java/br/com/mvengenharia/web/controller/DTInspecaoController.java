package br.com.mvengenharia.web.controller;

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
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAll(input), HttpStatus.OK);
		return resp;
	}

	@RequestMapping(value = "/dtinspecao/listaInspecaoPorStatus/{idStatus}", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Inspecao>> getInspecaoPorStatus(@Valid @RequestBody DataTablesInput input,
			@PathVariable Long idStatus) {
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAllByStatus(input, idStatus), HttpStatus.OK);
		return resp;
	}
	
	@RequestMapping(value = "/dtinspecao/listaInspecaoPorFase/{idFase}", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Inspecao>> getInspecaoPorFase(@Valid @RequestBody DataTablesInput input,
			@PathVariable Long idFase) {
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAllByFase(input, idFase), HttpStatus.OK);
		return resp;
	}
	
	@RequestMapping(value = "/dtinspecao/listaInspecaoPorFase/{idFase1}/{idFase2}", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Inspecao>> getInspecaoPorFase(@Valid @RequestBody DataTablesInput input,
			@PathVariable Long idFase1, @PathVariable Long idFase2) {
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAllByFase(input, idFase1, idFase2 ), HttpStatus.OK);
		return resp;
	}
	
	@RequestMapping(value = "/dtinspecao/listaInspecaoPorFase/{idFase1}/{idFase2}/{idFase3}", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Inspecao>> getInspecaoPorFase(@Valid @RequestBody DataTablesInput input,
			@PathVariable Long idFase1, @PathVariable Long idFase2, @PathVariable Long idFase3) {
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAllByFase(input, idFase1, idFase2, idFase3), HttpStatus.OK);
		return resp;
	}

	@RequestMapping(value = "/dtinspecao/listaInspecaoPorFase/{idFase1}/{idFase2}/{idFase3}/{idFase4}", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Inspecao>> getInspecaoPorFase(@Valid @RequestBody DataTablesInput input,
			@PathVariable Long idFase1, @PathVariable Long idFase2, @PathVariable Long idFase3, @PathVariable Long idFase4) {
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAllByFase(input, idFase1, idFase2, idFase3, idFase4), HttpStatus.OK);
		return resp;
	}
	
	@RequestMapping(value = "/dtinspecao/listaInspecaoPorCpf/{cpf}", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Inspecao>> getInspecaoPorCpf(@Valid @RequestBody DataTablesInput input,
			@PathVariable String cpf) {
		ResponseEntity<DataTablesOutput<Inspecao>> resp = new ResponseEntity<DataTablesOutput<Inspecao>>(
				dtInspecaoService.findAllByCpf(input, cpf), HttpStatus.OK);
		return resp;
	}

	@RequestMapping(value = "/dtinspecao/listainspecao", method = RequestMethod.GET)
	public ModelAndView showInspecao() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/listaInspecao");
		return mav;
	}

	

	

	


	

}
