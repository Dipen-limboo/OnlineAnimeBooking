package com.springbootAnmte.animte.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="animes")
public class Anime {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "anime_name", nullable=false)
	@NotBlank(message ="Insert anime name")
	private String animeName;
	
	@Column(name = "writer_name", nullable=false)
	@NotBlank(message ="Insert Writer Name")
	private String animeWriter;
	
	@Column(name="anime_category", nullable=false)
	@NotBlank(message ="Add Category")
	private String animeCategory;
	
	@Column(name="anime_trailer")
	private String animeTrailer;
	
	@Column(name="anime_story", nullable=false)
	@NotBlank(message="Write short story of an anime..")
	private String animeStory;
	
	@Column(name ="anime_video", nullable=false)
	@NotBlank(message="Add the video")
	private String animeVideo;
	
	@Column(name="anime_image")
    @NotBlank(message ="Anime image is required!!")
    private String animeImage;

	public Anime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anime(@NotBlank(message = "Insert anime name") String animeName,
			@NotBlank(message = "Insert Writer Name") String animeWriter,
			@NotBlank(message = "Add Category") String animeCategory, String animeTrailer,
			@NotBlank(message = "Write short story of an anime..") String animeStory,
			@NotBlank(message = "Add the video") String animeVideo,
			@NotBlank(message = "Anime image is required!!") String animeImage) {
		super();
		this.animeName = animeName;
		this.animeWriter = animeWriter;
		this.animeCategory = animeCategory;
		this.animeTrailer = animeTrailer;
		this.animeStory = animeStory;
		this.animeVideo = animeVideo;
		this.animeImage = animeImage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnimeName() {
		return animeName;
	}

	public void setAnimeName(String animeName) {
		this.animeName = animeName;
	}

	public String getAnimeWriter() {
		return animeWriter;
	}

	public void setAnimeWriter(String animeWriter) {
		this.animeWriter = animeWriter;
	}

	public String getAnimeCategory() {
		return animeCategory;
	}

	public void setAnimeCategory(String animeCategory) {
		this.animeCategory = animeCategory;
	}

	public String getAnimeTrailer() {
		return animeTrailer;
	}

	public void setAnimeTrailer(String animeTrailer) {
		this.animeTrailer = animeTrailer;
	}

	public String getAnimeStory() {
		return animeStory;
	}

	public void setAnimeStory(String animeStory) {
		this.animeStory = animeStory;
	}

	public String getAnimeVideo() {
		return animeVideo;
	}

	public void setAnimeVideo(String animeVideo) {
		this.animeVideo = animeVideo;
	}

	public String getAnimeImage() {
		return animeImage;
	}

	public void setAnimeImage(String animeImage) {
		this.animeImage = animeImage;
	}

}
