package br.com.cuidebemapp.web.rest.responsavelphoto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cuidebemapp.CuidebemappApp;
import br.com.cuidebemapp.model.Responsavel;
import br.com.cuidebemapp.model.ResponsavelPhoto;
import br.com.cuidebemapp.web.rest.RestBase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuidebemappApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ResponsavelPhotoTest  extends RestBase {

	private String baseURL = "/api/responsavelphotos/";
	
	@Test
	public void teste() throws Exception {
		ResponsavelPhoto rp = new ResponsavelPhoto();
		Responsavel responsavel = new Responsavel(1L);
		rp.setResponsavel(responsavel);
		
		String photo = encoder("/home/alecindro/Pictures/dogok.jpeg");
		Base64.getDecoder().decode(photo);
		System.out.println("Photo Upload: "+ photo);
		rp.setPhoto(photo);
		ResponsavelPhoto result = post(baseURL,rp);
		System.out.println("ID rp " +result.getIdresponsavel());
	}
	

	public static String encoder(String imagePath) {
		String base64Image = "";
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = new String(Base64.getEncoder().encode(imageData),"UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}

}
