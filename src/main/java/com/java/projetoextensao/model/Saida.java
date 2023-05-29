package com.java.projetoextensao.model;

import java.time.LocalTime;
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
@Table(name = "saida_alunos")
public class Saida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id_aluno")
	@NotNull(message = "O aluno que saiu deve ser informado.")
	private Aluno aluno; // Qual aluno saiu?
	
	@OneToOne
	@JoinColumn(name = "id_professor")
	@NotNull(message = "O professor que estava dando aula no momento da saída deve ser informado.")
	private Professor professor; // Saiu em aula de qual professor?
	
	@NotNull(message = "Informe o motivo da saída.")
	@Size(min = 3, max = 60, message = "O motivo da saída deve conter entre {min} e {max} caracteres")
	private String motivo; // usarei um text area no front
	
	// ----------------------------------------------------------------
	
	/*Por que não usar LocalDateTime? 
	 * 
	 * Por questão de gosto, trabalhar com LocalDateTime no thymeleaf não é muito do meu agrado, fora que
	 * por regra de negócio eu queria mostrar os atributos em <td>'s separadas na minha View. 
	 * Dito isso, provavelmente essa maneira é "decente"...
	 * 
	 * */
	
	@NotNull(message = "A data da saída deve ser informada")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataSaida;
	
	@NotNull(message = "O horário da saída deve ser informado")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horarioSaida;
	
	
	/* Ano que o aluno está - POR REGRA DE NEGOCIO ISSO SERÁ UMA STRING, JÁ QUE NO COLÉGIO
	 * 
	 * as turmas são fixas do 6 ao 9 ano, pois é um projeto civico militar
	 * 
	 * */
	
	@NotNull(message = "O ano que o aluno está matriculado deve ser informado.")
	@NotBlank(message = "O ano que o aluno está matriculado deve ser informado.")
	private String ano; 
	
	/*Turma que o aluno está tambem é fixa (A,B,C) 
	 * Não irei criar uma classe PQ É UMA REGRA FIXA NAO IRÁ MUDAR... APESAR DE QUE
	 * SERIA UMA BOA PRÁTICA FAZER ISSO*/
	@NotNull(message = "A turma que o aluno está matriculado deve ser informado.")
	@NotBlank(message = "A turma que o aluno está matriculado deve ser informado.")
	private String turma;

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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public LocalTime getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(LocalTime horarioSaida) {
		this.horarioSaida = horarioSaida;
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
		Saida other = (Saida) obj;
		return Objects.equals(id, other.id);
	} 
	
	// ----------------------------------------------------------------

	
	
}
