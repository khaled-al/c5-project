package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        answer=findViewById(R.id.editTextShareQuestion);
        String answerKey=getIntent().getStringExtra("questionAnswer");

        if(answerKey!=null)
        {
          answer.setText(answerKey);
        }
    }


    public void ReturnClick(View view) {
        finish();
    }
}