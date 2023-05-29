package com.java.projetoextensao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.projetoextensao.model.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

}
