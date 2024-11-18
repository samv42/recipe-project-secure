package com.lab.recipeproject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Integer rating;

    @NotNull
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    @NotNull
    private CustomUserDetails user;

    public void setRating(Integer rating) throws IllegalStateException{
        if (rating <= 0 || rating > 10) {
            throw new IllegalStateException("Rating must be between 0 and 10.");
        }
        this.rating = rating;
    }

    public String getAuthor() {
        return user.getUsername();
    }

    public void validate() throws IllegalStateException {
        if (getAuthor() == null) {
            throw new IllegalStateException("You have to have a username for your review!");
        } else if (rating == null) {
            throw new IllegalStateException("You have to have a rating for your review!");
        } else if (description == null) {
            throw new IllegalStateException("You have to include a description for your review!");
        }
    }


}
