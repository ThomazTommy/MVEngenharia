package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Honorario;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.services.HonorarioService;
import br.com.mvengenharia.business.services.InspecaoService;

@Controller
public class HonorarioController {

	@Autowired
	private HonorarioService honorarioService;

	@Autowired
	private InspecaoService inspecaoService;

	public HonorarioController() {
		super();
	}

	@RequestMapping(value = "/honorario/{idInspecao}", method = RequestMethod.GET)
	public ModelAndView showHonorario(final Honorario honorario, @PathVariable Long idInspecao) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("honorario/honorario");
		mav.addObject("inspecao", this.inspecaoService.findOne(idInspecao));
		return mav;
	}

	@RequestMapping(value = "/honorario/editar/{idInspecao}", method = RequestMethod.GET)
	public ModelAndView editaHonorario(Honorario honorario, @PathVariable Long idInspecao) {
		Inspecao inspecao = this.inspecaoService.findOne(idInspecao);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("honorario/editarHonorario");
		mav.addObject("inspecao", inspecao);
		if(inspecao.getHonorario() == null)
		{
			honorario = new Honorario();
		}
		else
		{
			honorario = inspecao.getHonorario();
		}
		mav.addObject("honorario", honorario);
		return mav;
	}

	@RequestMapping(value = "/honorario/{idInspecao}", params = { "save" },  method = RequestMethod.POST)
	public String saveHonorario(final Honorario honorario, final BindingResult bindingResult, final ModelMap model, @PathVariable Long idInspecao) {
		if (bindingResult.hasErrors()) {
			return "honorario/editarHonorario";
		}
		honorario.setInspecao(this.inspecaoService.findOne(idInspecao));
		this.honorarioService.addOrUpdate(honorario);
		model.clear();
		return "redirect:/honorario/" + idInspecao;
	}

	@RequestMapping(value = "/honorario/remover/{id}")
	public String saveHonorario(@PathVariable Long id) {
		this.honorarioService.remove(id);
		return "redirect:/honorario";
	}

}
