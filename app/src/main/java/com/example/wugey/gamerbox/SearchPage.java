//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑　　　　　　　　  Mythical Animal bless me that
//    ┃　　　┃   代码无BUG！               there is no bug!
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛



package com.example.wugey.gamerbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wugey on 2018/3/30.
 */



public class SearchPage extends AppCompatActivity {
    //spinner
    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;

    //文本框



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);


        //spinner控件的设置
        //第一步：添加一个下拉列表项的list

        list.add("PS store");
        list.add("Steam");
        list.add("Ubi store");
        mySpinner = (Spinner)findViewById(R.id.store);


        //第二步，为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);

        mySpinner.setSelection(MainActivity.searchedstore);//初始化spinner




    }







}