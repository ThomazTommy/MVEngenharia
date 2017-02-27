package br.com.mvengenharia.web.controller;

import java.util.Calendar;
import java.util.Date;

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

import br.com.mvengenharia.business.entities.Agendamento;
import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.Cliente;
import br.com.mvengenharia.business.entities.CustoInspecao;
import br.com.mvengenharia.business.entities.Designacao;
import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.Status;
import br.com.mvengenharia.business.entities.TipoLogradouro;
import br.com.mvengenharia.business.services.AtividadeService;
import br.com.mvengenharia.business.services.CidadeService;
import br.com.mvengenharia.business.services.ClienteService;
import br.com.mvengenharia.business.services.EstadoService;
import br.com.mvengenharia.business.services.FuncionarioService;
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
	private AtividadeService atividadeService;

	@Autowired
	private TipoLogradouroService tipoLogradouroService;

	@Autowired
	private FuncionarioService funcionarioService;

	public InspecaoController() {
		super();
	}

	@ModelAttribute("allInspecaos")
	public Iterable<Inspecao> populateInspecaos() {
		return this.inspecaoService.findAll();
	}
	
	@ModelAttribute("allAtividades")
	public Iterable<Atividade> populateAtividades() {
		return this.atividadeService.findAll();
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
		Calendar c = Calendar.getInstance();
		c.setTime(inspecao.getDtSolicitacaoInspecao());
		c.add(Calendar.DATE, + this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getPrazoCliente());
		inspecao.setDtLimite(c.getTime());
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
	
	@RequestMapping(value = "/inspecao/detalheinspecao/{idInspecao}")
	public ModelAndView detalheInspecao(@PathVariable Long idInspecao) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/detalheInspecao");
		mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
		return mav;
	}
	
	@RequestMapping(value = "/inspecao/inspecaoInicioFimCustos/{idInspecao}")
	public ModelAndView inspecaoInicioFimCustos(@PathVariable Long idInspecao) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/inspecaoInicioFimCustos");
		mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
		
		return mav;
	}
	
	@RequestMapping(value = "/inspecao/informaInicioInspecao/{idInspecao}")
	public ModelAndView informaIncioInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		insp.setDtInicioInspecao(new Date());
		this.inspecaoService.addOrUpdate(insp);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/inspecaoInicioFimCustos");
		mav.addObject("inspecao", insp);
	
		return mav;
	}
	
	@RequestMapping(value = "/inspecao/informaFimInspecao/{idInspecao}")
	public ModelAndView informaFimInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		Date agora = new Date();
		if(insp.getDtInicioInspecao()==null||insp.getDtInicioInspecao().after(agora))
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("inspecao/inspecaoInicioFimCustos");
			mav.addObject("inspecao", insp);
			mav.addObject("Erro", "Data inicio inspeção não informada ou maior que a data fim inspeção");
			return mav;
		}		
		insp.setDtFimInspecao(new Date());
		this.inspecaoService.addOrUpdate(insp);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/dtinspecao/listaInspecaoPorFuncionarioDesignado/" + SecurityContextHolder.getContext().getAuthentication().getName());
		mav.addObject("inspecao", insp);
		
		return mav;
	}
	
	
/*	@RequestMapping(value = "/inspecao/inspecaoRelatorio/{idInspecao}")
	public ModelAndView inspecaoRelatorio(@PathVariable Long idInspecao) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/inspecaoRelatorio");
		mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
		return mav;
	}
	*/
	
	

}
