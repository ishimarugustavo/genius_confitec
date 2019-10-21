package br.com.confitec.genius.dto;

import lombok.Data;

@Data
public class Response {
	
	private Hits[] hits;
	
	private Songs[] songs;

}
