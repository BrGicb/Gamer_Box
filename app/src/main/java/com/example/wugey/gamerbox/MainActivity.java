package com.example.wugey.gamerbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public static int searchedstore;//用于初始化spinner


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button psbtn = findViewById(R.id.PS_button);
        Button stmbtn = findViewById(R.id.ST_button);
        Button ubibtn = findViewById(R.id.UB_button);


        psbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchPage.class));
                //↑页面跳转
                searchedstore = 0;
            }
        });

        stmbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchPage.class));
                //↑页面跳转
                searchedstore = 1;
            }
        });

        ubibtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchPage.class));
                //↑页面跳转
                searchedstore = 2;
            }
        });

    }


}
