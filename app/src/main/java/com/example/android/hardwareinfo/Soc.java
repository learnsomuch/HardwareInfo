package com.example.android.hardwareinfo;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by sankar on 4/20/17.
 */

public class Soc extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //return inflater.inflate(R.layout.fragment_soc, container, false);
            View view =  inflater.inflate(R.layout.fragment_soc, container, false);

            TextView tv = (TextView) getView().findViewById(R.id.quantity_text);
            tv.setText("Hardware: " + Build.HARDWARE + "\n");
            return view;
        }

}
