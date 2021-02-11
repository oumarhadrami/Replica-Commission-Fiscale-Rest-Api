package com.mohamed.hadramy.springbootapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="news")
@Getter
@Setter
@NoArgsConstructor
public class News {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="news_title")
	private String news_title;
	
	@Column(name="news_info")
	private String news_info;
	
	@Column(name="news_date")
	private String news_date;
	
	@Column(name="news_video")
	private String news_video;
	
	@Column(name="news_image")
	private String news_image;

    @Column(name="news_title_ar")
	private String news_title_ar;
	
	@Column(name="news_info_ar")
	private String news_info_ar;
	
	@Column(name="news_date_ar")
	private String news_date_ar;

	public News(long id, String news_title, String news_info, String news_date, String news_video, String news_image,
			String news_title_ar, String news_info_ar, String news_date_ar) {
		this.id = id;
		this.news_title = news_title;
		this.news_info = news_info;
		this.news_date = news_date;
		this.news_video = news_video;
		this.news_image = news_image;
		this.news_title_ar = news_title_ar;
		this.news_info_ar = news_info_ar;
		this.news_date_ar = news_date_ar;
	}
	
}
