package com.example.freewaresys.newsarticle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import static android.R.attr.orientation;

public class HindiActivity extends AppCompatActivity implements HindiNewsAdapter.HindiNewsClick{
    RecyclerView recycler_hindi;
    ArrayList<HindiItems> hindiItemsArrayList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi);

        getSupportActionBar().setTitle("Hindi News");

        recycler_hindi= (RecyclerView) findViewById(R.id.recycler_hindi);
        RecyclerView.LayoutManager layoutManager;
        layoutManager=new GridLayoutManager(getApplicationContext(),3);
        recycler_hindi.setLayoutManager(layoutManager);

        int orientation=this.getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            recycler_hindi.setLayoutManager(new GridLayoutManager(context,2));
        }
        else{
            recycler_hindi.setLayoutManager(new GridLayoutManager(context,3));
        }
        setHindiList();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<HindiItems> hindiArraylist=setHindiList();
        HindiNewsAdapter hindiNewsAdapter=new HindiNewsAdapter(this,hindiArraylist);
        recycler_hindi.setAdapter(hindiNewsAdapter);
        hindiNewsAdapter.sethindiclick(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                Log.d("TAG", "onOptionsItemSelected: ");
                return super.onOptionsItemSelected(item);
        }
    }

    private ArrayList<HindiItems> setHindiList() {

        hindiItemsArrayList = new ArrayList<>();

        HindiItems hindiItems1 = new HindiItems();
        hindiItems1.setImage_id(R.drawable.amar_ujala);
        hindiItems1.setName_text("Amar Ujala");
        hindiItems1.setUrl("http://www.amarujala.com");
        hindiItemsArrayList.add(hindiItems1);

        HindiItems hindiItems2 = new HindiItems();
        hindiItems2.setImage_id(R.drawable.dainik_jagran);
        hindiItems2.setName_text("Dainik Jagran");
        hindiItems2.setUrl("http://www.jagran.com");
        hindiItemsArrayList.add(hindiItems2);

        HindiItems hindiItems3 = new HindiItems();
        hindiItems3.setImage_id(R.drawable.dainik_navajyoti);
        hindiItems3.setName_text("Dainik Navjyoti");
        hindiItems3.setUrl("http://www.dainiknavajyoti.net");
        hindiItemsArrayList.add(hindiItems3);

        HindiItems hindiItems4 = new HindiItems();
        hindiItems4.setImage_id(R.drawable.deshbandhu);
        hindiItems4.setName_text("DeshBandhu");
        hindiItems4.setUrl("http://www.deshbandhu.co.in");
        hindiItemsArrayList.add(hindiItems4);

        HindiItems hindiItems5 = new HindiItems();
        hindiItems5.setImage_id(R.drawable.divya_himachal);
        hindiItems5.setName_text("Divya Himachal");
        hindiItems5.setUrl("http://www.divyahimachal.com");
        hindiItemsArrayList.add(hindiItems5);

        HindiItems hindiItems6 = new HindiItems();
        hindiItems6.setImage_id(R.drawable.economic_times);
        hindiItems6.setName_text("Economic Times");
        hindiItems6.setUrl("http://allindiannewspapers.com/economictimes-hindi/");
        hindiItemsArrayList.add(hindiItems6);

        HindiItems hindiItems7 = new HindiItems();
        hindiItems7.setImage_id(R.drawable.haribhoomi);
        hindiItems7.setName_text("Hari Bhoomi");
        hindiItems7.setUrl("http://www.haribhoomi.com");
        hindiItemsArrayList.add(hindiItems7);

        HindiItems hindiItems8 = new HindiItems();
        hindiItems8.setImage_id(R.drawable.hindustan);
        hindiItems8.setName_text("Hindustan");
        hindiItems8.setUrl("http://www.livehindustan.com");
        hindiItemsArrayList.add(hindiItems8);

        HindiItems hindiItems9 = new HindiItems();
        hindiItems9.setImage_id(R.drawable.jansata);
        hindiItems9.setName_text("Jansatta");
        hindiItems9.setUrl("http://www.jansatta.com");
        hindiItemsArrayList.add(hindiItems9);

        HindiItems hindiItems10 = new HindiItems();
        hindiItems10.setImage_id(R.drawable.nai_duniya);
        hindiItems10.setName_text("Nai Duniya");
        hindiItems10.setUrl("http://naidunia.jagran.com");
        hindiItemsArrayList.add(hindiItems10);

        HindiItems hindiItems11 = new HindiItems();
        hindiItems11.setImage_id(R.drawable.navbharat_times);
        hindiItems11.setName_text("Navbharat Times");
        hindiItems11.setUrl("http://navbharattimes.indiatimes.com/");
        hindiItemsArrayList.add(hindiItems11);

        HindiItems hindiItems12 = new HindiItems();
        hindiItems12.setImage_id(R.drawable.panchjanya);
        hindiItems12.setName_text("Panchjanya");
        hindiItems12.setUrl("http://www.panchjanya.com");
        hindiItemsArrayList.add(hindiItems12);

        HindiItems hindiItems13 = new HindiItems();
        hindiItems13.setImage_id(R.drawable.prabhat_khabar);
        hindiItems13.setName_text("Prabhat Khabar");
        hindiItems13.setUrl("http://www.prabhatkhabar.com");
        hindiItemsArrayList.add(hindiItems13);

        HindiItems hindiItems14 = new HindiItems();
        hindiItems14.setImage_id(R.drawable.punjab_kesri);
        hindiItems14.setName_text("Punjab Kesari");
        hindiItems14.setUrl("http://www.punjabkesari.in");
        hindiItemsArrayList.add(hindiItems14);

        HindiItems hindiItems15 = new HindiItems();
        hindiItems15.setImage_id(R.drawable.rajasthan_patrika);
        hindiItems15.setName_text("Rajasthan Patrika");
        hindiItems15.setUrl("https://www.patrika.com/rajasthan-news");
        hindiItemsArrayList.add(hindiItems15);

        HindiItems hindiItems16 = new HindiItems();
        hindiItems16.setImage_id(R.drawable.rashtriya_sahara);
        hindiItems16.setName_text("Rashtriya Sahara");
        hindiItems16.setUrl("http://www.rashtriyasahara.com");
        hindiItemsArrayList.add(hindiItems16);

        HindiItems hindiItems17 = new HindiItems();
        hindiItems17.setImage_id(R.drawable.sanjivni_today);
        hindiItems17.setName_text("Sanjeevni Today");
        hindiItems17.setUrl("http://www.sanjeevnitoday.com");
        hindiItemsArrayList.add(hindiItems17);


        for (int i = 0; i < hindiItemsArrayList.size(); i++) {
            Log.d("TAG", "namme of newspaper " + hindiItemsArrayList.get(i).getName_text());
        }
        return hindiItemsArrayList;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            recycler_hindi.setLayoutManager(new GridLayoutManager(context,2));
        }
        else{
            recycler_hindi.setLayoutManager(new GridLayoutManager(context,3));
        }
    }

    @Override
    public void hindiNewsOnclick(View view,int position,ArrayList<HindiItems> hindiItemsArrayList) {
       // Toast.makeText(getApplicationContext(),hindiItemsArrayList.get(position).getName_text(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(HindiActivity.this,HindiWebview.class);
        Bundle extras=new Bundle();
        extras.putString("URL",hindiItemsArrayList.get(position).getUrl());
        extras.putString("name",hindiItemsArrayList.get(position).getName_text());
        intent.putExtras(extras);
        startActivity(intent);
    }
}
