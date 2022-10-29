package com.gft.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafio.entities.Atividade;
import com.gft.desafio.entities.Evento;
import com.gft.desafio.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	public Evento salvarEvento(Evento evento) {
		
		return eventoRepository.save(evento);
	}
	
	public List<Evento> listarTodosEventos(){
		return eventoRepository.findAll();
	}
	
	public Evento obterEventos(Long id) throws Exception{
		
		Optional<Evento> evento = eventoRepository.findById(id);
		
		if(evento.isEmpty()) {
			throw new Exception("Evento não cadastrado.");
		}
			return evento.get();
	}
	
	public void excluirEvento(Long id) {
		eventoRepository.deleteById(id);;
	}
	
	public List<Atividade> listarAtividadesEventos(Long id){
		
		return eventoRepository.findAtividade(id);
	
	}
}
