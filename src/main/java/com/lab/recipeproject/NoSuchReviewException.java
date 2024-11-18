package com.lab.recipeproject;

public class NoSuchReviewException extends Exception{
    public NoSuchReviewException(String message) {
        super(message);
    }

    public NoSuchReviewException() {
    }
}
