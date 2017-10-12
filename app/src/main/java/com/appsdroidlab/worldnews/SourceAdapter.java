package com.appsdroidlab.worldnews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Source;

/**
 * Created by Freeware Sys on 9/19/2017.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder>{


    List<Source> sourceList=new ArrayList<>();
  //  List<Source> mfilteredsourcelist=new ArrayList<>();
    Context context;
    Map<String,Integer> hasmap;
    private Clicklistener mclicklistener;

    public SourceAdapter(Context applicationContext, List<Source> sourceList, Map<String, Integer> hashMap){
        this.context=applicationContext;
        this.sourceList=sourceList;
      // this.mfilteredsourcelist=sourceList;
        this.hasmap=hashMap;
        Log.d("TAG", "SourceAdapter: constructor");
    }

   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.source_single_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // Source src = mfilteredsourcelist.get(position);
        Source src = sourceList.get(position);

        if (!TextUtils.isEmpty(src.getName())) {
            holder.name_tv.setVisibility(View.VISIBLE);
            holder.name_tv.setText(src.getName());
        }else {
            holder.name_tv.setVisibility(View.GONE);
        }
       // holder.category_tv.setText(src.getCategory());
        if (!TextUtils.isEmpty(src.getId())) {
            holder.icon_iv.setVisibility(View.VISIBLE);
            Picasso.with(context).load(hasmap.get(src.getId())).resize(100, 100).into(holder.icon_iv);
        }else {
            holder.icon_iv.setVisibility(View.GONE);
        }
        }
       // holder.icon_iv.setImageResource(hasmap.get(src.getId()));


    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public void setClickListener(Clicklistener mclicklistener) {
        this.mclicklistener=mclicklistener;
    }


    public void setList(List<Source> myList){
        sourceList.clear();
        sourceList.addAll(myList);
    }

 /*  @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();

                if (charString.isEmpty()){
                    mfilteredsourcelist=sourceList;
                }
                else {
                    List<Source> filteredList=new ArrayList<>();

                    for (Source source:sourceList){
                        if (source.getName().toLowerCase().contains(charString)){
                            filteredList.add(source);
                        }
                    }
                    mfilteredsourcelist=filteredList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mfilteredsourcelist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mfilteredsourcelist= (List<Source>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/


   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name_tv,category_tv;
        ImageView icon_iv;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/Bariol_Bold.otf");
            name_tv= (TextView) itemView.findViewById(R.id.name_tv);
            name_tv.setTypeface(typeface);
            //category_tv= (TextView) itemView.findViewById(R.id.category_tv);
            icon_iv= (ImageView) itemView.findViewById(R.id.icon_iv);


        }

        @Override
        public void onClick(View view) {
            if (mclicklistener!=null)
            mclicklistener.itemclicked(view,getPosition(),sourceList);
        }
    }

  /*  public void setFilter(List<Source> source){
        sourceList=new ArrayList<>();
        sourceList.addAll(source);
        notifyDataSetChanged();

    }*/




    public interface Clicklistener{
        void itemclicked(View view,int position,List<Source> sourceList);
    }
}
