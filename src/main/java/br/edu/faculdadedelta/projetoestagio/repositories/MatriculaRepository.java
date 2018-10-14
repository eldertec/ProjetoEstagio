package br.edu.faculdadedelta.projetoestagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.faculdadedelta.projetoestagio.domain.Aluno;
import br.edu.faculdadedelta.projetoestagio.domain.Curso;
import br.edu.faculdadedelta.projetoestagio.domain.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

	Matricula findByIdAluno(Aluno aluno);
	
	List<Matricula> findByIdCurso(Curso curso);
}
