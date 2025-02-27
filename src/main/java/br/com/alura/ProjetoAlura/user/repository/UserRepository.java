package br.com.alura.ProjetoAlura.user.repository;

import br.com.alura.ProjetoAlura.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

    Optional<User> findById(Long id);
}
