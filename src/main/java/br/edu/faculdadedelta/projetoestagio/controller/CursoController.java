package br.edu.faculdadedelta.projetoestagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.faculdadedelta.projetoestagio.domain.Curso;
import br.edu.faculdadedelta.projetoestagio.repositories.CursoRepository;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/cadastroCurso.xhtml")
public class CursoController {

	private Curso curso = new Curso();
	@Autowired
	private CursoRepository cursoRepository;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String limpar() {
		curso = new Curso();
		return "cadastroCurso.xhtml";
	}

	public String salvar() {
		cursoRepository.save(curso);
		limpar();
		FacesUtil.exibirMsg("Curso cadastrado com sucesso!");
		return "cadastroCurso.xhtml";
	}

}
