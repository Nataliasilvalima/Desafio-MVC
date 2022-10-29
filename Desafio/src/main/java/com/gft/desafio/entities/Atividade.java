package com.gft.desafio.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome da atividade tem que ser registrado.")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicio;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entrega;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "eventos_id", referencedColumnName = "id")
	private Evento eventos;
	
	@OneToMany(mappedBy = "id.atividade")
	private List<ParticipanteAtividade> atividades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getEntrega() {
		return entrega;
	}
	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}
	public List<ParticipanteAtividade> getParticipantes() {
		return atividades;
	}
	public void setParticipantes(List<ParticipanteAtividade> atividades) {
		this.atividades = atividades;
	}
	public Evento getEventos() {
		return eventos;
	}
	public void setEventos(Evento eventos) {
		this.eventos = eventos;
	}
	@Override
	public int hashCode() {

		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	
	@PreRemove
	public void atualizarEvento() {
			this.setEventos(null);
		}
}