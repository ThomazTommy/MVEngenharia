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
import br.com.mvengenharia.business.entities.Log;
import br.com.mvengenharia.business.services.LogService;

@Controller
public class LogController {

	@Autowired
	private LogService logService;

	public LogController() {
		super();
	}

	@RequestMapping(value = "/log/getAll", method = RequestMethod.POST)
	public ResponseEntity<DataTablesOutput<Log>> getInspecoes(@Valid @RequestBody DataTablesInput input) {
		ResponseEntity<DataTablesOutput<Log>> resp = new ResponseEntity<DataTablesOutput<Log>>(
				this.logService.findAll(input), HttpStatus.OK);
		return resp;
	}

	@RequestMapping("/log")
	public String showLog(final Log log) {
		return "log/log";
	}

}
