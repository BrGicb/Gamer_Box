package com.example.wugey.gamerbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    public static int searchedstore;//用于初始化spinner


    private Button psbtn;
    private Button stmbtn;
    private Button ubibtn;


    @Override


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        psbtn = (Button) findViewById(R.id.PS_button);
        stmbtn = (Button) findViewById(R.id.ST_button);
        ubibtn = (Button) findViewById(R.id.UB_button);




        psbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDlg();

                //startActivity(new Intent(MainActivity.this, SearchPage.class));
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
                showAlertDlg();

                //startActivity(new Intent(MainActivity.this, SearchPage.class));
                //↑页面跳转
                searchedstore = 2;
            }
        });

    }

    private void showAlertDlg(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Sorry");
        dialog.setMessage("In development, it can't be used for the time being");
        dialog.setCancelable(false);
        dialog.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }



}
