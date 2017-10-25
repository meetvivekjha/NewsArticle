package com.appsdroidlab.worldnews;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Freeware Sys on 10/15/2017.
 */

public class UrlToSourceFragment extends DialogFragment {
    Context context;
    TextView urlTV;
    Button dismissButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.url_to_source_fragment,null,false);
       this.context=getActivity().getApplicationContext();
        dismissButton= (Button) rootview.findViewById(R.id.dismissButton);
        urlTV= (TextView) rootview.findViewById(R.id.urltextview);

       // Typeface typeface=Typeface.createFromAsset(context.getAssets(),"Bariol_Bold.otf");
       // urlTV.setTypeface(typeface);
        urlTV.setText("Api Source:"+"https://newsapi.org");


       // getDialog().setTitle(getActivity().getResources().getString(R.string.about));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Linkify.addLinks(urlTV,Linkify.WEB_URLS);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return  rootview;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
