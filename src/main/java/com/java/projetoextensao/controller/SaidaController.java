package com.java.projetoextensao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.projetoextensao.model.Aluno;
import com.java.projetoextensao.model.Professor;
import com.java.projetoextensao.model.Saida;
import com.java.projetoextensao.repository.AlunoRepository;
import com.java.projetoextensao.repository.ProfessorRepository;
import com.java.projetoextensao.repository.SaidaRepository;

@Controller
@RequestMapping("/saida")
public class SaidaController {

	@Autowired
	AlunoRepository alunoRepository; // Preciso do acesso a camada de Repository para pegar as infos dos alunos (findAll) p/ meu Select
	
	@Autowired
	ProfessorRepository professorRepository; // same thing
	
	@Autowired
	SaidaRepository saidaRepository;
	
	@GetMapping("/novo")
	public String novaSaida(Model model) {
		
		model.addAttribute("saida", new Saida());
		return "/auth/admin/admin-registrar-saida";
	}
	
	@PostMapping("/salvar")
	public String registrarSaida(@Valid Saida saida, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "auth/admin/admin-registrar-saida";
		}
		
		saidaRepository.save(saida);
		redirectAttributes.addFlashAttribute("mensagem", "Sucesso");
		return "redirect:/saida/novo";
		
	}
	
	@GetMapping("/listar")
	public String listarSaidas(Model model) {
		
		model.addAttribute("listaSaidas", saidaRepository.findAll());
		return "publica-listar-saidas";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model) {
		
		Optional<Saida> saidaVelha = saidaRepository.findById(id);
		if (!saidaVelha.isPresent()) {
			throw new IllegalArgumentException("ID Inválido: " + id);
		}
		
		Saida saida = saidaVelha.get();
		model.addAttribute("saida", saida);
		return "/auth/admin/admin-editar-saida";
		
	}
	
	@PostMapping("/editar/{id}")
	public String editarFinal(@PathVariable("id") Long id, @Valid Saida saida, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			saida.setId(id);
			
			return "/auth/admin/admin-editar-saida";
		}
		
		saidaRepository.save(saida);
		redirectAttributes.addFlashAttribute("mensagem", "Editar");
		return "redirect:/saida/listar";
	}
	
	@GetMapping("/apagar/{id}")
	public String excluirRegistroSaida(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		
		Saida saida = saidaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
		
		saidaRepository.delete(saida);
		redirectAttributes.addFlashAttribute("mensagem", "Apagar");
		return "redirect:/saida/listar";
		
	}
	
	@ModelAttribute("alunos")
	public List<Aluno> listaDeAlunos() {
		return alunoRepository.findAll();
	}
	
	@ModelAttribute("professores")
	public List<Professor> listaDeProfessores() {
		return professorRepository.findAll();
	}
	
}
