package br.com.gmauricio.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmauricio.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Curso findByNome(String nomeCurso);

}
