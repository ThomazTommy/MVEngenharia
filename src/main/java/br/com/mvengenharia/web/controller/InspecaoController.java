package br.com.mvengenharia.web.controller;

import java.util.ArrayList;
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

import br.com.mvengenharia.business.entities.Atividade;
import br.com.mvengenharia.business.entities.Cliente;
import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.entities.Fase;
import br.com.mvengenharia.business.entities.Feriado;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.Log;
import br.com.mvengenharia.business.entities.Status;
import br.com.mvengenharia.business.entities.TipoLogradouro;
import br.com.mvengenharia.business.services.AtividadeService;
import br.com.mvengenharia.business.services.CidadeService;
import br.com.mvengenharia.business.services.ClienteService;
import br.com.mvengenharia.business.services.EstadoService;
import br.com.mvengenharia.business.services.FaseService;
import br.com.mvengenharia.business.services.FeriadoService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.HonorarioService;
import br.com.mvengenharia.business.services.InspecaoService;
import br.com.mvengenharia.business.services.LogService;
import br.com.mvengenharia.business.services.StatusService;
import br.com.mvengenharia.business.services.TipoLogradouroService;
import br.com.mvengenharia.business.services.VistoriaService;

@Controller
public class InspecaoController {

	@Autowired
	VistoriaService vistoriaService;

	@Autowired
	FeriadoService feriadoService;

	@Autowired
	FuncionarioService funcionarioService;

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
	private HonorarioService honorarioService;

	@Autowired
	private FaseService faseService;

	@Autowired
	private LogService logService;

	public InspecaoController() {
		super();
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

	@ModelAttribute("allFases")
	public Iterable<Fase> populateFases() {
		return this.faseService.findAll();
	}

	@ModelAttribute("allTipoLogradouros")
	public Iterable<TipoLogradouro> populateTipoLogradouros() {
		return this.tipoLogradouroService.findAll();
	}

	@RequestMapping("/novaInspecao")
	public ModelAndView novaInspecao() {
		Inspecao insp = new Inspecao();
		insp.setDtSolicitacaoInspecao(new Date());
		Fase fase = faseService.findOne(1L);
		Status status = statusService.findOne(2L);
		insp.setFase(fase);
		insp.setStatus(status);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/novaInspecao");
		mav.addObject("inspecao", insp);
		return mav;
	}

	@RequestMapping("/inspecao/sucesso/{idInspecao}")
	public ModelAndView showSucesso(@PathVariable Long idInspecao) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/sucesso");
		mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
		return mav;
	}

	@RequestMapping("/inspecao")
	public ModelAndView showInspecao() {
		Inspecao insp = new Inspecao();
		insp.setDtSolicitacaoInspecao(new Date());
		Fase fase = faseService.findOne(1L);
		Status status = statusService.findOne(2L);
		insp.setFase(fase);
		insp.setStatus(status);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/inspecao");
		mav.addObject("inspecao", insp);
		return mav;
	}

