package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.TipoLogradouro;
import br.com.mvengenharia.business.services.TipoLogradouroService;

@Controller
public class TipoLogradouroController {


    @Autowired
    private TipoLogradouroService tipoLogradouroService;
    
      
    public TipoLogradouroController() {
        super();
    }
     
    @ModelAttribute("allTipoLogradouros")
    public Iterable<TipoLogradouro> populateTipoLogradouros() {
        return this.tipoLogradouroService.findAll();
    }
   
    
    @RequestMapping("/tipoLogradouro")
    public String showTipoLogradouro(ModelMap model) {
    	TipoLogradouro tl = new TipoLogradouro();
    	tl.setSituacao(true);
    	model.addAttribute("tipoLogradouro", tl);
          return "tipoLogradouro/tipoLogradouro";
    }      
    
    @RequestMapping(value="/tipoLogradouro", params={"save"})
    public String saveTipoLogradouro(final TipoLogradouro tipoLogradouro, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "tipoLogradouro/tipoLogradouro";
        }
        this.tipoLogradouroService.addOrUpdate(tipoLogradouro);
        model.clear();
        return "redirect:/tipoLogradouro";
    } 
    
    @RequestMapping(value="/tipoLogradouro/editar/{id}")
    public String editarTipoLogradouro(@PathVariable Long id, ModelMap model) {
        TipoLogradouro tl = this.tipoLogradouroService.findOne(id);
        model.addAttribute("tipoLogradouro", tl);
        return "tipoLogradouro/tipoLogradouro";
    }  
    
    @RequestMapping(value="/tipoLogradouro/remover/{id}")
    public String saveTipoLogradouro(@PathVariable Long id) {
        TipoLogradouro tl = this.tipoLogradouroService.findOne(id);
        tl.setSituacao(false);
        this.tipoLogradouroService.addOrUpdate(tl);
        return "redirect:/tipoLogradouro";
    }  


}
