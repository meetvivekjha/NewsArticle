package com.example.freewaresys.newsarticle;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HindiWebview extends AppCompatActivity {
    private WebView webViewHindi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi_webview);

        webViewHindi= (WebView) findViewById(R.id.webviewHindi);

        Intent intent=this.getIntent();
        Bundle extras=intent.getExtras();
        String url=extras.getString("URL");
        String NewsName=extras.getString("name");
        startWebActivity(url);

        getSupportActionBar().setTitle(NewsName);

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
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,webViewHindi.getUrl());
                startActivity(Intent.createChooser(sharingIntent,"share this!"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startWebActivity(final String url ) {
        webViewHindi.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl( url);
                return true;
            }
        });

        webViewHindi.getSettings().setJavaScriptEnabled(true);
        webViewHindi.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webViewHindi.canGoBack()){
            webViewHindi.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
