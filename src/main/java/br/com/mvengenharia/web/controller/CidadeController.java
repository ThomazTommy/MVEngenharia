package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Cidade;
import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.services.CidadeService;
import br.com.mvengenharia.business.services.EstadoService;

@Controller
public class CidadeController {


    @Autowired
    private CidadeService cidadeService;
    
    @Autowired EstadoService estadoService;
      
    
    public CidadeController() {
        super();
    }
     
    @RequestMapping(value = "/inspecao/cidadesPorEstado/{idEstado}")
	public ModelAndView getCidadesPorEstado(@PathVariable Long idEstado) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inspecao/listaCidadesPorEstado");
		mav.addObject("listaCidades", this.cidadeService.findByIdEstado(idEstado));
		return mav;
	}   
    
    @RequestMapping("/cidade")
    public String showCidade(final Cidade cidade, ModelMap model) {
    	model.addAttribute("allEstados", this.estadoService.findAll());
    	model.addAttribute("allCidades", this.cidadeService.findAll());
          return "cidade/cidade";
    }      
    
    @RequestMapping(value="/cidade", params={"save"})
    public String saveCidade(final Cidade cidade, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("allEstados", this.estadoService.findAll());
        	model.addAttribute("allCidades", this.cidadeService.findAll());
            return "cidade/cidade";
        }
        this.cidadeService.addOrUpdate(cidade);
        model.clear();
        return "redirect:/cidade";
    } 
    
    @RequestMapping(value="/cidade/remover/{id}")
    public String saveCidade(@PathVariable Long id) {
        this.cidadeService.remove(id);
        return "redirect:/cidade";
    }  


}
