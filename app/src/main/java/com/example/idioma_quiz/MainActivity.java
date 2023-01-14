package com.example.idioma_quiz;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.idioma_quiz.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.idioma_quiz.databinding.ContentMainBinding;
import com.example.idioma_quiz.quizsecond.CardAdapter;
import com.example.idioma_quiz.quizsecond.models.Word;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CardStackListener{

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    private int currentWord = 0;
    private int score = 0;
    List<Word> words = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        final CardStackView stackView = findViewById(R.id.card_view);

        // Configuring Layout Manager
        CardStackLayoutManager layoutManager = new CardStackLayoutManager(this, this);
        layoutManager.setStackFrom(StackFrom.Top);
        layoutManager.setVisibleCount(3);

        //Rewind feature
        RewindAnimationSetting setting = new RewindAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .build();
        layoutManager.setRewindAnimationSetting(setting);

//        stackView.setLayoutManager(layoutManager);
//        stackView.setAdapter(new CardAdapter(initData()));
//
//        findViewById(R.id.action_home).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stackView.rewind();
//            }
//        });
        findViewById(R.id.quiz_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainFragment.class);
                startActivity(i);
            }
        });
    }

    //function to add placeholder data
    private List<Word> initData() {
        words.add(new Word("Apple", "Manzana", Boolean.TRUE, "apple"));
        words.add(new Word("Book", "Libro", Boolean.TRUE, "book"));
        words.add(new Word("Helmet", "Traje", "Casco", Boolean.FALSE, "helmet"));
        words.add(new Word("Lamp", "Lámpara", Boolean.TRUE, "lamp"));
        words.add(new Word("Library", "Zorro", "Biblioteca", Boolean.FALSE, "library"));
        words.add(new Word("Pool", "Piscina", Boolean.TRUE, "pool"));
        words.add(new Word("Skunk", "Correo", "Mofeta", Boolean.FALSE, "skunks"));
        words.add(new Word("Sofa", "Sofá", Boolean.TRUE, "sofa"));
        words.add(new Word("Watch", "Reloj de pulsera", Boolean.TRUE, "watch"));
        words.add(new Word("Wine", "Tequila", "Vino", Boolean.FALSE, "wine"));
        return words;
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }
    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Right && words.get(currentWord).getCorrect()==Boolean.FALSE){
            alertView("Помилочка!!", "Не вгадав 😩.\nПравильна відповідь - " + words.get(currentWord).getTrueTranslation(), false);
        } else if (direction == Direction.Left && words.get(currentWord).getCorrect()==Boolean.TRUE){
            alertView("Помилочка!!", "Не вгадав 😩. Це було правильно!", false);
        } else {
            alertView("Правильно!", "✅✅✅", true);
            score++;
        }
        final TextView textViewToChange = (TextView) findViewById(R.id.detailsTextView);
        textViewToChange.setText("You answered " + score + "/10 correct 👍🏻");
        currentWord++;
    }

    @Override
    public void onCardRewound() {
        currentWord--;
    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }

    private void alertView(String title, String message, Boolean countdown ) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        if (countdown) {
            new CountDownTimer(1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    System.out.println("починаємо");
                }

                public void onFinish() {
                    alertDialog.dismiss();
                }
            }.start();
        }
    }

    private void gameOver() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        Dialog d = adb.setView(new View(this)).create();
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        d.show();
        d.getWindow().setAttributes(lp);
    }
    public static boolean checkAndRequestPermissions(final Activity context) {
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}