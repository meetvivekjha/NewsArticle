package com.example.freewaresys.newsarticle;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Article;

/**
 * Created by Freeware Sys on 9/18/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<Article> articleList=new ArrayList<>();
    Context context;
    private ClickListener mclicklistener;

    public NewsAdapter(Context applicationContext, List<Article> list_of_article){
        context=applicationContext;
        articleList=list_of_article;

    }

    public NewsAdapter(Context applicationContext) {
        context=applicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

   /* public int getScreenWidth()
    {
        int screen_width=Resources.getSystem().getDisplayMetrics().widthPixels;
        return screen_width;
    }
    public int getScreenheight() {
        int screen_height=Resources.getSystem().getDisplayMetrics().heightPixels;
        return screen_height;
    }*/


    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

       /* Date d = new Date();
        String parsedDate ;
        String dateToParse=articleList.get(position).getPublishedAt().toString();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
             d=formatter.parse(dateToParse);
             parsedDate=formatter.format(d).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parsedDate=formatter.format(d).toString();
       /* TimeZone timeZone=TimeZone.getTimeZone("Asia/Calcutta");
        Calendar calendar=Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setCalendar(calendar);
        try {
            calendar.setTime(simpleDateFormat.parse(articleList.get(position).getDescription().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
      //  Date date=calendar.getTime();
      //  String parsedDate=date.toString();
        Drawable mydrawable=context.getResources().getDrawable(R.drawable.image_not_found);
        Article arcpos=articleList.get(position);

        holder.title.setText(arcpos.getTitle());
        if(!TextUtils.isEmpty(arcpos.getDescription())){
            holder.desription.setText(arcpos.getDescription());
        }else{
            holder.desription.setText("NO DESCRIPTION FOUND");
        }

        if (!TextUtils.isEmpty(arcpos.getPublishedAt())) {
            String parsedDate=arcpos.getPublishedAt().substring(0,10);
            holder.date.setText(parsedDate);
        }else {
            holder.date.setText("Date not provided");
        }

        if (!TextUtils.isEmpty(arcpos.getUrlToImage()))
           {
            Picasso.with(context).load(arcpos.getUrlToImage()).resize(145,140).into(holder.image);
        }else {
            holder.image.setImageDrawable(mydrawable);
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setclicklistener(ClickListener mclicklistener) {
       this.mclicklistener=mclicklistener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,desription,date;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/Bariol_Bold.otf");
            title= (TextView) itemView.findViewById(R.id.title_tv);
           // title.setTypeface(typeface);
            desription= (TextView) itemView.findViewById(R.id.description_tv);
            date= (TextView) itemView.findViewById(R.id.date_tv);
            image= (ImageView) itemView.findViewById(R.id.imageview);
        }

        @Override
        public void onClick(View view) {
            if (mclicklistener!=null)
            mclicklistener.itemclicked(view,getPosition(),articleList);
        }
    }

    public void setList(List<Article> mylist){
        articleList.clear();
        articleList.addAll(mylist);
    }

    public interface ClickListener{
        void itemclicked(View view,int position,List<Article> articleList);
    }
}
