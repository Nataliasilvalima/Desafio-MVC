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

import com.gft.desafio.entities.Atividade;
import com.gft.desafio.services.AtividadeService;
import com.gft.desafio.services.EventoService;

@Controller
@RequestMapping("atividade")
public class AtividadeController {
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private AtividadeService atividadeService;
	
	@RequestMapping(path = "novo")
	public ModelAndView editarAtividade(@RequestParam(required = false) Long id) {
		
		ModelAndView modelo = new ModelAndView("atividade/formulario.html");
		Atividade atividade;
		
		if(id==null) {
			atividade = new Atividade();
		}else {
			try {
			atividade = atividadeService.obterAtividade(id);
			}catch(Exception e){
				atividade = new Atividade();
				modelo.addObject("mensagem",e.getMessage());
			}
		}
		
		modelo.addObject("atividade", atividade);
		modelo.addObject("listaEventos", eventoService.listarTodosEventos());
		
		return modelo;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarAtividade(@Valid Atividade atividade, BindingResult bindingResult) {
		
		ModelAndView modelo = new ModelAndView("atividade/formulario.html");
		
		boolean novo = true;
		
		if(atividade.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()){
			modelo.addObject("atividade", atividade);
			return modelo;
			
		}
		
		atividadeService.salvarAtividade(atividade);
		
		if(novo) {
			modelo.addObject("atividade", new Atividade());
		}else {
			modelo.addObject("atividade", atividade);
		}
		
		modelo.addObject("mensagem", "Atividade salva com sucesso.");
		modelo.addObject("listaEventos", eventoService.listarTodosEventos());
		
		return modelo;
	}
	
	@RequestMapping
	public ModelAndView listarAtividades(String nome) {
		
		ModelAndView modelo = new ModelAndView("atividade/listar.html");
		
		modelo.addObject("nome", nome);
		modelo.addObject("lista", atividadeService.listarAtividade(nome));		
		
		return modelo;
		
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirAtividade(@RequestParam Long id, RedirectAttributes redirectAtributes) {
		
		ModelAndView modelo = new ModelAndView("redirect:/atividade");

		try {
			atividadeService.excluirAtividade(id);
			redirectAtributes.addFlashAttribute("mensagem", "Atividade excluido com exito.");
		}catch(Exception e){
			redirectAtributes.addFlashAttribute("mensagem", "Erro ao excluir atividade." + e.getMessage());
		}

		return modelo;
	}
}