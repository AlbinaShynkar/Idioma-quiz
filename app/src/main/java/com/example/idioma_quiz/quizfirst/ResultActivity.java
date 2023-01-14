package com.example.idioma_quiz.quizfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.idioma_quiz.MainActivity;
import com.example.idioma_quiz.R;

import com.example.idioma_quiz.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int totalQuestions = getIntent().getIntExtra(Constant.TOTAL_QUESTIONS, 0);
        int correctAnswers = getIntent().getIntExtra(Constant.CORRECT_ANSWERS,0);

        TextView tv_score = findViewById(R.id.tv_score);

        tv_score.setText(new StringBuilder().append("Your Score is ").append(correctAnswers).append(" out of ").append(totalQuestions).toString());

        Button btn_submit = findViewById(R.id.btn_finish);

        Intent intent =  new Intent(this, MainActivity.class );

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        Button btn_home = findViewById(R.id.action_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}