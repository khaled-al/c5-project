package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class share extends AppCompatActivity {

    private EditText editTextShareTitle;
    public String questionShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        editTextShareTitle=findViewById(R.id.editTextShareQuestion);
         questionShare=getIntent().getStringExtra("shared question");

         SharedPreferences sharedPreferences = getSharedPreferences("app pref",MODE_PRIVATE);
         String questionTitle=sharedPreferences.getString("share title","");
         editTextShareTitle.setText(questionTitle);
    }


    public void shareWithFriend(View view) {
        String questionTitle=editTextShareTitle.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,questionTitle+"\n"+questionShare);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);

        SharedPreferences sharedPreferences= getSharedPreferences("app pref",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("share title",questionTitle);
        editor.apply();


    }
}