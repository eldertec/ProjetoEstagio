package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.faculdadedelta.projetoestagio.domain.Role;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.domain.Utilizador;
import br.edu.faculdadedelta.projetoestagio.domain.enums.TipoUtilizador;
import br.edu.faculdadedelta.projetoestagio.repositories.RoleRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UtilizadorRepository;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/cadastroUsuario.xhtml")
public class UtilizadorController {

	@Autowired
	private UtilizadorRepository repo;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository roleRepository;

	private Role role = new Role();

	private Utilizador utilizador = new Utilizador();
	private Usuario usuario = new Usuario();

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public String salvar() {
		if (utilizador.getId() == null) {
			if (utilizador.getTipo().getCod() == 1) {
				role.setRole("ROLE_COORDENADOR");
			}
			if (utilizador.getTipo().getCod() == 2) {
				role.setRole("ROLE_REPRESENTANTE");
			}
			if (utilizador.getTipo().getCod() == 3) {
				role.setRole("ROLE_FUNCIONARIO");
			}
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			roleRepository.saveAll(Arrays.asList(role));
			usuario.getRoles().addAll(Arrays.asList(role));
			usuarioRepository.save(usuario);
			utilizador.setUsuario(usuario);
			repo.save(utilizador);
			FacesUtil.exibirMsg("Cadastro realizado com sucesso!");
			limpar();
		} else {
			repo.save(utilizador);
			FacesUtil.exibirMsg("Cadastro atualizado com sucesso!");
			limpar();
		}

		return "cadastroUsuario.xhtml";
	}

	public String atualizar() {

		return "cadastroUsuario.xhtml";
	}

	public String remover() {
		repo.delete(utilizador);
		FacesUtil.exibirMsg("Cadastro removido com sucesso!");
		limpar();
		return "cadastroUsuario.xhtml";
	}

	public List<Utilizador> getListar() {
		List<Utilizador> utilizadores = new ArrayList<>();
		utilizadores = repo.findAll();
		return utilizadores;
	}

	public TipoUtilizador[] getTipos() {
		return TipoUtilizador.values();
	}

}
