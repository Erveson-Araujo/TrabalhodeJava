package com.java.projetoextensao.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ocorrencia_alunos")
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id_aluno")
	@NotNull(message = "O aluno deve ser informado.")
	private Aluno aluno; // Aluno que irá receber a ocorrência
	
	@NotNull(message = "O ano que o aluno está matriculado deve ser informado.")
	@NotBlank(message = "O ano que o aluno está matriculado deve ser informado.")
	private String ano;
	
	@NotNull(message = "A turma que o aluno está matriculado deve ser informado.")
	@NotBlank(message = "A turma que o aluno está matriculado deve ser informado.")
	private String turma;
	
	@NotNull(message = "A data da ocorrência deve ser informada!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@NotNull(message = "A classificação da ocorrência deve ser informada!")
	@NotBlank(message = "A classificação da ocorrência deve ser informada!")
	private String classificacao; // Merito ou demerito
	
	@OneToOne
	@NotNull(message = "O professor que reportou deve ser informado!")
	@NotBlank(message = "O professor que reportou deve ser informado!")
	@JoinColumn(name = "id_professor")
	private Professor professor; // No front, professor.nome;
	
	@NotNull(message = "Informe a justificativa do aluno.")
	@Size(min = 3, max = 60, message = "A justificativa deve conter entre {min} e {max} caracteres")
	private String justificativa; // Por que o aluno fez isso
	
	@NotNull(message = "O tipo da ocorrência deve ser informado!")
	@NotBlank(message = "O tipo da ocorrência deve ser informado!")
	private String tipo; // Agressão, Furto, desrespeito...
	
	@NotNull(message = "Quem assinou deve ser informado!")
	@NotBlank(message = "Quem assinou deve ser informado!")
	private String decisao; // QUEM ASSINOU, É FIXO (RUBRICA OGES/OGED/DIREÇÃO ESCOLAR)
	
	@NotNull(message = "A solução da ocorrência deve ser informada!")
	@NotBlank(message = "A solução da ocorrência deve ser informada!")
	private String solucao; // Elogio, advertencia... tudo isso vai depender do merito/demerito... tem opções boas (+) e ruins (-) para afetar a pontuacao do aluno

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDecisao() {
		return decisao;
	}

	public void setDecisao(String decisao) {
		this.decisao = decisao;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ocorrencia other = (Ocorrencia) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
