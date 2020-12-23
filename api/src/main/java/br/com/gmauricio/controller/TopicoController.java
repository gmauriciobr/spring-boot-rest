package br.com.gmauricio.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmauricio.dto.DetalheTopicoDTO;
import br.com.gmauricio.dto.TopicoDTO;
import br.com.gmauricio.form.AtualizarTopicoForm;
import br.com.gmauricio.form.CadastroTopicoForm;
import br.com.gmauricio.modelo.Topico;
import br.com.gmauricio.respository.CursoRepository;
import br.com.gmauricio.service.TopicoService;

@RestController
@RequestMapping("/topico")
public class TopicoController {

	@Autowired
	private TopicoService topicoService;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@PostMapping
	@CacheEvict(value = "listaTopico", allEntries = true)
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid CadastroTopicoForm topicoForm, UriComponentsBuilder uriBuilder){
		Topico topico = topicoForm.parse(cursoRepository);
		topicoService.getTopicoRepository().save(topico);
		URI uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@GetMapping
	@Cacheable("listaTopico")
	public Page<TopicoDTO> listaTopico(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 5) Pageable page) {
		Page<Topico> topicos = topicoService.getTopicoRepository().findAll(page);
		return TopicoDTO.parse(topicos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheTopicoDTO> buscaTopico(@PathVariable Long id){
		Optional<Topico> topico = topicoService.getTopicoRepository().findById(id);
		return topico.isPresent() ? ResponseEntity.ok(new DetalheTopicoDTO(topico.get())) 
				: ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarTopicoForm atualizarTopicoForm) {
		Optional<Topico> optional = topicoService.getTopicoRepository().findById(id);
		if(optional.isPresent()) {
			Topico topico = topicoService.atualizar(id, atualizarTopicoForm);
			return ResponseEntity.ok(new TopicoDTO(topico));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Topico> optional = topicoService.getTopicoRepository().findById(id);
		if(optional.isPresent()) {
			topicoService.getTopicoRepository().delete(optional.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
