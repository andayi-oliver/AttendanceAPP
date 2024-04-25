package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {
    CardView cardView;
    CardView cardView1;
    CardView cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardView = findViewById(R.id.cardForStudent);
        cardView1 = findViewById(R.id.cardForunites);
        cardView2 = findViewById(R.id.cardAttendence);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardview = new Intent(Home.this,Profile.class);
                startActivity(cardview);
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardview = new Intent(Home.this,Units.class);
                startActivity(cardview);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardview = new Intent(Home.this,Attendance.class);
                startActivity(cardview);
            }
        });

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs" , Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"welcome"+username,Toast.LENGTH_SHORT).show();

        CardView carForExit = findViewById(R.id.cardForExit);//code for exit button
        carForExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, LoginActivity.class));
            }

        });
    }
    //public void onExitButtonClick(View view) {
      //  finish();
    //}
}
