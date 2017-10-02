package com.example.freewaresys.newsarticle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    private WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView= (WebView) findViewById(R.id.webview);

        Intent intent=this.getIntent();
        Bundle extras=intent.getExtras();
        url=extras.getString("URL");
        String Author=extras.getString("Author");
        startWebActivity(url);
        getSupportActionBar().setTitle(Author);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharing_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.share:
                String url = null;
                this.url=url;
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,webView.getUrl());
                startActivity(Intent.createChooser(sharingIntent,"share this!"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startWebActivity(final String url) {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
