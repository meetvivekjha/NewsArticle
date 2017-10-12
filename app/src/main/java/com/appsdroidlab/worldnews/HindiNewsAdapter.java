package com.appsdroidlab.worldnews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Freeware Sys on 9/28/2017.
 */

public class HindiNewsAdapter extends RecyclerView.Adapter<HindiNewsAdapter.ViewHolder> {

    ArrayList<HindiItems> hindiItemsArrayList;
    HindiItems hindiItems;
    Context context;
    HindiNewsClick hindiNewsClick;

    public HindiNewsAdapter(HindiActivity hindiActivity, ArrayList<HindiItems> hindiArraylist) {
        this.hindiItemsArrayList=hindiArraylist;
        context=hindiActivity;
    }

    @Override
    public HindiNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("TAG", "onCreateViewHolder: ");

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_hindi,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HindiNewsAdapter.ViewHolder holder, int position) {
        HindiItems hpos=hindiItemsArrayList.get(position);

        holder.hindi_name_tv.setText(hpos.getName_text());
        holder.hindi_icon_iv.setImageResource(hpos.getImage_id());

    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: "+hindiItemsArrayList.size());
        return hindiItemsArrayList.size();
    }

    public void sethindiclick(HindiActivity hindiActivity) {
        hindiNewsClick=hindiActivity;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hindi_name_tv;
        ImageView hindi_icon_iv;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.d("TAG", "ViewHolder: ");
            itemView.setOnClickListener(this);
            Typeface typeface=Typeface.createFromAsset(context.getAssets(),"fonts/Bariol_Bold.otf");
            hindi_name_tv= (TextView) itemView.findViewById(R.id.name_tv_hindi);
            hindi_name_tv.setTypeface(typeface);
            hindi_icon_iv= (ImageView) itemView.findViewById(R.id.icon_iv_hindi);
        }

        @Override
        public void onClick(View view) {
            if (hindiNewsClick !=null){
                hindiNewsClick.hindiNewsOnclick(view,getPosition(),hindiItemsArrayList);
            }
        }
    }

    public interface HindiNewsClick{

        void hindiNewsOnclick(View view, int position, ArrayList<HindiItems> hindiItemsArrayList);
    }
}
