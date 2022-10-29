package com.gft.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.entities.Usuario;
import com.gft.desafio.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario) ;
	}
	
	public List<Usuario> listarUsuarioAdm(){
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> listarUsuario(String nome, String quatroLetras){
		
		if(nome != null || quatroLetras != null )
			return listarUsuarioPorNomeEQuatroLetras(nome, quatroLetras);
		
		return listarUsuarioAdm();
	}
	
private List<Usuario> listarUsuarioPorNomeEQuatroLetras(String nome, String quatroLetras){
		
		return usuarioRepository.findByNomeContainsAndQuatroLetrasContains(nome, quatroLetras);
	}
	
	public Usuario obterDadosUsuario(Long id) throws Exception {
		
	Optional<Usuario> usuario = usuarioRepository.findById(id);
	
	if(usuario.isEmpty()) {
		throw new Exception("Usuario não cadastrado.");
	}
		return usuario.get();
		
	}
	
	public void excluirUsuario(Long id) {
		
		usuarioRepository.deleteById(id);
	}
}
