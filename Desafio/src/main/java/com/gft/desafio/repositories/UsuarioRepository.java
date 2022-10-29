package com.gft.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafio.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByNomeContains(String nome);
	
	List<Usuario> findByNomeContainsAndQuatroLetrasContains(String nome, String quatroLetras);
}