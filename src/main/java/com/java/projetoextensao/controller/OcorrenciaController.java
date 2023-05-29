package com.java.projetoextensao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.projetoextensao.model.Ocorrencia;
import com.java.projetoextensao.repository.OcorrenciaRepository;

@Controller
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	@GetMapping("/novo")
	public String novaOcorrencia(Model model) {
		
		model.addAttribute("ocorrencia", new Ocorrencia());
		return "/auth/admin/admin-registrar-ocorrencia";
	}
	
	@PostMapping("/salvar")
	public String registrarOcorrencia(@Valid Ocorrencia ocorrencia, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "/auth/admin/admin-registrar-ocorrencia";
		}
		
		ocorrenciaRepository.save(ocorrencia);
		redirectAttributes.addFlashAttribute("mensagem", "Sucesso");
		return "redirect:/ocorrencia/novo";
		
	}
	
}
