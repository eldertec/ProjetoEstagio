package br.edu.faculdadedelta.projetoestagio.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.faculdadedelta.projetoestagio.security.UserSS;

public class UsuarioService {

	public static UserSS logado() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
