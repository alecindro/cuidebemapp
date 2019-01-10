package br.com.cuidebemapp.web.rest.paciente;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		PacientePost.class,
		PacienteGet.class,
      //  PacienteCheckinTest.class,
      //  PacienteCheckoutTest.class,
        PacienteUpdate.class,
        PacienteDelete.class
})
@ActiveProfiles(profiles= {"test"})
public class AllTests {

	@Test
    public void contextLoads() {
    }
}
