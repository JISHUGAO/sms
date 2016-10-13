package com.gavingao.sms.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.gavingao.sms.R;

/**
 * Created by gaoweisong on 16-10-3.
 */

public class SearchActivity extends Activity {

    WebView webview;
    String url = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        webview = (WebView)findViewById(R.id.search_webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }
}