	@RequestMapping(value = "/inspecao", params = { "save" }, method = RequestMethod.POST)
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
			return "inspecao/novaInspecao";
		}
		Inspecao insp = this.inspecaoService.findOne(inspecao.getIdInspecao());
		if (insp != null) {
			inspecao.setAgendamentos(insp.getAgendamentos());
			inspecao.setAprovacaoSistemas(insp.getAprovacaoSistemas());
			inspecao.setCustoInspecao(insp.getCustoInspecao());
			inspecao.setDesignacoes(insp.getDesignacoes());
			inspecao.setDtAgendada(insp.getDtAgendada());
			inspecao.setDtVistoria(insp.getDtVistoria());
			inspecao.setFuncionarioVistoriador(insp.getFuncionarioVistoriador());
			inspecao.setInsercaoSistemas(insp.getInsercaoSistemas());
			inspecao.setInspecaoAtividadeApurada(insp.getInspecaoAtividadeApurada());
			inspecao.setObservacao(insp.getObservacao());
			inspecao.setRelatorios(insp.getRelatorios());
			inspecao.setRevisaos(insp.getRevisaos());
			inspecao.setVistoria(insp.getVistoria());
			inspecao.setFuncionarioCadastrador(insp.getFuncionarioCadastrador());
		}
		if (inspecao.getFuncionarioCadastrador() == null) {
			inspecao.setFuncionarioCadastrador(
					this.funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
		}
		inspecao.setDtLimite(obtemDataLimiteSemFeriados(inspecao.getDtSolicitacaoInspecao(),
				this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getPrazoCliente()));
		this.inspecaoService.addOrUpdate(inspecao);
		honorarioService.calculaHonorario(inspecao);
		model.clear();
		return "redirect:/inspecao/sucesso/" + inspecao.getIdInspecao();
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
		Inspecao insp = this.inspecaoService.findOne(inspecao.getIdInspecao());
		if (insp != null) {
			inspecao.setAgendamentos(insp.getAgendamentos());
			inspecao.setAprovacaoSistemas(insp.getAprovacaoSistemas());
			inspecao.setCustoInspecao(insp.getCustoInspecao());
			inspecao.setDesignacoes(insp.getDesignacoes());
			inspecao.setDtAgendada(insp.getDtAgendada());
			inspecao.setDtVistoria(insp.getDtVistoria());
			inspecao.setFuncionarioVistoriador(insp.getFuncionarioVistoriador());
			inspecao.setInsercaoSistemas(insp.getInsercaoSistemas());
			inspecao.setInspecaoAtividadeApurada(insp.getInspecaoAtividadeApurada());
			inspecao.setObservacao(insp.getObservacao());
			inspecao.setRelatorios(insp.getRelatorios());
			inspecao.setRevisaos(insp.getRevisaos());
			inspecao.setVistoria(insp.getVistoria());
			inspecao.setHonorario(insp.getHonorario());
		}
		inspecao.setDtLimite(obtemDataLimiteSemFeriados(inspecao.getDtSolicitacaoInspecao(),
				this.clienteService.findOne(inspecao.getCliente().getIdCliente()).getPrazoCliente()));
		this.inspecaoService.addOrUpdate(inspecao);
		honorarioService.calculaHonorario(inspecao);
		model.clear();
		return "redirect:/inspecao/sucesso/" + inspecao.getIdInspecao();
	}

	@RequestMapping(value = "/inspecao/remover/{id}")
	public String removeInspecao(@PathVariable Long id) {
		Inspecao insp = this.inspecaoService.findOne(id);
		Status status = new Status();
		status.setIdStatus(1L);
		insp.setStatus(status);
		this.inspecaoService.addOrUpdate(insp);
		this.logService.addOrUpdate(new Log("Removida inspecao de id nº " + id,
				SecurityContextHolder.getContext().getAuthentication().getName(), id, new Date()));
		return "redirect:/inspecao";
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

	@RequestMapping(value = "/inspecao/detalheInspecao/{idInspecao}")
	public ModelAndView detalheInspecao(@PathVariable Long idInspecao) {
		Inspecao insp = this.inspecaoService.findOne(idInspecao);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/detalheInspecao");
		mav.addObject("inspecao", insp);
		return mav;
	}

	private Date obtemDataLimiteSemFeriados(Date dataCadastro, int qtdDiasPrazo) {
		Date retorno = (Date) dataCadastro.clone();
		Calendar cal = Calendar.getInstance();
		Iterable<Feriado> feriados = feriadoService.findByAnoFeriado(cal.get(Calendar.YEAR));
		for (int i = 0; i <= qtdDiasPrazo; i++) {
			while (retorno.getDay() == 0 || retorno.getDay() == 6) {
				Calendar c = Calendar.getInstance();
				c.setTime(retorno);
				c.add(Calendar.DATE, 1);
				retorno = c.getTime();
			}
			for (Feriado feriado : feriados) {
				if (retorno.compareTo(feriado.getDataFeriado()) == 0) {
					Calendar c = Calendar.getInstance();
					c.setTime(retorno);
					c.add(Calendar.DATE, 1);
					retorno = c.getTime();
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(retorno);
			c.add(Calendar.DATE, 1);
			retorno = c.getTime();
		}
		return retorno;
	}

}
