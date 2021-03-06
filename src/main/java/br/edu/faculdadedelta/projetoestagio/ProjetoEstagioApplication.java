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
import br.edu.faculdadedelta.projetoestagio.domain.Matricula;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;
import br.edu.faculdadedelta.projetoestagio.domain.enums.Perfil;
import br.edu.faculdadedelta.projetoestagio.domain.enums.StatusPagamento;
import br.edu.faculdadedelta.projetoestagio.domain.enums.StatusPresenca;
import br.edu.faculdadedelta.projetoestagio.repositories.AlunoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.CursoRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.InstrutorRepository;
import br.edu.faculdadedelta.projetoestagio.repositories.MatriculaRepository;
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
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEstagioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(null, "admin", new BCryptPasswordEncoder().encode("admin"));
		
		Usuario userAluno = new Usuario(null, "bruno@gmail.com", new BCryptPasswordEncoder().encode("12345"));
		Usuario userAluno2 = new Usuario(null, "iara@gmail.com", new BCryptPasswordEncoder().encode("12345"));
		Usuario userAluno3 = new Usuario(null, "samuel@gmail.com", new BCryptPasswordEncoder().encode("12345"));
		Usuario userAluno4 = new Usuario(null, "lucianna@gmail.com", new BCryptPasswordEncoder().encode("12345"));
		Usuario userAluno5 = new Usuario(null, "walter@gmail.com", new BCryptPasswordEncoder().encode("12345"));
		Usuario userAluno6 = new Usuario(null, "jonas@gmail.com", new BCryptPasswordEncoder().encode("12345"));
		

		usuario.addPerfil(Perfil.ADMIN);
		
		userAluno.addPerfil(Perfil.ALUNO);
		userAluno2.addPerfil(Perfil.ALUNO);
		userAluno3.addPerfil(Perfil.ALUNO);
		userAluno4.addPerfil(Perfil.ALUNO);
		userAluno5.addPerfil(Perfil.ALUNO);
		userAluno6.addPerfil(Perfil.ALUNO);

		usuarioRepository.saveAll(Arrays.asList(usuario,userAluno,userAluno2,userAluno3,userAluno4,userAluno5,userAluno6));

		Instrutor i1 = new Instrutor(null, "Paulo Roberto", "Spring MVC", "paulonill@gmail.com");
		Instrutor i2 = new Instrutor(null, "Osmar", "HTML", "webmaster@gmail.com");
		Instrutor i3 = new Instrutor(null, "Lucas Assis", "Carros Autônomos", "lucasassis@gmail.com");

		instrutorRepository.saveAll(Arrays.asList(i1, i2, i3));
		
		Aluno aluno = new Aluno(null, "Bruno", "Rezende" ,"bruno@gmail.com", "010101", "66633322211", new Date(), userAluno);
		Aluno aluno2 = new Aluno(null, "Iara", "Neves Esteves" ,"iara@gmail.com", "020202", "66633322210", new Date(), userAluno2);
		Aluno aluno3 = new Aluno(null, "Samuel", "Freitas" ,"samuel@gmail.com", "030303", "66633322212", new Date(), userAluno3);
		Aluno aluno4 = new Aluno(null, "Lucianna", "Xavier" ,"lucianna@gmail.com", "040404", "66633322213", new Date(), userAluno4);
		Aluno aluno5 = new Aluno(null, "Walter", "Paulo" ,"walter@gmail.com", "050505", "66633322214", new Date(), userAluno5);
		Aluno aluno6 = new Aluno(null, "Jonas", "Souza" ,"jonas@gmail.com", "060606", "66633322215", new Date(), userAluno6);
		
		alunoRepository.saveAll(Arrays.asList(aluno,aluno2,aluno3,aluno4,aluno5,aluno6));
		
		Curso c1 = new Curso(null, "Java Web", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i1);
		Curso c2 = new Curso(null, "Data Science", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i2);
		Curso c3 = new Curso(null, "Javascript", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i3);
		Curso c4 = new Curso(null, "Python", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i1);
		Curso c5 = new Curso(null, "Html e Css", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i2);
		Curso c6 = new Curso(null, "Inteligência Artificial", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i3);
		Curso c7 = new Curso(null, "Php", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i1);
		Curso c8 = new Curso(null, "Scrum", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 20, i2);
		Curso c9 = new Curso(null, "Spring Boot", "Qualquer coisa", "Delta", new Date(), "15:00", "3 horas", 50.0, 2, i3);
		
		i1.getCursos().addAll(Arrays.asList(c1,c4,c7));
		i2.getCursos().addAll(Arrays.asList(c2,c5,c8));
		i3.getCursos().addAll(Arrays.asList(c3,c6,c9));
		
		cursoRepository.saveAll(Arrays.asList(c1, c2, c3,c4,c5,c6,c7,c8,c9));
		
		Matricula m1 = new Matricula(aluno2, c1, new Date(), StatusPagamento.AGUARDANDO, StatusPresenca.PRESENTE);
		
		matriculaRepository.save(m1);

	}
}
