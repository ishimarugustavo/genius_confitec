package br.com.confitec.genius.genius;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.confitec.genius.dto.MusicaDTO;
import br.com.confitec.genius.service.GeniusService;

@SpringBootTest
@RunWith(SpringRunner.class)
class GeniusApplicationTests {

	@Autowired
	private GeniusService service;
	
	@Test
	void testeAPI() {
		List<MusicaDTO> response = new ArrayList<MusicaDTO>();
		
		response = service.getMusciasByArtista("Drake");
		
		assertThat(response.size() == 10);
	}

}
