package com.liu.webviewwithjs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint("JavascriptInterface")
    private void initView() {
        webView= (WebView) findViewById(R.id.wv);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInterface(),"control");
        //通过webview加载html页面
        webView.loadUrl("file:///android_asset/MyHtml.html");
    }

    public class JsInterface{
        @JavascriptInterface
        public void showToast(String tosat){
            Toast.makeText(MainActivity.this,tosat,Toast.LENGTH_SHORT).show();
            Log.d("html", "show toast success");
        }

        public void log(final String msg){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript log("+"'"+msg+"'"+")");
                }
            });
        }
    }
}
