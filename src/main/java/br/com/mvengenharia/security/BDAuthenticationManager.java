package br.com.mvengenharia.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.mvengenharia.business.entities.Funcionario;
import br.com.mvengenharia.business.entities.Permissoes;
import br.com.mvengenharia.business.services.FuncionarioService;


@Component
public class BDAuthenticationManager implements AuthenticationProvider{

	@Autowired
	FuncionarioService funcionarioService;
		
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
        String password = auth.getCredentials().toString();
        
        Funcionario func = funcionarioService.findByCpf(username);
        
        if (func == null) {
            throw new BadCredentialsException("1000");
        }
        
        if (!func.getSenha().contentEquals(password))
        {
            throw new BadCredentialsException("1000");
        }
                   
		List<Permissoes> listaPermissoes = func.getPermissoes();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        for(Permissoes perm : listaPermissoes)
        {
        	grantedAuths.add(new SimpleGrantedAuthority(perm.getDescPermissoes()));
        }
        return new UsernamePasswordAuthenticationToken(func.getCpf(), password, grantedAuths);
				
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
