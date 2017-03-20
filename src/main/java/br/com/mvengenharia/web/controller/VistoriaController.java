package br.com.mvengenharia.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Vistoria;
import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.VistoriaService;
import br.com.mvengenharia.business.services.AtividadeService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class VistoriaController {


    @Autowired
    private VistoriaService vistoriaService;
    
    @Autowired
    private InspecaoService inspecaoService;
    
	@Autowired
	private AtividadeService atividadeService;

    @Autowired
    private FuncionarioService funcionarioService;
        
    public VistoriaController() {
        super();
    }
    
	@ModelAttribute("allAtividades")
	public Iterable<Atividade> populateAtividades() {
		return this.atividadeService.findAll();
	}
	@RequestMapping(value = "/vistoria/inspecaoInicioFimCustos/{idInspecao}")
	public ModelAndView inspecaoInicioFimCustos(@PathVariable Long idInspecao) {
		Inspecao insp =  this.inspecaoService.findOne(idInspecao);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);

		return mav;
	}

	@RequestMapping(value = "/vistoria/informaInicioInspecao/{idInspecao}")
	public ModelAndView informaIncioInspecao(@PathVariable Long idInspecao) {
		Vistoria vistoria = new Vistoria();
		vistoria.setDataHoraChegadaLocal(new Date());
		vistoria.setDtInicioInspecao(new Date());
		vistoria.setFuncionario(
				this.funcionarioService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName()));
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		vistoria.setInspecao(insp);
		List<Vistoria> listaVistorias = new ArrayList<Vistoria>();
		listaVistorias.add(vistoria);
		insp.setVistorias(listaVistorias);
		this.inspecaoService.addOrUpdate(insp);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);
		return mav;
	}

	@RequestMapping(value = "/vistoria/informaFimInspecao/{idInspecao}")
	public ModelAndView informaFimInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		List<Vistoria> listaVistorias = insp.getVistorias();
		Date agora = new Date();
		if (!listaVistorias.isEmpty()) {
			Vistoria vistoria = listaVistorias.get(0);
			vistoria.setDtFimInspecao(agora);
			vistoria.setInspecao(insp);
			this.vistoriaService.addOrUpdate(vistoria);
			insp.setFuncionarioVistoriador(vistoria.getFuncionario());
			insp.setDtVistoria(vistoria.getDtFimInspecao());
			this.inspecaoService.addOrUpdate(insp);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);
		return mav;
	}
	
	
	@RequestMapping(value = "/vistoria/listaInspecaoPorFuncionarioDesignado")
	public ModelAndView listaInspecaoPorFuncionarioDesignado() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/listaInspecaoPorFuncionarioDesignado");
		return mav;
	}
}
