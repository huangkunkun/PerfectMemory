package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
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
        mWebView = (WebView) findViewById(R.id.wv_choose_detail);
        mWebView.setFitsSystemWindows(true);
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
