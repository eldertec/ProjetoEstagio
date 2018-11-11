package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.domain.Utilizador;
import br.edu.faculdadedelta.projetoestagio.domain.enums.Perfil;
import br.edu.faculdadedelta.projetoestagio.domain.enums.TipoUtilizador;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UtilizadorRepository;
import br.edu.faculdadedelta.projetoestagio.security.UserSS;
import br.edu.faculdadedelta.projetoestagio.service.UsuarioService;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/cadastroUsuario.xhtml")
public class UtilizadorController {

	@Autowired
	private UtilizadorRepository repo;
	@Autowired
	private UsuarioRepository usuarioRepository;


	private Utilizador utilizador = new Utilizador();
	private Usuario usuario = new Usuario();

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String limpar() {
		utilizador = new Utilizador();
		usuario = new Usuario();
		return "cadastroUsuario.xhtml";
	}
	
	public String limparLogado() {
		utilizador = new Utilizador();
		usuario = new Usuario();
		return "cadastroUsuarioLogado.xhtml";
	}

	public String salvar() {
		if (utilizador.getId() == null) {
			if (utilizador.getTipo().getCod() == 1) {
				usuario.addPerfil(Perfil.COORDENADOR);
			}else 
			if (utilizador.getTipo().getCod() == 2) {
				usuario.addPerfil(Perfil.REPRESENTANTE);
			}else 
			if (utilizador.getTipo().getCod() == 3) {
				usuario.addPerfil(Perfil.FUNCIONARIO);
			}
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuarioRepository.save(usuario);
			utilizador.setUsuario(usuario);
			repo.save(utilizador);
			FacesUtil.exibirMsg("Cadastro realizado com sucesso!");
			limpar();
			return "cadastroUsuario.xhtml";
		} else {
			repo.save(utilizador);
			FacesUtil.exibirMsg("Cadastro atualizado com sucesso!");
			limpar();
			return "cadastroUsuarioLogado.xhtml";
		}

		
	}

	public String atualizar() {

		return "cadastroUsuarioLogado.xhtml";
	}

	public String remover() {
		repo.delete(utilizador);
		usuarioRepository.delete(utilizador.getUsuario());
		FacesUtil.exibirMsg("Cadastro removido com sucesso!");
		limpar();
		return "cadastroUsuario.xhtml";
	}

	public List<Utilizador> getListar() {
		List<Utilizador> utilizadores = new ArrayList<>();
		utilizadores = repo.findAll();
		return utilizadores;
	}
	
	public List<Utilizador> getListarLogado() {
		List<Utilizador> userlogado = new ArrayList<>();
		UserSS logado = UsuarioService.logado();
		if(logado != null) {
			utilizador = repo.findByUsuarioId(logado.getId());
			userlogado.add(utilizador);
		}
		return userlogado;
	}

	public TipoUtilizador[] getTipos() {
		return TipoUtilizador.values();
	}
	
	public Usuario getUsuarioLogado(@AuthenticationPrincipal Usuario logado, RedirectAttributes model){
		  return logado;
		}
	
	


}
