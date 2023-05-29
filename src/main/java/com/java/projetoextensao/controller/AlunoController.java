package com.java.projetoextensao.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.projetoextensao.model.Aluno;
import com.java.projetoextensao.repository.AlunoRepository;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;
	
	@GetMapping("/novo")
	public String novoAluno(Model model) {
		model.addAttribute("aluno", new Aluno());
		
		return "/auth/admin/admin-cadastrar-aluno";
	}
	
	@PostMapping("/salvar") // BindingResult = auxilia na reposta do erro do cadastro, redirect : volta pra pagina
	// redirect do novo e mostra um flash attribute (mensagem) com auxilio do toastr
	public String salvarAluno(@Valid Aluno aluno, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "/auth/admin/admin-cadastrar-aluno";
		}
		
		alunoRepository.save(aluno);
		redirectAttributes.addFlashAttribute("mensagem", "Sucesso");
		return "redirect:/aluno/novo";
		
	}
 	
	@GetMapping("/listar")
	public String listarAlunos(Model model) {
		
		model.addAttribute("listaAlunos", alunoRepository.findAll());
		return "/publica-listar-alunos";
	}
	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model) {
		
		Optional<Aluno> alunoAntigo = alunoRepository.findById(id);
		
		if (!alunoAntigo.isPresent()) {
			throw new IllegalArgumentException("Aluno inválido: " + id);
		}
		
		Aluno aluno = alunoAntigo.get();
		model.addAttribute("aluno", aluno);
		return "/auth/admin/admin-editar-aluno";
		
	}
	
	@PostMapping("/editar/{id}")
	public String editarFinal(@PathVariable("id") Long id, @Valid Aluno aluno, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			aluno.setId(id);
			return "/auth/admin/admin-editar-aluno";
		}
		
		alunoRepository.save(aluno);
		redirectAttributes.addFlashAttribute("mensagem", "Editar");
		return "redirect:/aluno/listar";
		
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarAluno(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
		
		alunoRepository.delete(aluno);
		redirectAttributes.addFlashAttribute("mensagem", "Apagar");
		return "redirect:/aluno/listar";
		
	}
	
}
