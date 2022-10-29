package com.gft.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gft.desafio.entities.Atividade;
import com.gft.desafio.entities.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

	List<Participante> findByNomeContains(String nome);
	
	List<Participante> findByNomeContainsAndQuatroLetrasContains(String nome, String quatroLetras);
	
	@Query("SELECT p FROM Atividade p JOIN FETCH p.atividades WHERE atividades_id = ?1")
	List<Atividade> findAtividade(Long id);
}
