package com.vitorbionic.model.objectdb;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Film {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String gender;
    private String director;
    private int duration;
    
    public Film() {}
    
    public Film(Long id, String title, String gender, String director, int duration) {
        this.id = id;
        this.title = title;
        this.gender = gender;
        this.director = director;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, duration, gender, id, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Film other = (Film) obj;
        return Objects.equals(director, other.director) && duration == other.duration
                && Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
                && Objects.equals(title, other.title);
    }
    
}
