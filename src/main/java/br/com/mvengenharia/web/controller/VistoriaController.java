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
import br.com.mvengenharia.business.entities.Fase;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.Status;
import br.com.mvengenharia.business.services.AtividadeService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class VistoriaController {

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
		Inspecao insp = this.inspecaoService.findOne(idInspecao);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);

		return mav;
	}

	@RequestMapping(value = "/vistoria/informaInicioInspecao/{idInspecao}")
	public ModelAndView informaIncioInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		Vistoria vistoria;
		if (insp.getVistoria() == null) {
			vistoria = new Vistoria();
		} else {
			vistoria = insp.getVistoria();
		}
		vistoria.setDataHoraChegadaLocal(new Date());
		vistoria.setDtInicioInspecao(new Date());
		vistoria.setDtFimInspecao(null);
		vistoria.setFuncionario(
				this.funcionarioService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName()));
		vistoria.setInspecao(insp);
		insp.setVistoria(vistoria);
		this.inspecaoService.addOrUpdate(insp);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);
		return mav;
	}

	@RequestMapping(value = "/vistoria/informaFimInspecao/{idInspecao}")
	public ModelAndView informaFimInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		Date agora = new Date();
		Vistoria vistoria = insp.getVistoria();
		vistoria.setDtFimInspecao(agora);
		vistoria.setInspecao(insp);
		insp.setFuncionarioVistoriador(vistoria.getFuncionario());
		insp.setDtVistoria(vistoria.getDtFimInspecao());
		Status status = new Status();
		status.setIdStatus(2);
		Fase fase = new Fase();
		fase.setIdFase(5);
		insp.setStatus(status);
		insp.setFase(fase);
		List<Atividade> atividades = new ArrayList<Atividade>();
		for (Atividade atv : insp.getInspecaoAtividadeInformada()) {
			atividades.add(atv);
		}
		insp.setInspecaoAtividadeApurada(atividades);
		insp.setVistoria(vistoria);
		this.inspecaoService.addOrUpdate(insp);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vistoria/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);
		return mav;
	}

	@RequestMapping(value = "/vistoria/frustarInspecao/{idInspecao}")
	public ModelAndView frustarInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		Date agora = new Date();
		Vistoria vistoria = insp.getVistoria();
		vistoria.setDtFimInspecao(agora);
		vistoria.setInspecao(insp);
		insp.setFuncionarioVistoriador(vistoria.getFuncionario());
		insp.setDtVistoria(vistoria.getDtFimInspecao());
		Status status = new Status();
		status.setIdStatus(4);
		Fase fase = new Fase();
		fase.setIdFase(1);
		insp.setFase(fase);
		insp.setStatus(status);
		insp.setVistoria(vistoria);
		this.inspecaoService.addOrUpdate(insp);
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
