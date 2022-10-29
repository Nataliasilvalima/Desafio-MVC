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

import com.gft.desafio.entities.Participante;
import com.gft.desafio.services.GrupoService;
import com.gft.desafio.services.ParticipanteService;

@Controller
@RequestMapping("participante")
public class ParticipanteController {

	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping(path = "novo")
	public ModelAndView editarParticipante(@RequestParam(required = false) Long id) {
		
		ModelAndView modelo = new ModelAndView("participante/formulario.html");
		Participante participante;
	
		if(id==null) {
			participante = new Participante();
		}else {
			try {
			participante = participanteService.obterDadosParticipante(id);
			}catch(Exception e){
				participante = new Participante();
				modelo.addObject("mensagem",e.getMessage());
			}
		}
		
		modelo.addObject("participante", participante);
		modelo.addObject("listaGrupos", grupoService.listarTodosGrupos());
		
		return modelo;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarParticipante (@Valid Participante participante, BindingResult bindingResult) {
		
		ModelAndView modelo = new ModelAndView("participante/formulario.html");
		
		boolean novo = true;
		
		if(participante.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()){
			modelo.addObject("participante", participante);
	
			return modelo;
		}
		
		participanteService.salvarParticipante(participante);
		
		if(novo) {
			modelo.addObject("participante", new Participante());
		}else {
			modelo.addObject("participante", participante);
		
		}
		
		modelo.addObject("mensagem", "Participante salvo com sucesso.");
		modelo.addObject("listaGrupos", grupoService.listarTodosGrupos());
		
		return modelo;
	}
	
	@RequestMapping
	public ModelAndView listarParticipantes(String nome, String quatroLetras) {
		
		ModelAndView modelo = new ModelAndView("participante/listar.html");
		
		modelo.addObject("lista", participanteService.listarParticipante(nome, quatroLetras));
		
		modelo.addObject("nome", nome);
		modelo.addObject("quatroLetras", quatroLetras);
		
		return modelo;
		
	}
	
	@RequestMapping("/detalhe")
	public ModelAndView detalheAtividades(Long id) {
		
		ModelAndView modelo = new ModelAndView("participante/detalhe.html");
		modelo.addObject("lista", participanteService.listarAtividadesPaticipantes(id));
		
		return modelo;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirParticipante(@RequestParam Long id, RedirectAttributes redirectAtributes) {
		
		ModelAndView modelo = new ModelAndView("redirect:/participante");

		try {
			participanteService.excluirParticipante(id);
			redirectAtributes.addFlashAttribute("mensagem", "Participante excluido com exito.");
		}catch(Exception e){
			redirectAtributes.addFlashAttribute("mensagem", "Erro ao excluir participante." + e.getMessage());
		}

		return modelo;
	}
}