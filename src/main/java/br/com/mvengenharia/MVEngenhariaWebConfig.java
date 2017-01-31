package br.com.mvengenharia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.mvengenharia.web.conversion.CurrencyFormatter;
import br.com.mvengenharia.web.conversion.DateFormatter;

@Configuration
public class MVEngenhariaWebConfig extends WebMvcConfigurerAdapter{

	public MVEngenhariaWebConfig() {
		super();
	}

	@Bean
	public DateFormatter dateFormatter() {
		return new DateFormatter();
	}
	
	@Bean 
	public CurrencyFormatter currencyFormatter(){
		return new CurrencyFormatter();
	}

	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	  }

}
