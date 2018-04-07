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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;




import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * Created by wugey on 2018/3/30.
 */







public class SearchPage extends AppCompatActivity {
    public static String Game_name;
    //public static String game_json;
    public static String forappid = "";

    //spinner
    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    //文本框
    private EditText gamename;
    //private TextView test;//测试用Text


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        //文本框
        gamename = findViewById(R.id.SearchText);
        //测试用text
        //test = findViewById(R.id.test);


        //spinner控件的设置
        //第一步：添加一个下拉列表项的list

        list.add("PS store");
        list.add("Steam");
        list.add("Ubi store");
        mySpinner = (Spinner) findViewById(R.id.store);


        //第二步，为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        mySpinner.setSelection(MainActivity.searchedstore);//初始化spinner

        WebView webView = (WebView) findViewById(R.id.webView);

        webView.setVisibility(View.INVISIBLE);//隐藏webview
        webView.setWebViewClient(new WebViewClient() {
            // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("http://store.steampowered.com/app/")){
                    forappid = url;
                    startActivity(new Intent(SearchPage.this, information.class));
                }
                else view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url)  {
                webView.setVisibility(View.VISIBLE);
            }
        });

        /*webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealwithappid();
            }
        });
        if(forappid.contains("http://store.steampowered.com/app/"))dealwithappid();*/

        webView.getSettings().setJavaScriptEnabled(true);
    }







    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            /*隐藏软键盘*/
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if(inputMethodManager.isActive()){
                inputMethodManager.hideSoftInputFromWindow(SearchPage.this.getCurrentFocus().getWindowToken(), 0);
            }

            //获取输入的游戏名
            Game_name = gamename.getText().toString();

            //startActivity(new Intent(SearchPage.this, information.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            //测试用
            //test.setText(Game_name+" is the game you want to search");

            WebView webView = (WebView)findViewById(R.id.webView);
            webView.loadUrl("http://store.steampowered.com/search/?term="+Game_name);





            return true;
        }
        return super.dispatchKeyEvent(event);
    }





/*
    public void getappid() throws IOException {
        Elements keytext;
        URL url = new URL("http://store.steampowered.com/search/?term="+Game_name);
        InputStream in =url.openStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader bufr = new BufferedReader(isr);
        String str;
        while ((str = bufr.readLine()) != null) {
            System.out.println(str);
        }
        bufr.close();
        isr.close();
        in.close();
        //test.setText(str);
    }
*/

}


