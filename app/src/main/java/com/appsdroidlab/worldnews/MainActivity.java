package com.appsdroidlab.worldnews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import model.Article;
import model.ArticleResponse;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ClickListener,PositiveButtonClick {

    private static final String apiKey = "414785b466474d9491ff6e63c70dd2f5";

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    Context context;
    private ProgressBar progressbar;
    static String source_name, title;
    private NoInternetDialog noInternetDialog;
  //  OnHomeButtonClicked onHomeButtonClicked;
    InterstitialAd minterstitialAd;

    public MainActivity(){

    }

    public MainActivity(Context applicationContext) {
        this.context=applicationContext;
        Log.d("TAG", "MainActivity: constructor ");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        Intent intent = this.getIntent();
        source_name = intent.getStringExtra("Source_id");
        title = intent.getStringExtra("Source_name");

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);

        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //  noInternetDialog.setPositiveBtnArticle(this);
        apiCall1(source_name);

        newsAdapter = new NewsAdapter(getApplicationContext());
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setclicklistener(this);

        Log.d("TAG", "onCreate: main activity");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
               // onHomeButtonClicked.changeTitle();
                Log.d("TAG", "onOptionsItemSelected: ");
                return true;
        default:
        Log.d("TAG", "onOptionsItemSelected: ");
        return super.onOptionsItemSelected(item);
    }
}

    private void apiCall1(final String source_name) {
        progressbar.setVisibility(View.VISIBLE);
        Log.i("ankur","apiCall source_name = "+source_name);
         ApiInterface apiInterface = ApiClient.getclient().create(ApiInterface.class);
        Call<ArticleResponse> call= apiInterface.getArticleResponse(source_name,apiKey);

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                List<Article> list_of_articles=response.body().getArticles();

                progressbar.setVisibility(View.GONE);

                newsAdapter.setList(list_of_articles);
                newsAdapter.notifyDataSetChanged();


                Log.d("vivek", "no. of Articles:: "+list_of_articles.size());

                for (Article article:list_of_articles){
                    Log.d("vivek",article.toString() );
                    getSupportActionBar().setTitle(title);
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.d("vivek", "onFailure: ");
                progressbar.setVisibility(View.GONE);
                FragmentManager manager=getSupportFragmentManager();
                NoInternetDialog noInternetDialog=new NoInternetDialog();
                noInternetDialog.setpositiveButtonClick(context);
                noInternetDialog.setRetainInstance(true);
                noInternetDialog.show(manager,"Fragment-manager");
            }

        });

    }

    @Override
    public void itemclicked(View view, int position, List<Article> articleList) {
        //Toast.makeText(MainActivity.this,"item clicked::"+"\n"+articleList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,WebviewActivity.class);
        Bundle extras=new Bundle();
        extras.putString("URL",articleList.get(position).getUrl());
        extras.putString("Author",MainActivity.title);
        intent.putExtras(extras);
        //intent.putExtra("URL",articleList.get(position).getUrl());

        startActivity(intent);
    }



    @Override
    public void positiveButtonCall() {
        apiCall1(source_name);

    }

   /* public void sethomebutton(OnHomeButtonClicked onHomeButtonClicked) {
       this.onHomeButtonClicked=onHomeButtonClicked;
    }*/

   /* public interface OnHomeButtonClicked{
        void changeTitle();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
         minterstitialAd=new InterstitialAd(this);
        minterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest=new AdRequest.Builder().build();
        minterstitialAd.loadAd(adRequest);
        minterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial();
            }

        });

    }
    private void showInterstitial() {
        if (minterstitialAd.isLoaded()){
            minterstitialAd.show();
        }
    }
}





