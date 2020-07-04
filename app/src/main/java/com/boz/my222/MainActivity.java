package com.boz.my222;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


        private WebView webView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            webView = (WebView) findViewById(R.id.webView);
            View decorView = getWindow().getDecorView();
            //全屏并隐藏虚拟按键
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //设置websetting
            WebSettings webSettings = webView.getSettings();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            webSettings.setJavaScriptEnabled(true);  //这句话必须保留。。不解释
            webSettings.setDomStorageEnabled(true);//这句话必须保留。。否则无法播放优酷视频网页。。其他的可以
            webSettings.setMediaPlaybackRequiresUserGesture(false);
            webView.setWebChromeClient(new WebChromeClient());//重写一下。有的时候可能会出现问题
            webView.setWebViewClient(new WebViewClient(){//不写的话自动跳到默认浏览器了。。跳出APP了。。怎么能不写？
                public boolean shouldOverrideUrlLoading(WebView view, String url) {//这个方法必须重写。否则会出现优酷视频周末无法播放。周一-周五可以播放的问题
                    if(url.startsWith("intent")||url.startsWith("youku")){
                        return true;
                    }else{
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                }
            });

            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            //webView.loadUrl("http://192.168.43.32:8089/statistics.html");
            //webView.loadUrl("http://192.168.43.212:8074/screen3/index");
            //webView.loadUrl("http://192.168.1.202:8074/screen3/index");
            if(Constant.startflag==0){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("http://192.168.1.202:8074/screen3/index");
                        Constant.startflag=1;
                    }
                },60000);
            }else {
                webView.loadUrl("http://192.168.1.202:8074/screen3/index");
            }


        }


}