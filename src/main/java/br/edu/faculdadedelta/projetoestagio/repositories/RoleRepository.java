package br.edu.faculdadedelta.projetoestagio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.faculdadedelta.projetoestagio.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
