package br.edu.faculdadedelta.projetoestagio.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = repo.findByLogin(login);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return new UserSS(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getPerfis());
	}

}
