package br.com.cuidebemapp.web.rest.agendadef;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Agendadef;
import br.com.cuidebemapp.web.rest.RestBase;
import br.com.foureffect.testrest2.model.PathVariable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AgendaDefUpdateTest extends RestBase{
	
	private String baseURL = "/api/agendadefs";
	
	@Test
	public void test1() throws Exception {
		PathVariable pv = new PathVariable();
		pv.addPath(5L);
		Agendadef  agendadef  = getOne(baseURL,pv,Agendadef.class);
		agendadef.setDias(append(agendadef.getDias(),7));
		Agendadef _agendadef = put(baseURL,agendadef);
		System.out.println(_agendadef);
	}
	
	static <T> T[] append(T[] arr, T element) {
	  final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}
	
	
	

}
