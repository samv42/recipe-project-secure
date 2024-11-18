package com.lab.recipeproject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.persistence.*;
import javax.persistence.Id;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private CustomUserDetails user;

    @Column(nullable = false)
    private Integer minutesToMake;

    @Column(nullable = false)
    private Integer difficultyRating;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId", nullable = false, foreignKey = @ForeignKey)
    private Collection<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId", nullable = false, foreignKey = @ForeignKey)
    private Collection<Step> steps = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId", nullable = false, foreignKey = @ForeignKey)
    private Collection<Review> reviews;

    @Transient
    @JsonIgnore
    private URI locationURI;

   /* public void setDifficultyRating(Integer difficultyRating) throws IllegalStateException{
        if (difficultyRating < 0 || difficultyRating > 10) {
            throw new IllegalStateException("Difficulty rating must be between 1 and 10");
        }else {
            this.difficultyRating = difficultyRating;
        }
    }*/

    public void validate() throws IllegalStateException {
        if (ingredients.size() == 0) {
            throw new IllegalStateException("You have to have at least one ingredient for your recipe!");
        } else if (steps.size() == 0) {
            throw new IllegalStateException("You have to include at least one step for your recipe!");
        } else if (name == null) {
            throw new IllegalStateException("You have to have a name for your recipe!");
        } else if (user == null) {
            throw new IllegalStateException("You have to have a username for your recipe!");
        } else if (minutesToMake == null) {
            throw new IllegalStateException("You have to include how long your recipe takes to make!");
        } else if (difficultyRating == null) {
            throw new IllegalStateException("You have to include a difficulty rating for your recipe!");
        } else if (difficultyRating < 0 || difficultyRating > 10) {
            throw new IllegalStateException("Difficulty rating must be between 1 and 10");
        }
    }

    public String getAuthor() {
        return user.getUsername();
    }

    public void generateLocationURI() {
        try {
            locationURI = new URI(
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/recipes/")
                            .path(String.valueOf(id))
                            .toUriString());
        } catch (URISyntaxException e) {
            //Exception should stop here.
        }
    }
}
