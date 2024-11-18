package com.lab.recipeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;


@SpringBootApplication
@Profile("test")
public class RecipeMainTest implements CommandLineRunner {
    @Autowired
    RecipeRepo recipeRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("STARTING WITH TEST DATABASE SETUP");
        if (recipeRepo.findAll().isEmpty()) {

            Ingredient ingredient = Ingredient.builder().name("flour").state("dry").amount("2 cups").build();
            Step step1 = Step.builder().description("put flour in bowl").stepNumber(1).build();
            Step step2 = Step.builder().description("eat it?").stepNumber(2).build();

            Review review = Review.builder().description("tasted pretty bad").rating(2).username("idfk").build();

            Recipe recipe1 = Recipe.builder()
                    .name("test recipe")
                    .difficultyRating(10)
                    .minutesToMake(2)
                    .ingredients(Arrays.asList(ingredient))
                    .steps(Arrays.asList(step1, step2))
                    .reviews(Arrays.asList(review))
                    .username("bob")
                    .build();

            recipeRepo.save(recipe1);

            ingredient.setId(null);
            Recipe recipe2 = Recipe.builder()
                    .steps(Arrays.asList(Step.builder().description("test").build()))
                    .ingredients(Arrays.asList(Ingredient.builder().name("test ing").amount("1").state("dry").build()))
                    .name("another test recipe")
                    .difficultyRating(10)
                    .minutesToMake(2)
                    .username("Sally")
                    .build();
            recipeRepo.save(recipe2);

            Recipe recipe3 = Recipe.builder()
                    .steps(Arrays.asList(Step.builder().description("test 2").build()))
                    .ingredients(Arrays.asList(Ingredient.builder().name("test ing 2").amount("2").state("wet").build()))
                    .name("another another test recipe")
                    .difficultyRating(5)
                    .minutesToMake(2)
                    .username("Mark")
                    .build();

            recipeRepo.save(recipe3);

            Recipe recipe4 = Recipe.builder()
                    .name("chocolate and potato chips")
                    .difficultyRating(10)
                    .minutesToMake(1)
                    .ingredients(Arrays.asList(
                            Ingredient.builder().name("potato chips").amount("1 bag").build(),
                            Ingredient.builder().name("chocolate").amount("1 bar").build()))
                    .steps(Arrays.asList(
                            Step.builder().stepNumber(1).description("eat both items together").build()))
                    .reviews(Arrays.asList(
                            Review.builder().username("ben").rating(10).description("this stuff is so good").build()
                    ))
                    .username("Billy")
                    .build();

            recipeRepo.save(recipe4);
            System.out.println("FINISHED TEST DATABASE SETUP");
        }
    }
}
