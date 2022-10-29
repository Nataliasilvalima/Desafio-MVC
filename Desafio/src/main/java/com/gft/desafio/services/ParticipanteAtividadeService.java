package com.gft.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.entities.ParticipanteAtividade;
import com.gft.desafio.repositories.ParticipanteAtividadeRepository;

@Service
public class ParticipanteAtividadeService {
	
	@Autowired
	private ParticipanteAtividadeRepository participanteAtividadeRepository;
	
	public ParticipanteAtividade salvarPartAtividade(ParticipanteAtividade partAtividade) {
		
		return participanteAtividadeRepository.save(partAtividade);
	}
	
	public List<ParticipanteAtividade> listarTodosPartAtividade(){
		
		return participanteAtividadeRepository.findAll();
	}
	
	public ParticipanteAtividade obterPartAtividade(Long id) throws Exception{
		
		Optional<ParticipanteAtividade> partiAtividade = participanteAtividadeRepository.findById(id);
		
		if(partiAtividade.isEmpty()) {
			throw new Exception("Atividade não cadastrada.");
		}
			return partiAtividade.get();
		
	}
	
	public void excluirPartiAtividade(Long id) {
		
		participanteAtividadeRepository.deleteById(id);
	}
}