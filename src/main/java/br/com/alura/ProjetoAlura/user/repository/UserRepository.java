package br.com.alura.ProjetoAlura.user.repository;

import br.com.alura.ProjetoAlura.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
