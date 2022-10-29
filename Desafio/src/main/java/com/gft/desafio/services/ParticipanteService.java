package com.gft.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.entities.Atividade;
import com.gft.desafio.entities.Participante;
import com.gft.desafio.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

	@Autowired
	private ParticipanteRepository participanteRepository;
	
	public Participante salvarParticipante(Participante participante) {
		
		return participanteRepository.save(participante) ;
	}
	
	public List<Participante> listarParticipante(String nome, String quatroLetras){
		
		if(nome != null || quatroLetras != null)
			return listarParticipantePorNomeEQuatroLetras(nome, quatroLetras);
		
		return listarTodosParticipantes();
	}
	
	private List<Participante> listarParticipantePorNomeEQuatroLetras(String nome, String quatroLetras){
		
		return participanteRepository.findByNomeContainsAndQuatroLetrasContains(nome, quatroLetras);
	}
	public List<Participante> listarTodosParticipantes(){
		return participanteRepository.findAll();
	}
	
	public Participante obterDadosParticipante(Long id) throws Exception {
		
	Optional<Participante> participante = participanteRepository.findById(id);
	
	if(participante.isEmpty()) {
		throw new Exception("Participante não cadastrado.");
	}
		return participante.get();
		
	}
	
	public void excluirParticipante(Long id) {
		
		participanteRepository.deleteById(id);
	}

	public List<Atividade> listarAtividadesPaticipantes(Long id) {
		
		return participanteRepository.findAtividade(id) ;
	}
}