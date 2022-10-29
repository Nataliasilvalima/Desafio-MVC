package com.gft.desafio.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ParticipanteAtividade {

	@EmbeddedId
	private ParticipanteAtividadesID id;
	
	private String status;
	
	public ParticipanteAtividade() {
		super();
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ParticipanteAtividadesID getId() {
		return id;
	}
	public void setId(ParticipanteAtividadesID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
	
		return super.toString();
	}
}