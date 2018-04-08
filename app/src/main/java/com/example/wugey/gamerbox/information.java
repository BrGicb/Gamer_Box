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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
        try {
            setpricechart();
        }  catch (IOException e) {
            e.printStackTrace();
        }

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

    void setpricechart() throws IOException {
        WebView webView = (WebView)findViewById(R.id.pricechart);
        TextView textView = (TextView)findViewById(R.id.textView3);

        //根据获取的信息写html文件
        String str = "<iframe src=\\\"https://steamdb.info/embed/?appid=730\\\" height=\\\"389px\\\" width=\\\"100%\\\" scrolling=\\\"no\\\" frameborder=\\\"0\\\"></iframe>";
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/assets/game.html", false); // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示覆盖的方式写入
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //打开写好的html文件
        webView.loadUrl("file:///android_asset/game.html");
        //webView.loadUrl(str);
        //webView.loadUrl("https://steamdb.info/app/"+appid+"/graphs/");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            //页面加载完之后再显示
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
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
