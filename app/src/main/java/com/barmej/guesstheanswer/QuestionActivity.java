package com.barmej.guesstheanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private String currentQuestion,currentAnswerDetail;
    private boolean currentAnswer;

    private String[] QUESTIONS;

    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            true,
            true
    };

    private  String[] ANSWERS_DETAILS ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("app language",MODE_PRIVATE);
        String appLanguage = sharedPreferences.getString("language","");
        if (!appLanguage.equals("")){
            LocaleHelper.setLocale(this,appLanguage);
        }

        setContentView(R.layout.questionactivity);

        textViewQuestion = findViewById(R.id.textViewQuestionDetail);

        QUESTIONS= getResources().getStringArray(R.array.questions);

        ANSWERS_DETAILS=getResources().getStringArray(R.array.answersDetails);

        newQuestion();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuChangeLanguage){
         showLanguageDialog();
         return true;
        }else return super.onOptionsItemSelected(item);
    }
    private void showLanguageDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(R.string.change_lang_text).setItems(R.array.Languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    String language= "ar";
                    switch (which){
                        case 0: language="ar";break;
                        case 1:language="en";break;
                        case 2:language="fr";break;
                    }
                    saveLanguage(language);
                    LocaleHelper.setLocale(QuestionActivity.this,language);
                    Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                    }
                }).create();
        alertDialog.show();
    }

    private void saveLanguage(String savedLanguage){
        SharedPreferences sharedPreferences = getSharedPreferences("app language",MODE_PRIVATE);
        sharedPreferences.edit().putString("language",savedLanguage).apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    private void newQuestion()
    {
        Random random= new Random();
       int questionsIdInfo= random.nextInt(QUESTIONS.length);
       currentQuestion=QUESTIONS[questionsIdInfo];
       currentAnswer=ANSWERS[questionsIdInfo];
       currentAnswerDetail=ANSWERS_DETAILS[questionsIdInfo];
       textViewQuestion.setText(currentQuestion);
    }

    public void showQuestion(View view)
    {
       newQuestion();
    }


     public void checkAnswerTrue(View view)
    {
        if (currentAnswer==true) {
            answerResult("True");
        }
        else {
            answerResult("False");
        }



    }


    public void checkAnswerFalse(View view) {

        if (currentAnswer==false) {
            answerResult("True");

        } else {
            answerResult("False");
        }

    }


    private void answerResult(String TOrF)
    {
        switch (TOrF) {
            case "True":
                         Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
                         newQuestion();
                         break;
            case "False":
                          Intent intent = new Intent(QuestionActivity.this,AnswerActivity.class);
                          Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
                          intent.putExtra("questionAnswer",currentAnswerDetail);
                          startActivity(intent);
                          break;

        }

    }


    public void onClickShareQuestion(View view) {
        Intent intent =new Intent(QuestionActivity.this,share.class);
        intent.putExtra("shared question",currentQuestion);
        startActivity(intent);
    }



}