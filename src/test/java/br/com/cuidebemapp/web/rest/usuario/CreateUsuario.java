package br.com.cuidebemapp.web.rest.usuario;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.uaa.model.User;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateUsuario extends RestBase {

	private String baseURL = "/api/usuarios";

	@Test
	public void teste() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail("alecindrocastilho@gmail.com");
		usuario.setEnabled(true);
		usuario.setGenero(true);
		usuario.setLogin("alecindrocastilho@gmail.com");
		usuario.setDatanascimento(new Date());
		usuario.setNome("alecindro");
		Usuario user = post(baseURL,usuario, Usuario.class);
		assertTrue(user != null);
	}
}
