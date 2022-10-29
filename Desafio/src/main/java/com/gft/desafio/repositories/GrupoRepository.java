package com.gft.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafio.entities.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	List<Grupo> findByNomeContains(String nome);
}