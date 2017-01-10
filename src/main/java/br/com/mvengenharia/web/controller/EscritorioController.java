package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Cidade;
import br.com.mvengenharia.business.entities.Escritorio;
import br.com.mvengenharia.business.entities.Estado;
import br.com.mvengenharia.business.entities.TipoLogradouro;
import br.com.mvengenharia.business.services.CidadeService;
import br.com.mvengenharia.business.services.EscritorioService;
import br.com.mvengenharia.business.services.EstadoService;
import br.com.mvengenharia.business.services.TipoLogradouroService;

@Controller
public class EscritorioController {


    @Autowired
    private EscritorioService escritorioService;
    
    @Autowired
    private TipoLogradouroService tipoLogradouroService;
    
    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private CidadeService cidadeService;
   
    
      
    public EscritorioController() {
        super();
    }
     
    @ModelAttribute("allEscritorios")
    public Iterable<Escritorio> populateEscritorios() {
        return this.escritorioService.findAll();
    }
    
    @ModelAttribute("allEstados")
    public Iterable<Estado> populateEstados() {
        return this.estadoService.findAll();
    }
    
    @ModelAttribute("allCidades")
    public Iterable<Cidade> populateCidades() {
        return this.cidadeService.findAll();
    }
    
    @ModelAttribute("allTipoLogradouros")
    public Iterable<TipoLogradouro> populateTipoLogradouros() {
        return this.tipoLogradouroService.findAll();
    }
   
   
    
    @RequestMapping("/escritorio")
    public String showEscritorio(final Escritorio escritorio) {
          return "escritorio/escritorio";
    }      
    
    @RequestMapping(value="/escritorio", params={"save"})
    public String saveEscritorio(final Escritorio escritorio, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "escritorio";
        }
        this.escritorioService.addOrUpdate(escritorio);
        model.clear();
        return "redirect:/escritorio";
    } 
    
    @RequestMapping(value="/escritorio/remover/{id}")
    public String saveEscritorio(@PathVariable Long id) {
        this.escritorioService.remove(id);
        return "redirect:/escritorio";
    }  


}
