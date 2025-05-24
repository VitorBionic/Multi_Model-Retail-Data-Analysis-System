package com.vitorbionic.model.mongo;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "film_images")
public class FilmImage {

    @Id
    private String id;
    private Long filmId;
    private String imageUrl;
    private String description;
    
    public FilmImage() {}

    public FilmImage(String id, Long filmId, String imageUrl, String description) {
        this.id = id;
        this.filmId = filmId;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, filmId, id, imageUrl);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FilmImage other = (FilmImage) obj;
        return Objects.equals(description, other.description) && Objects.equals(filmId, other.filmId)
                && Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl);
    }
    
}
