package br.com.gmauricio.config.security;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gmauricio.modelo.Usuario;
import br.com.gmauricio.respository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${jwt.chave}")
	private String chave;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date expiracao = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
		
		return Jwts.builder()
				.setIssuer("API")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(expiracao)
				.signWith(SignatureAlgorithm.HS256, chave)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public Usuario getUsuarioByToken(String token) {
		Claims body = Jwts.parser().setSigningKey(chave).parseClaimsJws(token).getBody();
		Usuario usuario = this.usuarioRepository.findById(Long.parseLong(body.getSubject())).get();
		return usuario;
	}

}
