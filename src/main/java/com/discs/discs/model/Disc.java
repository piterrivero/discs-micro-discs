package com.discs.discs.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "disc")
public class Disc {

	@Id
	private long idDisc;
	
	@Indexed(unique = true)
	private String title;
	
	private int year;
	
	private String cover;
	
	private List<String> groups;
	
	public Disc() {
	}

	public long getIdDisc() {
		return idDisc;
	}
	public void setIdDisc(long idDisc) {
		this.idDisc = idDisc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
}
