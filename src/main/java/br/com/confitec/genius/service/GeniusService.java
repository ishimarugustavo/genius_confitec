package br.com.confitec.genius.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.confitec.genius.dto.GeniusResponseSearch;
import br.com.confitec.genius.dto.Hits;
import br.com.confitec.genius.dto.MusicaDTO;
import br.com.confitec.genius.dto.Songs;

@Service
public class GeniusService {

	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${api_access_toke}")
	private String accessToken;
	
	@Value("${api_url_search}")
	private String urlApiSearch;
	
	@Value("${api_url}")
	private String urlApi;
	
	private String url;
	
	public List<MusicaDTO> getMusciasByArtista(String nome) {
		url = Strings.EMPTY;
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
		
		if (retornoBody != null) {
			url = urlApi + "artists/" + retornoBody.getResponse().getHits()[0].getResult().getPrimary_artist().getId() + "/songs";
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("sort", "popularity")
					.queryParam("per_page", "10")
					.queryParam("page", "10");
			
			ResponseEntity<GeniusResponseSearch> retornoArtista = restTemplate.exchange(
					builder.toUriString(), 
					HttpMethod.GET,
					entity,
					new ParameterizedTypeReference<GeniusResponseSearch>() {});
			
			retornoBody = retornoArtista.getBody();
		}
		
		for (Songs songs : retornoBody.getResponse().getSongs()) {
			MusicaDTO music = new MusicaDTO();
			
			music.setNomeMusica(songs.getFull_title());
			music.setNomeArtista(songs.getPrimary_artist().getName());
			
			listaMusicas.add(music);
		}
		
		return listaMusicas;
	}
}
