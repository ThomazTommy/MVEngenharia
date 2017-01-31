package br.com.mvengenharia.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Cliente;
import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.Status;
import br.com.mvengenharia.business.entities.TipoLogradouro;
import br.com.mvengenharia.business.services.CidadeService;
import br.com.mvengenharia.business.services.ClienteService;
import br.com.mvengenharia.business.services.EstadoService;
import br.com.mvengenharia.business.services.InspecaoService;
import br.com.mvengenharia.business.services.StatusService;
import br.com.mvengenharia.business.services.TipoLogradouroService;

@Controller
public class InspecaoController {

	@Autowired
	private InspecaoService inspecaoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private TipoLogradouroService tipoLogradouroService;

	public InspecaoController() {
		super();
	}

	@ModelAttribute("allInspecaos")
	public Iterable<Inspecao> populateInspecaos() {
		return this.inspecaoService.findAll();
	}

	@ModelAttribute("allEstados")
	public Iterable<Estado> populateEstados() {
		return this.estadoService.findAll();
	}

	@ModelAttribute("allClientes")
	public Iterable<Cliente> populateClientes() {
		return this.clienteService.findAll();
	}

	@ModelAttribute("allStatus")
	public Iterable<Status> populateStatus() {
		return this.statusService.findAll();
	}

	@ModelAttribute("allTipoLogradouros")
	public Iterable<TipoLogradouro> populateTipoLogradouros() {
		return this.tipoLogradouroService.findAll();
	}

	@RequestMapping("/inspecao")
	public ModelAndView showInspecao() {
		Inspecao insp = new Inspecao();
		insp.setDtSolicitacaoInspecao(new Date());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/inspecao");
		mav.addObject("inspecao", insp);
		return mav;
	}

	@RequestMapping(value = "/inspecao", params = { "save" })
	public String saveInspecao(@Valid Inspecao inspecao, final BindingResult bindingResult, final ModelMap model) {

		if (bindingResult.hasErrors()) {
			if (inspecao.getCliente() != null) {
				model.addAttribute("listaRamos",
						this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getRamos());
				model.addAttribute("listaTipoInspecao",
						this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getTipoInspecaos());
			}
			if (inspecao.getEndereco().getEstado() != null) {
				model.addAttribute("listaCidades",
						this.cidadeService.findByIdEstado(inspecao.getEndereco().getEstado().getIdEstado()));
			}
			System.out.println(bindingResult.toString());
			return "inspecao/inspecao";
		}
		this.inspecaoService.addOrUpdate(inspecao);
		model.clear();
		return "redirect:/inspecao";
	}

	@RequestMapping(value = "/inspecao/remover/{id}")
	public String saveInspecao(@PathVariable Long id) {
		this.inspecaoService.remove(id);
		return "redirect:/inspecao";
	}

	@RequestMapping(value = "/inspecao/cidadesPorEstado/{idEstado}")
	public ModelAndView getCidadesPorEstado(@PathVariable Long idEstado) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/listaCidadesPorEstado");
		mav.addObject("listaCidades", this.cidadeService.findByIdEstado(idEstado));
		return mav;
	}

	@RequestMapping(value = "/inspecao/tipoInspecaoPorCliente/{idCliente}")
	public ModelAndView getTipoInspecaoPorCliente(@PathVariable Long idCliente) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/listaTipoInspecaoPorCliente");
		mav.addObject("listaTipoInspecao", this.clienteService.findOne(idCliente).getTipoInspecaos());
		return mav;
	}

	@RequestMapping(value = "/inspecao/ramoPorCliente/{idCliente}")
	public ModelAndView getRamoPorCliente(@PathVariable Long idCliente) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/listaRamoPorCliente");
		mav.addObject("listaRamos", this.clienteService.findOne(idCliente).getRamos());
		return mav;
	}

	@RequestMapping("/inspecao/editar/{idInspecao}")
	public ModelAndView editarInspecao(@PathVariable Long idInspecao) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/editarInspecao");
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		mav.addObject("inspecao", insp);
		mav.addObject("listaRamos", this.clienteService.findOne(insp.getCliente().getIdCliente()).getRamos());
		mav.addObject("listaTipoInspecao",
				this.clienteService.findOne(insp.getCliente().getIdCliente()).getTipoInspecaos());
		mav.addObject("listaCidades", this.cidadeService.findByIdEstado(insp.getEndereco().getEstado().getIdEstado()));
		return mav;
	}

	@RequestMapping(value = "/inspecao/editar", params = { "save" })
	public String saveEditedInspecao(@Valid Inspecao inspecao, final BindingResult bindingResult,
			final ModelMap model) {

		if (bindingResult.hasErrors()) {
			if (inspecao.getCliente() != null) {
				model.addAttribute("listaRamos",
						this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getRamos());
				model.addAttribute("listaTipoInspecao",
						this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getTipoInspecaos());
			}
			if (inspecao.getEndereco().getEstado() != null) {
				model.addAttribute("listaCidades",
						this.cidadeService.findByIdEstado(inspecao.getEndereco().getEstado().getIdEstado()));
			}
			System.out.println(bindingResult.toString());
			return "inspecao/editarInspecao";
		}
		this.inspecaoService.addOrUpdate(inspecao);
		model.clear();
		return "redirect:/inspecao";
	}

}