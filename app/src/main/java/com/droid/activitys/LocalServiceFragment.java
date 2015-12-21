package com.droid.activitys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.droid.R;

public class LocalServiceFragment extends WoDouGameBaseFragment implements View.OnClickListener {

    ImageButton tour;
    ImageButton tv;
    ImageButton ad1;
    ImageButton cate;
    ImageButton weather;
    ImageButton ad2;
    ImageButton news;
    ImageButton appStore;
    ImageButton video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_local_service, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv = (ImageButton) view.findViewById(R.id.local_tv);
        tour = (ImageButton) view.findViewById(R.id.local_tour);
        ad1 = (ImageButton) view.findViewById(R.id.local_ad1);
        ad2 = (ImageButton) view.findViewById(R.id.local_ad2);
        cate = (ImageButton) view.findViewById(R.id.local_cate);
        weather = (ImageButton) view.findViewById(R.id.local_weather);
        news = (ImageButton) view.findViewById(R.id.local_news);
        appStore = (ImageButton) view.findViewById(R.id.local_app_store);
        video = (ImageButton) view.findViewById(R.id.local_video);

        tv.setOnFocusChangeListener(mFocusChangeListener);
        tour.setOnFocusChangeListener(mFocusChangeListener);
        ad1.setOnFocusChangeListener(mFocusChangeListener);
        ad2.setOnFocusChangeListener(mFocusChangeListener);
        cate.setOnFocusChangeListener(mFocusChangeListener);
        weather.setOnFocusChangeListener(mFocusChangeListener);
        news.setOnFocusChangeListener(mFocusChangeListener);
        appStore.setOnFocusChangeListener(mFocusChangeListener);
        video.setOnFocusChangeListener(mFocusChangeListener);

        tv.setOnClickListener(this);
        video.setOnClickListener(this);

        tv.setFocusable(true);
        tv.setFocusableInTouchMode(true);
        tv.requestFocus();
        tv.requestFocusFromTouch();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local_tv:
                break;
            case R.id.local_ad1:
                break;
            case R.id.local_ad2:
                break;
            case R.id.local_weather:
                break;
            case R.id.local_app_store:
                break;
            case R.id.local_cate:
                break;
            case R.id.local_news:
                break;
            case R.id.local_tour:
                break;
            case R.id.local_video:
                break;
        }
        Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
    }
}