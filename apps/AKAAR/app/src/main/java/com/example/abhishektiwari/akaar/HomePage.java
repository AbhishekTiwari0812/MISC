package com.example.abhishektiwari.akaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class HomePage extends ActionBarActivity implements View.OnClickListener{

    ActionBar bar = null;       //to change title and make back button
    private Button comStat;        //for the activity complete_statistics
    private Button tdStat;          //for the activity today_statistics
    private Button StudentList;     //to see the whole list of students and their performance



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeButtonsAndStuff();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.student_list:
                Intent DisplayList = new Intent(this, StudentList.class);
                startActivity(DisplayList);
                break;
            case R.id.complete_stat:
                Intent CompleteStat = new Intent(getApplicationContext(), CompleteStat.class);
                startActivity(CompleteStat);
                break;
            case R.id.today_stat:
                Intent TodayStat = new Intent(this, TodayStat.class);
                startActivity(TodayStat);
                break;

        }
    }

    private void InitializeButtonsAndStuff() {
        comStat = (Button) findViewById(R.id.complete_stat);
        comStat.setOnClickListener(HomePage.this);
        tdStat = (Button) findViewById(R.id.today_stat);
        tdStat.setOnClickListener(HomePage.this);
        StudentList = (Button) findViewById(R.id.student_list);
        StudentList.setOnClickListener(HomePage.this);
        bar = getSupportActionBar();

    }

}
