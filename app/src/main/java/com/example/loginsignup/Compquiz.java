package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class Compquiz extends AppCompatActivity {


    private ScrollView scroll;
RadioGroup radioGroupOne;

   private RadioGroup radioGroupTwo;
    private RadioGroup radioGroupThree;
    private  RadioGroup radioGroupFour;
    private RadioGroup radioGroupFive;
    private   RadioGroup radioGroupSix;
    private RadioGroup radioGroupSeven;
    private RadioGroup radioGroupEight;
    private RadioGroup radioGroupNine;
    private RadioGroup radioGroupTen;
    private RadioButton question1;
    private RadioButton question2;
    private RadioButton question3;
    private RadioButton question4;
    private RadioButton question5;
    private RadioButton question6;
    private RadioButton question7;
    private RadioButton question8;
    private RadioButton question9;
    private RadioButton question10;
    private int correctAnswers;

private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compquiz);
        scroll = (ScrollView) findViewById(R.id.Scroll);
        question1 = (RadioButton) findViewById(R.id.rb_CorrectAnswerOne);
        question2 = (RadioButton) findViewById(R.id.rb_CorrectAnswerTwo);
        question3 = (RadioButton) findViewById(R.id.rb_CorrectAnswerThree);
        question4 = (RadioButton) findViewById(R.id.rb_CorrectAnswerFour);
        question5 = (RadioButton) findViewById(R.id.rb_CorrectAnswerFive);

        question6 = (RadioButton) findViewById(R.id.rb_CorrectAnswerSix);

        question7 = (RadioButton) findViewById(R.id.rb_CorrectAnswerSeve);

        question8 = (RadioButton) findViewById(R.id.rb_CorrectAnswerEight);

        question9 = (RadioButton) findViewById(R.id.rb_CorrectAnswerNine);
        question10 = (RadioButton) findViewById(R.id.rb_CorrectAnswerTen);

        radioGroupOne = (RadioGroup) findViewById(R.id.radioGroupOne);
        radioGroupTwo = (RadioGroup) findViewById(R.id.radioGroupTwo);
        radioGroupThree = (RadioGroup) findViewById(R.id.radioGroupThree);
        radioGroupFour = (RadioGroup) findViewById(R.id.radioGroupFour);
        radioGroupFive = (RadioGroup) findViewById(R.id.radioGroupFive);
        radioGroupSix = (RadioGroup) findViewById(R.id.radioGroupSix);
        radioGroupSeven = (RadioGroup) findViewById(R.id.radioGroupSeven);
        radioGroupEight = (RadioGroup) findViewById(R.id.radioGroupEight);
        radioGroupNine= (RadioGroup) findViewById(R.id.radioGroupNine);
        radioGroupTen = (RadioGroup) findViewById(R.id.radioGroupTen);
    b=findViewById(R.id.buttona);
    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent intent=new Intent(Compquiz.this,AnswersComp.class);
        startActivity(intent);
        }
    });
    }

    public void SubmitResponse(View v) {


        String wrongAnswers = "Check this question and try again :-\n";
        if (question1.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 1\n";
        }
        if (question2.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 2\n";
        }
        if (question3.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question4.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question5.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question6.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question7.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question8.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question9.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (question10.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswers = wrongAnswers + "Question - 3\n";
        }
        if (correctAnswers == 10) {
            Toast.makeText(this, "Congrats, All Answers Correct  \n Thanks for attempting this Quiz ", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Compquiz.this, SuccessComp.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Correct Answers: " + correctAnswers + " /10\n" + wrongAnswers, Toast.LENGTH_LONG).show();
        }

        ResetQuiz(findViewById(R.id.all));

    }
    public void ResetQuiz(View v) {

        correctAnswers = 0;



        radioGroupOne.clearCheck();
        radioGroupTwo.clearCheck();
        radioGroupThree.clearCheck();
        radioGroupFour.clearCheck();
        radioGroupFive.clearCheck();
        radioGroupSix.clearCheck();
        radioGroupSeven.clearCheck();
        radioGroupEight.clearCheck();
        radioGroupNine.clearCheck();
        radioGroupTen.clearCheck();


        scroll.fullScroll(ScrollView.FOCUS_UP);
    }

    }
