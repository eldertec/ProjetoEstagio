package br.edu.faculdadedelta.projetoestagio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.faculdadedelta.projetoestagio.domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

}
