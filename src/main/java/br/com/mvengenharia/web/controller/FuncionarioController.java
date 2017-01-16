package br.com.mvengenharia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mvengenharia.business.entities.Escritorio;
import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.entities.Permissoes;
import br.com.mvengenharia.business.services.EscritorioService;
import br.com.mvengenharia.business.services.FuncionarioService;
import br.com.mvengenharia.business.services.PermissoesService;

@Controller
public class FuncionarioController {


    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired 
    private PermissoesService permissoesService;
    
    @Autowired
    private EscritorioService escritorioService;
    
      
    public FuncionarioController() {
        super();
    }
     
    @ModelAttribute("allFuncionarios")
    public Iterable<Funcionario> populateFuncionarios() {
        return this.funcionarioService.findAll();
    }
    
    @ModelAttribute("allPermissoes")
    public Iterable<Permissoes> populatePermissoes() {
        return this.permissoesService.findAll();
    }
   
    @ModelAttribute("allEscritorios")
    public Iterable<Escritorio> populateEscritorios() {
        return this.escritorioService.findAll();
    }   
      
    @RequestMapping("/funcionario")
    public String showFuncionario(final Funcionario funcionario) {
          return "funcionario/funcionario";
    }      
    
    @RequestMapping(value="/funcionario", params={"save"})
    public String saveFuncionario(final Funcionario funcionario, final BindingResult bindingResult, final ModelMap model) {
        Funcionario func = this.funcionarioService.findOne(funcionario.getCpf());
        if (func!=null)
        {
        	bindingResult.rejectValue("cpf","cpf.invalido","Cpf já cadastrado");
        }
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            return "funcionario/funcionario";
        }
        this.funcionarioService.addOrUpdate(funcionario);
        model.clear();
        return "redirect:/funcionario";
    } 
    
    @RequestMapping(value="/funcionario/remover/{id}")
    public String saveFuncionario(@PathVariable String id) {
        this.funcionarioService.remove(id);
        return "redirect:/funcionario";
    }  

    @RequestMapping("/funcionario/editar/{cpf}")
    public ModelAndView editaCliente(@PathVariable String cpf) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("funcionario/editarFuncionario");
        Funcionario func = this.funcionarioService.findOne(cpf);
        mav.addObject("funcionario", func);
    	return mav;
    }   
    
    @RequestMapping(value="/funcionario/editar", params={"save"})
    public String saveEditedCliente(final Funcionario funcionario, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "funcionario/editarFuncionario";
        }
        this.funcionarioService.addOrUpdate(funcionario);
        model.clear();
        return "redirect:/funcionario";
    } 

}
