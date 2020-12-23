package br.com.gmauricio.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.gmauricio.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<UserDetails> findByEmail(String username);

}
