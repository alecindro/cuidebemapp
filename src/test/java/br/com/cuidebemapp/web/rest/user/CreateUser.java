package br.com.cuidebemapp.web.rest.user;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.domain.User;
import br.com.cuidebemapp.model.Usuario;
import br.com.cuidebemapp.security.AuthoritiesConstants;
import br.com.cuidebemapp.service.dto.UserDTO;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateUser extends RestBase {

	private String baseURL = "/sec/users";


	@Test
	public void teste() throws Exception {
		UserDTO usuarioDTO = new UserDTO();
		usuarioDTO.setLogin("userteste3");
		usuarioDTO.setFirstName("userTeste3");
		usuarioDTO.setLastName("sobrenome3");
		usuarioDTO.setEmail("alecindrocastilho3@gmail.com");
		usuarioDTO.setAuthorities(new HashSet<>());
		usuarioDTO.getAuthorities().add(AuthoritiesConstants.USER);
		createUser(usuarioDTO);
		//create(usuarioDTO);
	}
	
	public void createUser(UserDTO usuarioDTO) throws Exception {
		
		User user = post(baseURL,usuarioDTO, User.class);
		assertTrue(user.getId() != null);
	}
	
	private void create(UserDTO usuarioDTO) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNome(usuarioDTO.getFirstName());
		usuario.setEnabled(true);
		usuario =  post("/api/usuarios",usuario);
		assertTrue(usuario.getLogin() != null);
	}
	
}
