package com.java.projetoextensao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.projetoextensao.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
