package com.gft.desafio.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotEmpty;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	private String nome;
	
	private Long qntPessoas;
	
	@OneToMany(mappedBy = "grupos",cascade = CascadeType.ALL)
	private List<Participante> participantes;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Evento eventos;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getQntPessoas() {
		return qntPessoas;
	}
	public void setQntPessoas(Long qntPessoas) {
		this.qntPessoas = qntPessoas;
	}
	public List<Participante> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}
	
	public Evento getEventos() {
		return eventos;
	}
	public void setEventos(Evento eventos) {
		this.eventos = eventos;
	}
	
	@PreRemove
	public void atualizarEvento() {
			this.setEventos(null);
		}
}