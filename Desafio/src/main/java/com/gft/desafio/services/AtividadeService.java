package com.gft.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.entities.Atividade;
import com.gft.desafio.repositories.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
		public Atividade salvarAtividade(Atividade atividade) {
			
			return atividadeRepository.save(atividade);
		}
		
		public List<Atividade> listarAtividade(String nome){
			
			if(nome != null )
				return listarAtividadePorNomeEEventos(nome);
			
			return listarTodasAtividades();
		}
		
		private List<Atividade> listarAtividadePorNomeEEventos(String nome){
			
			return atividadeRepository.findByNomeContains(nome);
		}
		
		public List<Atividade> listarTodasAtividades(){
			return atividadeRepository.findAll();
		}
		
		public Atividade obterAtividade(Long id) throws Exception{
			
			Optional<Atividade> atividade = atividadeRepository.findById(id);
			
			if(atividade.isEmpty()) {
				throw new Exception("Atividade não cadastrado.");
			}
				return atividade.get();
		}
		
		public void excluirAtividade(Long id) {
			atividadeRepository.deleteById(id);
		}
}
