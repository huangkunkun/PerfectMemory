package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.huangkun.perfectmemory.R;

/**
 * Created by huangkun on 2016/9/11.
 */
public class ChooseItemDetailActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_item_detail);
        Intent intent = getIntent();
        String urlStr = intent.getStringExtra("url");

        final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("加载中...").create();
        dialog.show();

        mWebView = (WebView) findViewById(R.id.wv_choose_detail);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }); //表明当在加载新网页时不打开系统浏览器
        mWebView.loadUrl(urlStr);

        Button back = (Button) findViewById(R.id.btn_webview_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
