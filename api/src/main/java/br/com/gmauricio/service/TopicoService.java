package br.com.gmauricio.service;

import br.com.gmauricio.form.AtualizarTopicoForm;
import br.com.gmauricio.modelo.Topico;
import br.com.gmauricio.respository.TopicoRepository;

public interface TopicoService {
	
	public TopicoRepository getTopicoRepository();
	
	public Topico atualizar(Long id, AtualizarTopicoForm atualizarTopicoForm);

		
}
