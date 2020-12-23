package br.com.gmauricio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.gmauricio.modelo.Curso;
import br.com.gmauricio.respository.CursoRepository;

@DataJpaTest
@ActiveProfiles("teste")
public class RepositoryTest {

	@Autowired
	public CursoRepository cursoRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void buscaCursoNome() {
		String nomeCurso = "HTML 5";
		Curso curso = cursoRepository.findByNome(nomeCurso);
		assertNotNull(curso);
		assertEquals(nomeCurso, curso.getNome());
	}
	
}
