package br.com.gmauricio.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmauricio.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
