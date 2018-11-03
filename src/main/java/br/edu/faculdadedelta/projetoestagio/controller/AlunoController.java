package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.faculdadedelta.projetoestagio.domain.Aluno;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.domain.enums.Perfil;
import br.edu.faculdadedelta.projetoestagio.repositories.AlunoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;
import br.edu.faculdadedelta.projetoestagio.security.UserSS;
import br.edu.faculdadedelta.projetoestagio.service.UsuarioService;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/cadastroAluno.xhtml")
public class AlunoController {

	private Aluno aluno = new Aluno();
	private Usuario usuario = new Usuario();
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String limpar() {
		usuario = new Usuario();
		aluno = new Aluno();
		return "cadastroAluno.xhtml";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String salvar() {
		if(aluno.getId() == null) {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuario.setLogin(aluno.getEmail());
			usuario.addPerfil(Perfil.ALUNO);
			usuarioRepository.save(usuario);
			aluno.setUsuario(usuario);
			alunoRepository.save(aluno);
			limpar();
			FacesUtil.exibirMsg("Cadastro realizado com sucesso!");
			return "/";
		}else {
			alunoRepository.save(aluno);
			FacesUtil.exibirMsg("Cadastro atualizado com sucesso!");
			limpar();
			return "cadastroAluno.xhtml";
		}
		
	}
	
	public String atualizar() {
		return "cadastroAluno.xhtml";
	}
	
	
	public String remover() {
		alunoRepository.delete(aluno);
		limpar();
		return "listaAluno.xhtml";
	}
	
	public List<Aluno> getListar(){
		List<Aluno> alunos = new ArrayList<>();
		
		alunos = alunoRepository.findAll();
		
		return alunos;
	}
	
	public List<Aluno> getListarLogado(){
		List<Aluno> alunoLogado = new ArrayList<>();
		UserSS logado = UsuarioService.logado();
		if(logado != null) {
			aluno = alunoRepository.findByEmail(logado.getUsername());
			alunoLogado.add(aluno);
		}
		return alunoLogado;
	}
	

}
