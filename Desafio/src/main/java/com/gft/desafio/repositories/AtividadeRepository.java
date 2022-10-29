package com.gft.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafio.entities.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
	
	List<Atividade> findByNomeContains(String nome);
	
}