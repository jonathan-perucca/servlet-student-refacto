package com.valentin.domain;

public class User {
	
	//Attributes
    private Long id;
	private String nom;
	private String genre;
	private String birthday;

    public User() {

    }


    //Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
	
	//Constructor
	public User(Long id, String nom, String genre, String birthday) {
		this.id = id;
		this.nom = nom;
		this.genre = genre;
		this.birthday = birthday;
	}


    /** Equals and hashcode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
