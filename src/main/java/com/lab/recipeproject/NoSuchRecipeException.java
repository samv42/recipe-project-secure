package com.lab.recipeproject;

public class NoSuchRecipeException extends Exception{
    public NoSuchRecipeException(String message) {
        super(message);
    }

    public NoSuchRecipeException() {
    }
}
