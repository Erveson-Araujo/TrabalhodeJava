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

import com.java.projetoextensao.model.Professor;
import com.java.projetoextensao.repository.ProfessorRepository;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	ProfessorRepository professorRepository;
	
	@GetMapping("/novo")
	public String novoProfessor(Model model) {
		
		model.addAttribute("professor", new Professor());
		
		return "/auth/admin/admin-cadastrar-professor";
		
	}
	
	@PostMapping("/salvar")
	public String salvarProfessor(@Valid Professor professor, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "/auth/admin/admin-cadastrar-professor";
		}
		
		professorRepository.save(professor);
		
		redirectAttributes.addFlashAttribute("mensagem", "Sucesso");
		return "redirect:/professor/novo";
	}
	
	@GetMapping("/listar")
	public String listarProfessores(Model model) {
		
		model.addAttribute("listaProfessores", professorRepository.findAll());
		return "/publica-listar-professores";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model) {
		
		Optional<Professor> professorAntigo = professorRepository.findById(id);
		if (!professorAntigo.isPresent()) {
			throw new IllegalArgumentException("Professor(a) inválido" + id);
		}
		
		Professor professor = professorAntigo.get();
		model.addAttribute("professor", professor);
		return "/auth/admin/admin-editar-professor";
		
	}
	
	@PostMapping("/editar/{id}")
	public String editarFinal(@PathVariable("id") Long id, @Valid Professor professor, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			professor.setId(id);
			return "/auth/admin/admin-editar-professor";
			
		}
		
		professorRepository.save(professor);
		redirectAttributes.addFlashAttribute("mensagem", "Editar");
		return "redirect:/professor/listar";
		
		
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarProfessor(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		
		Professor professor = professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
		
		professorRepository.delete(professor);
		redirectAttributes.addFlashAttribute("mensagem", "Apagar");
		return "redirect:/professor/listar";
		
	}
	
}
