package com.gft.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {

	@RequestMapping("tabela")
	public ModelAndView tabela() {
		
		ModelAndView mv = new ModelAndView("tabela.html");
		
		return mv;
	}

	@RequestMapping("index")
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("index.html");
		
		return mv;
	}

}
