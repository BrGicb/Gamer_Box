package com.example.wugey.gamerbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import static java.lang.Thread.sleep;

public class information extends AppCompatActivity {
    String forappid = SearchPage.forappid;
    int appid = 570;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);



        dealwithappid();
        //TextView textView = (TextView)findViewById(R.id.textView3) ;
        //textView.setText(forappid);

        //new show_info().execute();
        setwebchart();
        setpricechart();

        //定位至开头
        ScrollView scrollView = (ScrollView)findViewById(R.id.scroll);
        scrollView.smoothScrollTo(0,1);
    }

    //对forappid进行处理
    void dealwithappid(){
        forappid = forappid.substring(34 , forappid.length());
        forappid = forappid.substring(0,forappid.indexOf("/"));
        appid = Integer.valueOf(forappid);
    }

    void setwebchart(){
        WebView webView = (WebView)findViewById(R.id.webchart);
        webView.loadUrl("https://steamdb.info/app/"+appid+"/");
        webView.getScrollY();
        // disable scroll on touch禁止表格页面的滑动
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){
            //页面加载完之后再显示
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                webView.setInitialScale(180);
                webView.scrollTo(11,0);
                ScrollView scrollView = (ScrollView)findViewById(R.id.scroll);
                scrollView.smoothScrollTo(0,1);
            }
        });
    }

    void setpricechart(){
        WebView webView = (WebView)findViewById(R.id.pricechart);
        TextView textView = (TextView)findViewById(R.id.textView3);
        webView.loadUrl("https://steamdb.info/app/"+appid+"/graphs/");
        webView.getScrollY();
        // disable scroll on touch禁止表格页面的滑动
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){
            //页面加载完之后再显示
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                webView.setInitialScale(100);
                webView.scrollTo(8,850);
                ScrollView scrollView = (ScrollView)findViewById(R.id.scroll);
                scrollView.smoothScrollTo(0,1);
            }
        });
    }



/*    private class show_info extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String js_gamename = "";
            String stringUrl = "http://steamspy.com/api.php?request=appdetails&appid=570";// + SearchPage.appid;
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    buffer.append(line + "\n");

                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                JSONObject response = new JSONObject(buffer.toString());
                js_gamename = response.optString("name");

                return js_gamename;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String js_gamename) {
            //Update the temperature displayed
            TextView inf_name = (TextView)findViewById(R.id.inf_name);
            inf_name.setText(js_gamename);
        }

    }*/
}
