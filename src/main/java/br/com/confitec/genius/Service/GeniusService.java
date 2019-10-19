package br.com.confitec.genius.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.confitec.genius.dto.GeniusResponseSearch;
import br.com.confitec.genius.dto.Hits;
import br.com.confitec.genius.dto.MusicaDTO;

@Service
public class GeniusService {

	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${api_access_toke}")
	private String accessToken;
	
	@Value("${api_url_search}")
	private String urlApiSearch;
	
	public List<MusicaDTO> getMusciasByArtista(String nome) {
		List<MusicaDTO> listaMusicas = new ArrayList<MusicaDTO>();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + accessToken);
		
		HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
		
		ResponseEntity<GeniusResponseSearch> retorno = restTemplate.exchange(
				urlApiSearch + "?q=" + nome, 
				HttpMethod.GET, 
				entity,
				new ParameterizedTypeReference<GeniusResponseSearch>() {});
		
		GeniusResponseSearch retornoBody = new GeniusResponseSearch();
		retornoBody = retorno.getBody();
		
		for (Hits hits : retornoBody.getResponse().getHits()) {
			MusicaDTO music = new MusicaDTO();
			
			music.setNomeMusica(hits.getResult().getFull_title());
			music.setNomeArtista(hits.getResult().getPrimary_artist().getName());
			
			listaMusicas.add(music);
		}
		
		return listaMusicas;
	}
}
