package com.exemplo.sistemacarrousuario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class SistemaDeGestaoDeCarrosEUsuariosApplicationTests {
	
	private final String HELLO = "Hello!";
	
    @Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void mainApplicationTest() {
		SistemaDeGestaoDeCarrosEUsuariosApplication.main(new String[] {});
	}
	
	@Test
	@SuppressWarnings("unlikely-arg-type")
	public void helloTest() throws Exception {
        this.mockMvc.perform(get("/hello")).andExpect(status().isOk())
        	.andReturn().equals(HELLO);
	}

}
