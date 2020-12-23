package br.com.gmauricio.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gmauricio.modelo.Usuario;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuerarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		if(valido) this.autenticarClientes(token);
		
		filterChain.doFilter(request, response);
	}

	private void autenticarClientes(String token) {
		Usuario usuario = this.tokenService.getUsuarioByToken(token);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuerarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token != null && token.trim() != "" && token.startsWith("Bearer ")) {
			return token.substring(7, token.length());
		}
		return null;
	}

}
