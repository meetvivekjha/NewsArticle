package com.example.freewaresys.newsarticle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Source;
import model.SourceResponse;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NavigationActivity extends AppCompatActivity implements SourceAdapter.Clicklistener, PositiveButtonClick {

    RecyclerView recyclerViewSource;
    private SourceAdapter sourceAdapter;
    Context context;
    AdView madview;
    NavigationView navigation;
    boolean doubleTapToExitPressedOnce;
   // NavigationView navigationView;
    final private String apiKey="414785b466474d9491ff6e63c70dd2f5";
    private DrawerLayout drawerLayout;
    private ProgressBar progressbar;
    private ActionBarDrawerToggle mdrawerToggle;
    Map<String,Integer> hashMap;
    private List<Source> msourceList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Log.d("TAG", "onCreate: ");

        this.context=this;
        navigation= (NavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);//set icon with default color
       // navigation.getBackground().setAlpha(175);//for trasperency of drawer
        navigation.setCheckedItem(R.id.home);

        hashMap=new HashMap<>();

        progressbar= (ProgressBar) findViewById(R.id.progressbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        recyclerViewSource= (RecyclerView) findViewById(R.id.recyclerviewSource);
        madview= (AdView) findViewById(R.id.adView);

        recyclerViewSource.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerViewSource.setLayoutManager(layoutManager);

        AdRequest adRequest=new AdRequest.Builder().addTestDevice("D58035577CA95969B15BBE50A1B91EFC").build();
        madview.loadAd(adRequest);



        int orientation=this.getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewSource.setLayoutManager(new GridLayoutManager(context, 2));
        }
        else{
            recyclerViewSource.setLayoutManager(new GridLayoutManager(context, 3));
        }

       drawerLayout.setDrawerListener(mdrawerToggle);

        sourceAdapter=new SourceAdapter(getApplicationContext(),msourceList,hashMap);
       // Log.d("TAG", "onCreate: sending application context");
        recyclerViewSource.setAdapter(sourceAdapter);
       // Log.d("TAG", "onCreate: setting the Adapter");
        sourceAdapter.setClickListener(this);

        MainActivity mainActivity=new MainActivity(getApplicationContext());
        //mainActivity.sethomebutton(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
        Log.d("TAG", "onCreate: NavigationActivity ");

        setupDrawerToogle();
        getHashmap(hashMap);
        apiCall("");
        drawerOpenListener();
        setNavigationDrawer();
        adviewListener(madview);
    }

    private void adviewListener(AdView madview) {
        madview.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.d("TAG", "onAdClosed: ");
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("TAG", "onAdFailedToLoad: ");
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                Log.d("TAG", "onAdLeftApplication: ");
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                Log.d("TAG", "onAdOpened: ");
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                Log.d("TAG", "onAdLoaded: ");
                super.onAdLoaded();
            }
        });
    }

    @Override
    protected void onPause() {
        if (madview!=null){
            madview.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (madview!=null){
            madview.resume();
        }
    }



    private void drawerOpenListener() {
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
               // getSupportActionBar().setTitle("Categories");

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }

        });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if(getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewSource.setLayoutManager(new GridLayoutManager(context, 2));
        }
        else{
            recyclerViewSource.setLayoutManager(new GridLayoutManager(context, 3));
        }
    }



    private void setupDrawerToogle() {//for hamburger icon

        mdrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer);

        mdrawerToggle.syncState();
    }



   @Override //for itemclick listener on hamburger icon
       public boolean onOptionsItemSelected(MenuItem item) {
       Log.d("TAG", "onOptionsItemSelected: ");
        if (mdrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        Log.d("TAG", "onPostCreate: ");
        super.onPostCreate(savedInstanceState, persistentState);

        mdrawerToggle.syncState();
    }


    public  void apiCall(String category) {

        Log.d("TAG", "apiCall: ");
        progressbar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface= ApiClient.getclient().create(ApiInterface.class);
        Call<SourceResponse> call=apiInterface.getSourceResponse(category,apiKey);

        call.enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
               // Toast.makeText(NavigationActivity.this,"API success",Toast.LENGTH_SHORT).show();
                List<Source> list_of_sources=response.body().getSources();



                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse: ");
                    progressbar.setVisibility(View.GONE);
                    sourceAdapter.setList(list_of_sources);
                    sourceAdapter.notifyDataSetChanged();

                }

                else {
                    int status_code=response.code();
                    Log.d("TAG", "Status code ="+"\t"+status_code);
                }

                Log.d("TAG", "no. of Sources:: "+"\n"+list_of_sources.size());


               /* for (Source source:list_of_sources){
                    Log.d("TAG","Name of Source::\t"+source.getName());
                }*/
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {
              //  Toast.makeText(NavigationActivity.this,"API failed",Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.GONE);
                FragmentManager manager=getSupportFragmentManager();
                NoInternetDialog nointernetDialog=new NoInternetDialog();
                nointernetDialog.setpositiveButtonClick(context);
                nointernetDialog.setRetainInstance(true);
                nointernetDialog.show(manager,"fragment-manager");

                Log.d("TAG", "onFailure: Navigation activity response ");
            }
        });
    }



  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //final List<Source> mfilteredlist=filter(msourceList,newText);
                sourceAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }*/




    private void setNavigationDrawer() {
       // navigationView= (NavigationView) findViewById(R.id.navigation);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                progressbar.setVisibility(View.VISIBLE);

                int itemid=item.getItemId();

                if (itemid==R.id.home){
                    apiCall("");
                }

                else if (itemid==R.id.hindi){
                    Intent intent=new Intent(NavigationActivity.this,HindiActivity.class);
                    startActivity(intent);
                }

                else if (itemid==R.id.business){
                    apiCall("business");
                }

                else if (itemid==R.id.entertainment){
                    apiCall("entertainment");
                }

                else if (itemid==R.id.gaming){
                    apiCall("gaming");
                }

                else if (itemid==R.id.generl){
                    apiCall("general");
                }

                else if (itemid==R.id.music){
                    apiCall("music");
                }

                else if (itemid==R.id.politics){
                    apiCall("politics");
                }

                else if (itemid==R.id.science){
                    apiCall("science-and-nature");
                }

                else if (itemid==R.id.sport){
                    apiCall("sport");
                }

                else if (itemid==R.id.technology){
                    apiCall("technology");
                }

                if (itemid==R.id.hindi){

                }else {
                    getSupportActionBar().setTitle(item.getTitle().toString());
                }
                //Toast.makeText(NavigationActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                if (itemid==R.id.hindi){

                }else {
                    navigation.setCheckedItem(itemid);
                }

                drawerLayout.closeDrawer(navigation);
                progressbar.setVisibility(View.GONE);

                Log.d("TAG", "onNavigationItemSelected: ");
               return false;
            }
        });


}

    @Override
    public void onBackPressed() {
        if (doubleTapToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleTapToExitPressedOnce=true;
        Toast.makeText(this,"Please click BACK again to Exit",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleTapToExitPressedOnce=false;
            }
        },2000);
    }

    @Override
    public void itemclicked(View view, int position, List<Source> sourceList) {
      //  getSupportActionBar().setTitle(sourceList.get(position).getName());
        Intent intent=new Intent(NavigationActivity.this,MainActivity.class);
        Bundle extras=new Bundle();
        extras.putString("Source_id",sourceList.get(position).getId());
        extras.putString("Source_name",sourceList.get(position).getName());
        intent.putExtras(extras);
        startActivity(intent);
    }


  /*  private List<Source> filter(List<Source> msourceList,String query) {
        query=query.toLowerCase();
        final List<Source> mfilteredlist=new ArrayList<>();

        for (Source source:msourceList){
          final String text=source.getName().toLowerCase();
            if (text.contains(query)){
                mfilteredlist.add(source);
            }
        }
        return mfilteredlist;
    }*/

    @Override
    protected void onDestroy() {
        Log.d("TAG", "onDestroy: ");
        if (madview!=null){
            madview.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void positiveButtonCall() {
        apiCall("");
    }

    private void getHashmap(Map<String, Integer> hashMap) {
        hashMap.put("abc-news-au",R.drawable.abc_news);
        hashMap.put("al-jazeera-english",R.drawable.al_jazeera_english);
        hashMap.put("ars-technica",R.drawable.ars_technica);
        hashMap.put("associated-press",R.drawable.associated_press);
        hashMap.put("bbc-news",R.drawable.bbc_news);
        hashMap.put("bbc-sport",R.drawable.bbc_sport);
        hashMap.put("bild",R.drawable.bild);
        hashMap.put("bloomberg",R.drawable.bloomberg);
        hashMap.put("breitbart-news",R.drawable.breitbart_news);
        hashMap.put("business-insider",R.drawable.business_insider);
        hashMap.put("business-insider-uk",R.drawable.business_insider_uk);
        hashMap.put("buzzfeed",R.drawable.buzzfeed);
        hashMap.put("cnbc",R.drawable.cnbc);
        hashMap.put("cnn",R.drawable.cnn);
        hashMap.put("daily-mail",R.drawable.dailymail);
        hashMap.put("der-tagesspiegel",R.drawable.der_tagessiegel);
        hashMap.put("die-zeit",R.drawable.die_ziet);
        hashMap.put("engadget",R.drawable.engadget);
        hashMap.put("entertainment-weekly",R.drawable.entertainment_weekly);
        hashMap.put("espn",R.drawable.espn);
        hashMap.put("espn-cric-info",R.drawable.espn_cric_info);
        hashMap.put("financial-times",R.drawable.financial_times);
        hashMap.put("focus",R.drawable.focus);
        hashMap.put("football-italia",R.drawable.football_italia);
        hashMap.put("fortune",R.drawable.fortune);
        hashMap.put("four-four-two",R.drawable.four_four_two);
        hashMap.put("fox-sports",R.drawable.fox_sports);
        hashMap.put("google-news",R.drawable.google_news);
        hashMap.put("gruenderszene",R.drawable.grunderszene);
        hashMap.put("hacker-news",R.drawable.hackernews);
        hashMap.put("handelsblatt",R.drawable.handelsblat);
        hashMap.put("ign",R.drawable.ign);
        hashMap.put("independent",R.drawable.independent);
        hashMap.put("mashable",R.drawable.mashable);
        hashMap.put("metro",R.drawable.metro);
        hashMap.put("mirror",R.drawable.mirror);
        hashMap.put("mtv-news",R.drawable.mtv_news);
        hashMap.put("mtv-news-uk",R.drawable.mtv_news_uk);
        hashMap.put("national-geographic",R.drawable.national_geographic);
        hashMap.put("new-scientist",R.drawable.new_scientist);
        hashMap.put("newsweek",R.drawable.newsweek);
        hashMap.put("new-york-magazine",R.drawable.newyork_magzine);
        hashMap.put("nfl-news",R.drawable.nflnews);
        hashMap.put("polygon",R.drawable.polygon);
        hashMap.put("recode",R.drawable.recode);
        hashMap.put("reddit-r-all",R.drawable.redit_r_all);
        hashMap.put("reuters",R.drawable.reuters);
        hashMap.put("spiegel-online",R.drawable.spiegel_online);
        hashMap.put("t3n",R.drawable.t3n);
        hashMap.put("talksport",R.drawable.talk_sport);
        hashMap.put("techcrunch",R.drawable.techcrunch);
        hashMap.put("techradar",R.drawable.techradar);
        hashMap.put("the-economist",R.drawable.the_economist);
        hashMap.put("the-guardian-au",R.drawable.the_guardian);
        hashMap.put("the-guardian-uk",R.drawable.the_guardian_uk);
        hashMap.put("the-hindu",R.drawable.the_hindu);
        hashMap.put("the-huffington-post",R.drawable.the_huffington_post);
        hashMap.put("the-lad-bible",R.drawable.the_lad_bible);
        hashMap.put("the-new-york-times",R.drawable.the_new_york_times);
        hashMap.put("the-next-web",R.drawable.the_next_web);
        hashMap.put("the-sport-bible",R.drawable.the_sport_bible);
        hashMap.put("the-telegraph",R.drawable.the_telegraph);
        hashMap.put("the-times-of-india",R.drawable.the_times_of_india);
        hashMap.put("the-verge",R.drawable.the_verge);
        hashMap.put("the-wall-street-journal",R.drawable.the_wall_street_journal);
        hashMap.put("the-washington-post",R.drawable.the_washington_post);
        hashMap.put("time",R.drawable.time);
        hashMap.put("usa-today",R.drawable.usa_today);
        hashMap.put("wired-de",R.drawable.wired);
        hashMap.put("wirtschafts-woche",R.drawable.wirtschafts_woche);
    }

   /* @Override
    public void changeTitle() {
           Toast.makeText(getApplicationContext(),"it seems to happen",Toast.LENGTH_SHORT).show();
    }*/
}