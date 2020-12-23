package br.com.gmauricio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmauricio.config.security.TokenService;
import br.com.gmauricio.dto.TokenDTO;
import br.com.gmauricio.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod", "test"})
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginForm loginForm){
		try {
			UsernamePasswordAuthenticationToken user = loginForm.parse();
			Authentication authentication = authenticationManager.authenticate(user);
			String token = tokenService.gerarToken(authentication);
			System.out.println(token);;
			return ResponseEntity.ok().body(new TokenDTO(token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
