package br.com.gmauricio.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gmauricio.form.AtualizarTopicoForm;
import br.com.gmauricio.modelo.Topico;
import br.com.gmauricio.respository.TopicoRepository;

@Service
@Transactional
public class TopicoServiceImpl implements TopicoService {
	
	@Autowired
	public TopicoRepository topicoRepository;
	
	@Override
	public TopicoRepository getTopicoRepository() {
		return this.topicoRepository;
	}
	
	public Topico atualizar(Long id, AtualizarTopicoForm atualizarTopicoForm) {
		Topico topico = this.topicoRepository.getOne(id);
		BeanUtils.copyProperties(atualizarTopicoForm, topico);
		return topico;
	}

}
