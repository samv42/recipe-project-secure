package com.lab.recipeproject;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private int stepNumber;

    @NotNull
    private String description;
}
