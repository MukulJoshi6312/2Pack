package com.example.a2pack.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2pack.R;


public class AboutUsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    TextView aboutUs,aboutUsHeading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        aboutUs = view.findViewById(R.id.about_us_text);
        aboutUsHeading = view.findViewById(R.id.about_us_heading);

        aboutUs.setText(getResources().getString(R.string.about_us));


        return view;
    }
}