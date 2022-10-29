package com.gft.desafio.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafio.entities.Evento;
import com.gft.desafio.services.EventoService;

@Controller
@RequestMapping("evento")
public class EventoController {
	
	@Autowired
	private EventoService eventoService;
	
	@RequestMapping(path = "novo")
	public ModelAndView editarEvento(@RequestParam(required = false) Long id) {
		
		ModelAndView modelo = new ModelAndView("evento/formulario.html");
		Evento evento;
		
		if(id==null) {
			evento = new Evento();
		}else {
			try {
			evento = eventoService.obterEventos(id);
			}catch(Exception e){
				evento = new Evento();
				modelo.addObject("mensagem",e.getMessage());
			}
		}
		
		modelo.addObject("evento", evento);
		
		return modelo;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult) {
		
		ModelAndView modelo = new ModelAndView("evento/formulario.html");
		
		boolean novo = true;
		
		if(evento.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()){
			modelo.addObject("evento", evento);
			return modelo;
			
		}
		
		eventoService.salvarEvento(evento);
		
		if(novo) {
			modelo.addObject("evento", new Evento());
		}else {
			modelo.addObject("evento", evento);
		}
		
		modelo.addObject("mensagem", "Evento salvo com sucesso.");
		
		return modelo;
	}
	
	@RequestMapping
	public ModelAndView listarEventos() {
		
		ModelAndView modelo = new ModelAndView("evento/listar.html");
		
		modelo.addObject("lista", eventoService.listarTodosEventos());
		
		return modelo;
		
	}
	
	@RequestMapping("/detalhe")
	public ModelAndView detalheEvento(Long id) {
		
		ModelAndView modelo = new ModelAndView("evento/detalhe.html");
		modelo.addObject("lista", eventoService.listarAtividadesEventos(id));
		
		return modelo;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAtributes) {
		
		ModelAndView modelo = new ModelAndView("redirect:/evento");

		try {
			eventoService.excluirEvento(id);
			redirectAtributes.addFlashAttribute("mensagem", "Evento excluido com exito.");
		}catch(Exception e){
			redirectAtributes.addFlashAttribute("mensagem", "Erro ao excluir evento." + e.getMessage());
		}

		return modelo;
	}
}