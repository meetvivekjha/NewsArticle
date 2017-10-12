package com.appsdroidlab.worldnews;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class HindiWebview extends AppCompatActivity {
    private WebView webViewHindi;
    InterstitialAd interstitialAdHindi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi_webview);

        webViewHindi= (WebView) findViewById(R.id.webviewHindi);
        //webViewHindi.getSettings().setBuiltInZoomControls(true);
        WebSettings webSettings=webViewHindi.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);

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
                startActivity(Intent.createChooser(sharingIntent,"Share News via"));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interstitialAdHindi=new InterstitialAd(this);
        interstitialAdHindi.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest=new AdRequest.Builder().build();
        interstitialAdHindi.loadAd(adRequest);

        interstitialAdHindi.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitialHindi();
            }
        });
    }

    private void showInterstitialHindi() {
        if (interstitialAdHindi.isLoaded()){
            interstitialAdHindi.show();
        }
    }
}
