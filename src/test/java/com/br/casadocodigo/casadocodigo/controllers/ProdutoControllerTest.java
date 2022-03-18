package com.br.casadocodigo.casadocodigo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
class ProdutoControllerTest {
	@Autowired
	WebApplicationContext applicationContext;
	//esse elemento não pode ser injetado, o spring não conhece
	MockMvc mockMvc;
	
	@Autowired
	private javax.servlet.Filter[] springSecurityFilterChain;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).addFilters(springSecurityFilterChain).build();
	}

	@Test
	void deveRetornarParaHomeComLivros() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/home.jsp"));
	}
	@Test
	void testeAdminUsandoProdutoForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
		.with(SecurityMockMvcRequestPostProcessors
						.user("user@casadocodigo.com.br").password("123456").roles("ADMs")))
				.andExpect(MockMvcResultMatchers.status().is(403));
	}

}
