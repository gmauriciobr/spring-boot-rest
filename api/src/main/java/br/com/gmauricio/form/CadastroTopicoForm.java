package br.com.gmauricio.form;

import javax.validation.constraints.NotEmpty;

import br.com.gmauricio.modelo.Topico;
import br.com.gmauricio.respository.CursoRepository;

public class CadastroTopicoForm {
	
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String mensagem;
	@NotEmpty
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public Topico parse(CursoRepository cursoRepository) {
		return new Topico(titulo, mensagem, cursoRepository.findByNome(nomeCurso));
	}

}
