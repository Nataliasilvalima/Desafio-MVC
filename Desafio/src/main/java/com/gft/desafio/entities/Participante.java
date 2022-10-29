package com.gft.desafio.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	private String nome;
	
	@Email
	@NotEmpty(message = "Email não pode ser vazio.")
	private String email;
	
	@NotEmpty(message = "4 Letras ão pode ser em branco.")
	private String quatroLetras;
	
	@NotEmpty(message = "Nivel não pode ser vazio.")
	private String nivel;
	
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupos;
	
	@OneToMany(mappedBy = "id.participante")
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
	public Grupo getGrupos() {
		return grupos;
	}
	public void setGrupos(Grupo grupos) {
		this.grupos = grupos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuatroLetras() {
		return quatroLetras;
	}
	public void setQuatroLetras(String quatroLetras) {
		this.quatroLetras = quatroLetras;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public List<ParticipanteAtividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<ParticipanteAtividade> atividades) {
		this.atividades = atividades;
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