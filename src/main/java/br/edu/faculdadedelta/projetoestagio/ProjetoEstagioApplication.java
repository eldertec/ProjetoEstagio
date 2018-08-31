package br.edu.faculdadedelta.projetoestagio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.faculdadedelta.projetoestagio.domain.Role;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.repositories.RoleRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;

@SpringBootApplication
public class ProjetoEstagioApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEstagioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(null, null, null, "admin", "admin", null, null);

		Role role = new Role(null, "Administrador", usuario);

		usuarioRepository.saveAll(Arrays.asList(usuario));
		roleRepository.saveAll(Arrays.asList(role));
	}
}
