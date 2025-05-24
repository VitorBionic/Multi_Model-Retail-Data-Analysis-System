package com.vitorbionic.model.mongo;

import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private Long filmId;
    private Long clientId;
    private String comment;
    private Double evaluation;
    @Field("createdAt")
    private Instant commentInstant;
    
    public Comment() {}

    public Comment(String id, Long filmId, Long clientId, String comment, Double evaluation, Instant commentInstant) {
        this.id = id;
        this.filmId = filmId;
        this.clientId = clientId;
        this.comment = comment;
        this.evaluation = evaluation;
        this.commentInstant = commentInstant;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public Instant getCommentInstant() {
        return commentInstant;
    }

    public void setCommentInstant(Instant commentInstant) {
        this.commentInstant = commentInstant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, comment, commentInstant, evaluation, filmId, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        return Objects.equals(clientId, other.clientId) && Objects.equals(comment, other.comment)
                && Objects.equals(commentInstant, other.commentInstant) && Objects.equals(evaluation, other.evaluation)
                && Objects.equals(filmId, other.filmId) && Objects.equals(id, other.id);
    }
    
}
