package com.gft.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.entities.Grupo;
import com.gft.desafio.repositories.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo salvarGrupo(Grupo grupo) {
		
		return grupoRepository.save(grupo);
	}
	
	public List<Grupo> listarGrupos(String nome){
		
		if(nome != null)
			return listarGrupoPorNome(nome);
		
		return listarTodosGrupos();
	}
	
	private List<Grupo> listarGrupoPorNome(String nome){
		
		return grupoRepository.findByNomeContains(nome);
	}
	
	public List<Grupo> listarTodosGrupos(){
		
		return grupoRepository.findAll();
	}
	
	public Grupo obterGrupo(Long id) throws Exception{
		
		Optional<Grupo> grupo = grupoRepository.findById(id);
		
		if(grupo.isEmpty()) {
			throw new Exception("Grupo não cadastrado.");
		}
		
		return grupo.get();
	}
	
	public void excluirGrupo(Long id) {
		
		grupoRepository.deleteById(id);
	}
}