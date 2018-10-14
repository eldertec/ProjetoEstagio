package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.faculdadedelta.projetoestagio.domain.Aluno;
import br.edu.faculdadedelta.projetoestagio.domain.Curso;
import br.edu.faculdadedelta.projetoestagio.domain.Matricula;
import br.edu.faculdadedelta.projetoestagio.domain.enums.StatusPagamento;
import br.edu.faculdadedelta.projetoestagio.domain.enums.StatusPresenca;
import br.edu.faculdadedelta.projetoestagio.repositories.AlunoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.CursoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.MatriculaRepository;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/matricula.xhtml")
public class MatriculaController {

	@Autowired
	private MatriculaRepository repo;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	private Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	private Matricula matricula = new Matricula();
	private Curso curso = new Curso();
	private String matriculaAluno;

	private Aluno logado = new Aluno();

	public Aluno getLogado() {
		return logado;
	}

	public void setLogado(Aluno logado) {
		this.logado = logado;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public Aluno logado() {
		Aluno aluno = new Aluno();
		if (principal instanceof UserDetails) {
			String login = ((UserDetails) principal).getUsername();
			aluno = alunoRepository.findByEmail(login);
		}
		if (aluno != null) {
			return aluno;
		}
		return null;
	}

	public String salvar() {
		logado = logado();
		matricula.getId().setAluno(logado);
		matricula.getId().setCurso(curso);
		matricula.setDataMatricula(new Date());
		matricula.setStatusPagamento(StatusPagamento.AGUARDANDO);
		matricula.setStatusPresenca(StatusPresenca.AUSENTE);
		repo.save(matricula);
		FacesUtil.exibirMsg("Matricula realizada com sucesso!");
		return "matricula.xhtml";
	}

	public String remover() {
		repo.delete(matricula);
		FacesUtil.exibirMsg("Curso removido com sucesso!");
		return "matricula.xhtml";
	}

	public List<Matricula> getListar() {
		List<Matricula> matriculas = new ArrayList<>();
		matriculas = repo.findAll();
		return matriculas;
	}

	public List<Curso> getListarCurso() {
		List<Curso> cursos = new ArrayList<>();
		cursos = cursoRepository.findAll();
		return cursos;
	}

	public List<Curso> getListarDetalheCurso() {
		List<Curso> cursos = new ArrayList<>();
		cursos = cursoRepository.findAll();
		return cursos;
	}
	
	public String checkin() {
		logado = alunoRepository.findByMatricula(matriculaAluno);
		matricula = repo.findByIdAluno(logado);
		if(matricula != null) {
			matricula.setStatusPresenca(StatusPresenca.PRESENTE);
			repo.save(matricula);
			FacesUtil.exibirMsg("Checkin realizado com sucesso!");
			return "";
		}
		FacesUtil.exibirMsg("Aluno não encontrado");
		return "";
	}

}
