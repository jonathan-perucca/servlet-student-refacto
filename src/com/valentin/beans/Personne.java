package com.valentin.beans;

import java.util.List;

import com.valentin.servlets.PersonneServlet;

public class Personne {
	
	//Attributes
	private int id;
	private String nom;
	private String genre;
	private String birthday;
	
	
	//Getters and Setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	//Constructor

	public Personne(int id, String nom, String genre, String birthday) {
		super();
		this.id = id;
		this.nom = nom;
		this.genre = genre;
		this.birthday = birthday;
	}
	public Personne() {
		// TODO Auto-generated constructor stub
	}
	
}
