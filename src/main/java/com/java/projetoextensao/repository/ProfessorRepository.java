package com.java.projetoextensao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.projetoextensao.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
