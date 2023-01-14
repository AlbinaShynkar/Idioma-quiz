package com.example.idioma_quiz.quizfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.idioma_quiz.R;

import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity implements View.OnClickListener {


    private int mCurrentPosition = 1;
    private ArrayList<Questions> mQuestionList = null;
    private int mSelectedOptionPosition = 0;
    private int mCorrectAnswers = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);


        mQuestionList = Constant.getQuestions();

        setQuestions();

        TextView tv_option_one = findViewById(R.id.tv_option_one);
        TextView tv_option_two = findViewById(R.id.tv_option_two);
        TextView tv_option_three = findViewById(R.id.tv_option_three);
        Button btn_submit = findViewById(R.id.btn_submit);

        tv_option_one.setOnClickListener(this);
        tv_option_two.setOnClickListener(this);
        tv_option_three.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    private void setQuestions(){

        Questions question = mQuestionList.get(mCurrentPosition - 1);

        defaultOptions();

        Button btn_submit = findViewById(R.id.btn_submit);

        if(mCurrentPosition == mQuestionList.size()){
            btn_submit.setText("FINISH");

        }else{
            btn_submit.setText("SUBMIT");
        }

        ProgressBar progressBar = findViewById(R.id.progressBar);

        TextView tv_progress = findViewById(R.id.tv_progress);

        TextView tv_option_one = findViewById(R.id.tv_option_one);
        TextView tv_option_two = findViewById(R.id.tv_option_two);
        TextView tv_option_three = findViewById(R.id.tv_option_three);

        ImageView iv_image = findViewById(R.id.iv_image);

        progressBar.setProgress(mCurrentPosition);

        tv_progress.setText(new StringBuilder().append(mCurrentPosition).append("/").append(progressBar.getMax()).toString());

        iv_image.setImageResource(question.getImage());

        tv_option_one.setText(question.getOptionOne());
        tv_option_two.setText(question.getOptionTwo());
        tv_option_three.setText(question.getOptionThree());

    }

    private void defaultOptions(){

        ArrayList<TextView> options = new ArrayList<TextView>();

        TextView tv_option_one = findViewById(R.id.tv_option_one);
        TextView tv_option_two = findViewById(R.id.tv_option_two);
        TextView tv_option_three = findViewById(R.id.tv_option_three);

        options.add(0,tv_option_one);
        options.add(1,tv_option_two);
        options.add(2,tv_option_three);

        for (TextView i: options){
            i.setTextColor(Color.parseColor("#7A8089"));
            i.setTypeface(Typeface.DEFAULT);
            i.setBackground(ContextCompat.getDrawable(this,R.drawable.default_option_border_bg));
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        TextView tv_option_one = findViewById(R.id.tv_option_one);
        TextView tv_option_two = findViewById(R.id.tv_option_two);
        TextView tv_option_three = findViewById(R.id.tv_option_three);
        Button btn_submit = findViewById(R.id.btn_submit);

        switch(view.getId()){

            case R.id.tv_option_one:
                selectedOptionView(tv_option_one,1);
                break;
            case R.id.tv_option_two:
                selectedOptionView(tv_option_two,2);
                break;
            case R.id.tv_option_three:
                selectedOptionView(tv_option_three,2);
                break;
            case R.id.btn_submit:
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition ++;

                     if(mCurrentPosition <= mQuestionList.size()){
                         setQuestions();
                     }else{
                         Intent intent = new Intent(this, ResultActivity.class);
                         intent.putExtra(Constant.CORRECT_ANSWERS, mCorrectAnswers);
                         intent.putExtra(Constant.TOTAL_QUESTIONS,mQuestionList.size());
                         startActivity(intent);
                     }

                }else {
                    Questions question =  mQuestionList.get(mCurrentPosition -  1);
                    if(question.getCorrectAnswer() != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg);
                    }else{
                        mCorrectAnswers ++;
                    }

                    answerView(question.getCorrectAnswer(), R.drawable.correct_option_border_bg);

                    if(mCurrentPosition == mQuestionList.size()){

                        btn_submit.setText("FINISH");

                    }else{

                        btn_submit.setText("GO TO NEXT QUESTION");
                    }
                    mSelectedOptionPosition = 0;
                }

        }
    }

    private void answerView(int answer, int drawableView){

        TextView tv_option_one = findViewById(R.id.tv_option_one);
        TextView tv_option_two = findViewById(R.id.tv_option_two);
        TextView tv_option_three = findViewById(R.id.tv_option_three);

        switch (answer){
            case 1:
                tv_option_one.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 2:
                tv_option_two.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 3:
                tv_option_three.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;

        }

    }

    private void selectedOptionView(TextView tv, int selectedOptionNumber){

        defaultOptions();
        mSelectedOptionPosition = selectedOptionNumber;

        tv.setTextColor(Color.parseColor("#7A8089"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg));

    }
}