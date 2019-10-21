package br.com.confitec.genius.dto;

import lombok.Data;

@Data
public class Songs {
	
	private String lyrics_owner_id;

    private String annotation_count;

    private String song_art_image_url;

    private Primary_artist primary_artist;

    private String title;

    private String api_path;

    private String url;

    private String path;

    private String song_art_image_thumbnail_url;

    private String full_title;

    private Stats stats;

    private String title_with_featured;

    private String id;

    private String header_image_thumbnail_url;

    private String pyongs_count;

    private String header_image_url;

    private String lyrics_state;

}
