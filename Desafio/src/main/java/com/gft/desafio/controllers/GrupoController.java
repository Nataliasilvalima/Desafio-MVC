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

import com.gft.desafio.entities.Grupo;
import com.gft.desafio.services.EventoService;
import com.gft.desafio.services.GrupoService;

@Controller
@RequestMapping("grupo")
public class GrupoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private EventoService eventoService;
	
	@RequestMapping(path = "novo")
	public ModelAndView editarGrupo(@RequestParam(required = false) Long id) {
		
		ModelAndView modelo = new ModelAndView("grupo/formulario.html");
		Grupo grupo;
		
		if(id==null) {
			grupo = new Grupo();
		}else {
			try {
			grupo = grupoService.obterGrupo(id);
			}catch(Exception e){
				grupo = new Grupo();
				modelo.addObject("mensagem",e.getMessage());
			}
		}
		
		modelo.addObject("grupo", grupo);
		modelo.addObject("listaEventos", eventoService.listarTodosEventos());
		
		return modelo;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarGrupo(@Valid Grupo grupo, BindingResult bindingResult) {
		
		ModelAndView modelo = new ModelAndView("grupo/formulario.html");
		
		boolean novo = true;
		
		if(grupo.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()){
			modelo.addObject("grupo", grupo);
			return modelo;
			
		}
		
		grupoService.salvarGrupo(grupo);
		
		if(novo) {
			modelo.addObject("grupo", new Grupo());
		}else {
			modelo.addObject("grupo", grupo);
		}
		
		modelo.addObject("mensagem", "Grupo salvo com sucesso.");
		modelo.addObject("listaEventos", eventoService.listarTodosEventos());
		
		return modelo;
	}
	
	@RequestMapping
	public ModelAndView listarGrupos(String nome) {
		
		ModelAndView modelo = new ModelAndView("grupo/listar.html");
		
		modelo.addObject("lista", grupoService.listarGrupos(nome));
		modelo.addObject("nome", nome);
		
		return modelo;
		
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirParticipante(@RequestParam Long id, RedirectAttributes redirectAtributes) {
		
		ModelAndView modelo = new ModelAndView("redirect:/grupo");

		try {
			grupoService.excluirGrupo(id);
			redirectAtributes.addFlashAttribute("mensagem", "Grupo excluido com exito.");
		}catch(Exception e){
			redirectAtributes.addFlashAttribute("mensagem", "Erro ao excluir grupo." + e.getMessage());
		}

		return modelo;
	}
}