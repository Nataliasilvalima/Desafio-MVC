package com.gft.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gft.desafio.entities.Atividade;
import com.gft.desafio.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
	@Query("SELECT atividade FROM Atividade atividade JOIN FETCH atividade.eventos WHERE eventos_id = ?1")
	List<Atividade> findAtividade(Long id);
		  
}