package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.faculdadedelta.projetoestagio.domain.Curso;
import br.edu.faculdadedelta.projetoestagio.domain.Instrutor;
import br.edu.faculdadedelta.projetoestagio.repositories.CursoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.InstrutorRepository;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/cadastroCurso.xhtml")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private InstrutorRepository instrutorRepository;

	@Autowired
	private Instrutor instrutorSelecionado;

	private Curso curso = new Curso();

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Instrutor getInstrutorSelecionado() {
		return instrutorSelecionado;
	}

	public void setInstrutorSelecionado(Instrutor instrutorSelecionado) {
		this.instrutorSelecionado = instrutorSelecionado;
	}

	public String limpar() {
		curso = new Curso();
		return "cadastroCurso.xhtml";
	}

	public String salvar() {
		if (curso.getId() == null) {
			curso.setInstrutor(instrutorSelecionado);
			cursoRepository.save(curso);
			instrutorSelecionado.getCursos().addAll(Arrays.asList(curso));
			instrutorRepository.save(instrutorSelecionado);
			limpar();
			FacesUtil.exibirMsg("Curso cadastrado com sucesso!");
		} else {
			curso.setInstrutor(instrutorSelecionado);
			cursoRepository.save(curso);
			instrutorSelecionado.getCursos().addAll(Arrays.asList(curso));
			instrutorRepository.save(instrutorSelecionado);
			limpar();
			FacesUtil.exibirMsg("Curso atualizado com sucesso!");
		}
		return "cadastroCurso.xhtml";
	}

	public String atualizar() {
		instrutorSelecionado = curso.getInstrutor();
		return "cadastroCurso.xhtml";
	}

	public String remover() {
		cursoRepository.delete(curso);
		return "listaCurso.xhtml";
	}

	public List<Curso> getListar() {
		List<Curso> cursos = new ArrayList<>();
		cursos = cursoRepository.findAll();
		return cursos;
	}

}
