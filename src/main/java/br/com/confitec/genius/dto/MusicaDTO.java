package br.com.confitec.genius.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicaDTO {

	private String nomeArtista;
	private String nomeMusica;
	
}
