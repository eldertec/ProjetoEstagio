package br.edu.faculdadedelta.projetoestagio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.faculdadedelta.projetoestagio.domain.Instrutor;
import br.edu.faculdadedelta.projetoestagio.domain.Role;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.repositories.InstrutorRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;

@SpringBootApplication
public class ProjetoEstagioApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private InstrutorRepository instrutorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEstagioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(null, "admin", new BCryptPasswordEncoder().encode("admin"));

		Role role = new Role("ROLE_ADMIN");
		usuario.getRoles().addAll(Arrays.asList(role));

		usuarioRepository.saveAll(Arrays.asList(usuario));

		Instrutor i1 = new Instrutor(null, "Paulo Roberto", "Spring MVC", "paulonill@gmail.com");
		Instrutor i2 = new Instrutor(null, "Osmar", "HTML", "webmaster@gmail.com");
		Instrutor i3 = new Instrutor(null, "Lucas Assis", "Carros Autônomos", "lucasassis@gmail.com");

		instrutorRepository.saveAll(Arrays.asList(i1, i2, i3));

	}
}
