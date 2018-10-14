package br.edu.faculdadedelta.projetoestagio;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.faculdadedelta.projetoestagio.domain.Aluno;
import br.edu.faculdadedelta.projetoestagio.domain.Curso;
import br.edu.faculdadedelta.projetoestagio.domain.Instrutor;
import br.edu.faculdadedelta.projetoestagio.domain.Role;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.repositories.AlunoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.CursoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.InstrutorRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.UsuarioRepository;

@SpringBootApplication
public class ProjetoEstagioApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private InstrutorRepository instrutorRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEstagioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(null, "admin", new BCryptPasswordEncoder().encode("admin"));
		
		Usuario userAluno = new Usuario(null, "teste@gmail.com", new BCryptPasswordEncoder().encode("12345"));

		Role role = new Role("ROLE_ADMIN");
		usuario.getRoles().addAll(Arrays.asList(role));
		
		Role roleAluno = new Role("ROLE_ALUNO");
		userAluno.getRoles().addAll(Arrays.asList(roleAluno));

		usuarioRepository.saveAll(Arrays.asList(usuario,userAluno));

		Instrutor i1 = new Instrutor(null, "Paulo Roberto", "Spring MVC", "paulonill@gmail.com");
		Instrutor i2 = new Instrutor(null, "Osmar", "HTML", "webmaster@gmail.com");
		Instrutor i3 = new Instrutor(null, "Lucas Assis", "Carros Autônomos", "lucasassis@gmail.com");

		instrutorRepository.saveAll(Arrays.asList(i1, i2, i3));
		
		Aluno aluno = new Aluno(null, "Teste", "teste@gmail.com", "123456", "66633322211", new Date(), userAluno);
		
		alunoRepository.saveAll(Arrays.asList(aluno));
		
		Curso c1 = new Curso(null, "Teste", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i1);
		Curso c2 = new Curso(null, "Teste2", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i2);
		Curso c3 = new Curso(null, "Teste3", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i3);
		Curso c4 = new Curso(null, "Teste4", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i1);
		Curso c5 = new Curso(null, "Teste5", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i2);
		Curso c6 = new Curso(null, "Teste6", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i3);
		Curso c7 = new Curso(null, "Teste7", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i1);
		Curso c8 = new Curso(null, "Teste8", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i2);
		Curso c9 = new Curso(null, "Teste9", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i3);
		
		i1.getCursos().addAll(Arrays.asList(c1,c4,c7));
		i2.getCursos().addAll(Arrays.asList(c2,c5,c8));
		i3.getCursos().addAll(Arrays.asList(c3,c6,c9));
		
		cursoRepository.saveAll(Arrays.asList(c1, c2, c3,c4,c5,c6,c7,c8,c9));

	}
}
