package br.edu.faculdadedelta.projetoestagio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.faculdadedelta.projetoestagio.domain.Aluno;
import br.edu.faculdadedelta.projetoestagio.domain.Usuario;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	Aluno findByMatricula(String matricula);
	
	Aluno findByEmail(String email);
	
	Aluno findByUsuario(Usuario usuario);
	
}
