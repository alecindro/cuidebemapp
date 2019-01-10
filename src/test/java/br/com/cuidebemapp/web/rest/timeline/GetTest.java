package br.com.cuidebemapp.web.rest.timeline;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.web.rest.RestBase;
import br.com.cuidebemapp.web.rest.dto.Timeline;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetTest extends RestBase{

	private String baseURL = "/api/timeline";

	@Test
	public void getTimeline() throws Exception {
		 List<Timeline> timelines = get(baseURL,"idpaciente",1L,Timeline.class);
		 for(Timeline timeline : timelines) {
			 System.out.println(timeline.toString());
		 }
	}
}
