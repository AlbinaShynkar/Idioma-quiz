package com.example.idioma_quiz.quizsecond.models;

public class Word {
    private String original;
    private String translation;
    private String trueTranslation;
    private Boolean isCorrect;
    private String imageName;

    public Word(String original, String translation, Boolean isCorrect, String imageName) {
        this.original = original;
        this.translation = translation;
        this.isCorrect = isCorrect;
        this.imageName = imageName;
    }

    public Word(String original, String translation, String trueTranslation, Boolean isCorrect, String imageName) {
        this.original = original;
        this.translation = translation;
        this.trueTranslation = trueTranslation;
        this.isCorrect = isCorrect;
        this.imageName = imageName;
    }

    public String getTrueTranslation(){
        return trueTranslation;
    }

    public String getOriginal() {
        return original;
    }

    public String getTranslation() {
        return translation;
    }

    public String getImageName() {
        return imageName;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }
}

