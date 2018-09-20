package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	private Long idSelecionado;

	private Instrutor instrutorSelecionado = new Instrutor();

	private Optional<Instrutor> instrutor = Optional.empty();

	private Curso curso = new Curso();

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public Optional<Instrutor> getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Optional<Instrutor> instrutor) {
		this.instrutor = instrutor;
	}

	public Instrutor getInstrutorSelecionado() {
		return instrutorSelecionado;
	}

	public void setInstrutorSelecionado(Instrutor instrutorSelecionado) {
		this.instrutorSelecionado = instrutorSelecionado;
	}

	public String limpar() {
		curso = new Curso();
		idSelecionado = (long) 0;
		return "cadastroCurso.xhtml";
	}

	public Instrutor retornaInstrutor(Long id) {
		instrutor = instrutorRepository.findById(id);
		return instrutor.get();
	}

	public String salvar() {
		instrutorSelecionado = retornaInstrutor(idSelecionado);
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
		idSelecionado = curso.getInstrutor().getId();
		return "cadastroCurso.xhtml";
	}

	public String remover() {
		cursoRepository.delete(curso);
		limpar();
		return "cadastroCurso.xhtml";
	}

	public List<Curso> getListar() {
		List<Curso> cursos = new ArrayList<>();
		cursos = cursoRepository.findAll();
		return cursos;
	}

}
