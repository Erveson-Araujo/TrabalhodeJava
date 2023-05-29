package com.java.projetoextensao.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*Validações*/
	@NotBlank(message = "O nome do aluno é obrigatório.")
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	/*
	 * 85912345678 = 11 começando a contar do 1 // 10 começando a contar do 0.
	 * (DDD + 9 na frente + 8 digitos de números)
	 * */
	
	@NotBlank(message = "O telefone do aluno é obrigatório.")
	private String telefoneAluno;
	
	@NotBlank(message = "O telefone do responsável do aluno é obrigatório.")
	private String telefoneResponsavel;

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

	public String getTelefoneAluno() {
		return telefoneAluno;
	}

	public void setTelefoneAluno(String telefoneAluno) {
		this.telefoneAluno = telefoneAluno;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, telefoneResponsavel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id) && Objects.equals(telefoneResponsavel, other.telefoneResponsavel);
	}
	
	
}
