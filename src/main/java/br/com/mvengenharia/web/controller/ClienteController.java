package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Cliente;
import br.com.mvengenharia.business.entities.Ramo;
import br.com.mvengenharia.business.entities.TipoInspecao;
import br.com.mvengenharia.business.services.ClienteService;
import br.com.mvengenharia.business.services.RamoService;
import br.com.mvengenharia.business.services.TipoInspecaoService;

@Controller
public class ClienteController {


    @Autowired
    private ClienteService clienteService;
    
    @Autowired 
    private TipoInspecaoService tipoInspecaoService;
    
    @Autowired
    private RamoService ramoService;
    
      
    public ClienteController() {
        super();
    }
     
    @ModelAttribute("allClientes")
    public Iterable<Cliente> populateClientes() {
        return this.clienteService.findAll();
    }
   
    @ModelAttribute("allRamos")
    public Iterable<Ramo> populateRamos() {
        return this.ramoService.findAll();
    }
   
    @ModelAttribute("allTipoInspecaos")
    public Iterable<TipoInspecao> populateTipoInspecaos() {
        return this.tipoInspecaoService.findAll();
    }
   
    
    @RequestMapping("/cliente")
    public String showCliente(final Cliente cliente) {
          return "cliente/cliente";
    }    
    
    @RequestMapping("/cliente/editar/{id}")
    public ModelAndView editaCliente(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cliente/editarCliente");
        Cliente cli = this.clienteService.findOne(id);
        mav.addObject("cliente", cli);
    	return mav;
    }   
    
    @RequestMapping(value="/cliente/editar", params={"save"})
    public String saveEditedCliente(final Cliente cliente, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "cliente/editarCliente";
        }
        this.clienteService.addOrUpdate(cliente);
        model.clear();
        return "redirect:/cliente";
    } 
    
    @RequestMapping(value="/cliente", params={"save"})
    public String saveCliente(final Cliente cliente, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "cliente/cliente";
        }
        this.clienteService.addOrUpdate(cliente);
        model.clear();
        return "redirect:/cliente";
    } 
    
    @RequestMapping(value="/cliente/remover/{id}")
    public String saveCliente(@PathVariable Long id) {
        this.clienteService.remove(id);
        return "redirect:/cliente";
    }  


}
