package br.com.gmauricio.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@NotEmpty
	private String email;
	@NotEmpty
	private String senha;
	
	public LoginForm() {
		
	}
	
	public LoginForm(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", senha=" + senha + "]";
	}
	public UsernamePasswordAuthenticationToken parse() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
	
}
