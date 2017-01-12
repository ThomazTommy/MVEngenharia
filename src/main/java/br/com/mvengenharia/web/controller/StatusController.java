package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvengenharia.business.entities.Status;
import br.com.mvengenharia.business.services.StatusService;

@Controller
public class StatusController {


    @Autowired
    private StatusService statusService;
    
      
    public StatusController() {
        super();
    }
     
    @ModelAttribute("allStatuss")
    public Iterable<Status> populateStatuss() {
        return this.statusService.findAll();
    }
   
    
    @RequestMapping("/status")
    public String showStatus(final Status status) {
          return "status/status";
    }      
    
    @RequestMapping(value="/status", params={"save"})
    public String saveStatus(final Status status, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "status/status";
        }
        this.statusService.addOrUpdate(status);
        model.clear();
        return "redirect:/status";
    } 
    
    @RequestMapping(value="/status/remover/{id}")
    public String saveStatus(@PathVariable Long id) {
        this.statusService.remove(id);
        return "redirect:/status";
    }  


}
