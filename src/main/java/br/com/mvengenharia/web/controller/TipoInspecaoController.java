package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.TipoInspecao;
import br.com.mvengenharia.business.services.TipoInspecaoService;

@Controller
public class TipoInspecaoController {


    @Autowired
    private TipoInspecaoService tipoInspecaoService;
    
      
    public TipoInspecaoController() {
        super();
    }
     
    @ModelAttribute("allTipoInspecaos")
    public Iterable<TipoInspecao> populateTipoInspecaos() {
        return this.tipoInspecaoService.findAll();
    }
   
    
    @RequestMapping("/tipoInspecao")
    public String showTipoInspecao(final TipoInspecao tipoInspecao) {
          return "tipoInspecao/tipoInspecao";
    }      
    
    @RequestMapping(value="/tipoInspecao", params={"save"})
    public String saveTipoInspecao(final TipoInspecao tipoInspecao, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "tipoInspecao/tipoInspecao";
        }
        this.tipoInspecaoService.addOrUpdate(tipoInspecao);
        model.clear();
        return "redirect:/tipoInspecao";
    } 
    
    @RequestMapping(value="/tipoInspecao/remover/{id}")
    public String saveTipoInspecao(@PathVariable Long id) {
        this.tipoInspecaoService.remove(id);
        return "redirect:/tipoInspecao";
    }  


}
