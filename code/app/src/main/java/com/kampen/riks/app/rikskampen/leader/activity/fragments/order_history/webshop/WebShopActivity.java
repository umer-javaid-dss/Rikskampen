package com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.webshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.kampen.riks.app.rikskampen.R;


public class WebShopActivity extends AppCompatActivity {


    WebView  webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_shop);

        webView=findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://rikskampen.dubaisoftwaresolutions.com/index.php?route=product/category&path=63_59");
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    public void onBackClick(View view) {

        finish();

    }
}
