package com.gft.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafio.entities.ParticipanteAtividade;

@Repository
public interface ParticipanteAtividadeRepository extends JpaRepository<ParticipanteAtividade, Long> {

}
