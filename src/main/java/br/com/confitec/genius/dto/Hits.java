package br.com.confitec.genius.dto;

import lombok.Data;

@Data
public class Hits {

	private Result result;

    private String[] highlights;

    private String index;

    private String type;
	
}
