package br.com.mvengenharia.exception;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/error");
		mav.addObject("status", "500");
		mav.addObject("mensagem", e.getMessage());
		mav.addObject("url", request.getRequestURI());		
		mav.addObject("stack", e.getStackTrace().toString());
		mav.addObject("datahora", LocalDateTime.now().toString());
		return mav;
	}

}