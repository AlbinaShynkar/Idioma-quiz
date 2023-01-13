package com.example.idioma_quiz;

import org.jetbrains.annotations.NotNull;



public final class Questions {

    private final int id;
    private final int image;
    @NotNull
    private final String optionOne;
    @NotNull
    private final String optionTwo;
    @NotNull
    private final String optionThree;
    private final int correctAnswer;

    public Questions(int id, int image, @NotNull String optionOne, @NotNull String optionTwo, @NotNull String optionThree, int correctAnswer) {
        this.id = id;
        this.image = image;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.correctAnswer = correctAnswer;
    }

    public final int getId() {
        return this.id;
    }

    public final int getImage() {
        return this.image;
    }

    @NotNull
    public final String getOptionOne() {
        return this.optionOne;
    }

    @NotNull
    public final String getOptionTwo() {
        return this.optionTwo;
    }

    @NotNull
    public final String getOptionThree() {
        return this.optionThree;
    }

    public final int getCorrectAnswer() {
        return this.correctAnswer;
    }


}
