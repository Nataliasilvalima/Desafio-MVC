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

import com.gft.desafio.entities.Usuario;
import com.gft.desafio.services.UsuarioService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "novo")
	public ModelAndView editarUsuario(@RequestParam(required = false) Long id) {
		
		ModelAndView modelo = new ModelAndView("usuario/formulario.html");
		Usuario usuario;
		
		if(id == null) {
			usuario = new Usuario();
		}else {
			try {
			usuario = usuarioService.obterDadosUsuario(id);
		}catch(Exception e ) {
			usuario = new Usuario();
			modelo.addObject("mensagem", e.getMessage());
		}
		}
		modelo.addObject("usuario", usuario);
		return modelo;
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "novo")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {
		
		ModelAndView modelo = new ModelAndView("usuario/formulario.html");
		
		boolean novo = true;
		
		if(usuario.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()){
			modelo.addObject("usuario", usuario);
			return modelo;
			
		}
		
		usuarioService.salvarUsuario(usuario);
		
		if(novo) {
			modelo.addObject("usuario", new Usuario());
		}else {
			modelo.addObject("usuario", usuario);
		}
		
		modelo.addObject("mensagem", "Usuario salvo com sucesso.");
		
		return modelo;
	}
	
	@RequestMapping
	public ModelAndView listarUsuario(String nome, String quatroLetras) {
		
		ModelAndView modelo = new ModelAndView("usuario/editar.html");
		
		modelo.addObject("lista", usuarioService.listarUsuario(nome, quatroLetras));
		modelo.addObject("nome", nome);
		modelo.addObject("quatroLetras", quatroLetras);
		
		return modelo;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirUsuario(@RequestParam Long id, RedirectAttributes redirectAtributes) {
		
		ModelAndView modelo = new ModelAndView("redirect:/usuario");

		try {
			usuarioService.excluirUsuario(id);
			redirectAtributes.addFlashAttribute("mensagem", "Usuario excluido com exito.");
		}catch(Exception e){
			redirectAtributes.addFlashAttribute("mensagem", "Erro ao excluir usuario." + e.getMessage());
		}

		return modelo;
	}
}