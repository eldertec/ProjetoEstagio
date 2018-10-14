package br.edu.faculdadedelta.projetoestagio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.faculdadedelta.projetoestagio.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByLogin(String login);
	
	@Query("select u from Usuario u where u.login = :login and u.senha = :senha")
	Usuario existUser(@Param("login") String login,@Param("senha") String senha);
	
}
