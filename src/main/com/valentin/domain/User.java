package com.valentin.domain;

public class User {
	
	//Attributes
    private Long id;
	private String nom;
	private String genre;
	private String birthday;

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
	private User(Builder builder) {
		this.id = builder.id;
		this.nom = builder.nom;
		this.genre = builder.genre;
		this.birthday = builder.birthday;
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


    /**
     * User Builder
     */
    public static class Builder {
        private Long id;
        private String nom;
        private String genre;
        private String birthday;

        public Builder(){
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withNom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder withBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
