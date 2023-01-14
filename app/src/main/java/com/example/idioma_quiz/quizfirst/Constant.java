package com.example.idioma_quiz.quizfirst;

import com.example.idioma_quiz.R;
import com.example.idioma_quiz.quizfirst.Questions;

import java.util.ArrayList;

public class Constant {

    static final String TOTAL_QUESTIONS = "total_questions";
    static final String CORRECT_ANSWERS = "correct_answers";

    public static ArrayList<Questions> getQuestions() {
        ArrayList questionList = new ArrayList<Questions>();
        Questions que1 = new Questions(1, R.drawable.qone, "Idioma", "Bombilla", "Parizes", 2);
        Questions que2 = new Questions(1, R.drawable.qtwo, "Martillo", "Hamster", "Loempia", 1);
        Questions que3 = new Questions(1, R.drawable.qthree, "Libro", "Memo pad", "Arbol", 1);
        questionList.add(que1);
        questionList.add(que2);
        questionList.add(que3);

        return questionList;
    }



}

