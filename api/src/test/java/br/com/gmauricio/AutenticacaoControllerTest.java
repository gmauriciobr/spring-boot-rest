package br.com.gmauricio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gmauricio.controller.AutenticacaoController;
import br.com.gmauricio.form.LoginForm;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private AutenticacaoController autenticacaoController;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(autenticacaoController).build();
	}

	@Test
	public void testeUsuarioInvalido() throws Exception{
		LoginForm loginForm = new LoginForm("invalido@email.com", "123");
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(loginForm))
				).andExpect(MockMvcResultMatchers.status().isBadRequest());
		;
	}
	
	@Test
	public void testeUsuarioValido() throws Exception{
		LoginForm loginForm = new LoginForm("aluno@email.com", "123");
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(loginForm))
				).andExpect(MockMvcResultMatchers.status().isOk());
		;
	}

}
