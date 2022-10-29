package com.gft.desafio.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class ParticipanteAtividadesID implements Serializable{
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Participante participante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Atividade atividade;

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public int hashCode() {
		
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	
		return super.equals(obj);
	}
}