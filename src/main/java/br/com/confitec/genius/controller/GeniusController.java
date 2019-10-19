package br.com.confitec.genius.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.confitec.genius.Service.GeniusService;
import br.com.confitec.genius.dto.MusicaDTO;

@RestController
public class GeniusController {

	@Autowired
	private GeniusService service;

	@RequestMapping(value = "/getMusicasByArtista", method = RequestMethod.GET)
	public List<MusicaDTO> getMusicasByArtista(@RequestParam("nomeArtista") String nome) {
		return service.getMusciasByArtista(nome);
	}

}
