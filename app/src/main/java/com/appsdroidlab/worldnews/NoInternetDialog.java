package com.appsdroidlab.worldnews;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by Freeware Sys on 9/24/2017.
 */

public class NoInternetDialog extends DialogFragment {


    PositiveButtonClick mpositiveButtonClick;


   /* public void setOnPositiveButtonClicked(OnPositiveButtonClicked onPositiveButtonClicked){
        this.onPositiveButtonClicked=onPositiveButtonClicked;
    }*/


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

       // onPositiveButtonClicked= (OnPositiveButtonClicked) getActivity();
       // positiveBtnArticle= (PositiveBtnArticle) getActivity();

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("No Internet Connection");
        builder.setMessage("press Cancel to exit or Try again");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
                /*Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
            }
        });

        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("TAG", "onClick: ");
                //onPositiveButtonClicked= (OnPositiveButtonClicked) getActivity();
                mpositiveButtonClick.positiveButtonCall();

              // onPositiveButtonClicked.apicall((OnPositiveButtonClicked)getActivity());
               //onPositiveButtonClicked.apicall();

            }
        });
        return builder.create();
    }


    public void setpositiveButtonClick(Context context) {
        mpositiveButtonClick= (PositiveButtonClick) context;
    }
}



