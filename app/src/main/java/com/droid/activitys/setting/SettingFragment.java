package com.droid.activitys.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.droid.R;
import com.droid.activitys.WoDouGameBaseFragment;
import com.droid.activitys.app.AppAutoRun;
import com.droid.activitys.app.AppUninstall;
import com.droid.activitys.eliminateprocess.EliminateMainActivity;
import com.droid.activitys.garbageclear.GarbageClear;
import com.droid.activitys.speedtest.SpeedTestActivity;

public class SettingFragment extends WoDouGameBaseFragment implements View.OnClickListener {

    private ImageButton Setting_Clean;// 垃圾清理
    private ImageButton Setting_Accelerate;// 一键加速
    private ImageButton appUninstall;
    private ImageButton setNet;
    private ImageButton setMore;
    private ImageButton netSpeed;
    private ImageButton sysUpdate;
    private ImageButton fileManage;
    private ImageButton about;
    private ImageButton autoRun;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_setting, container, false);
        initView(view);
        setListener();
        return view;
    }

    private void initView(View view) {
        appUninstall = (ImageButton) view.findViewById(R.id.setting_uninstall);
        setNet = (ImageButton) view.findViewById(R.id.setting_net);
        setMore = (ImageButton) view.findViewById(R.id.setting_more);
        netSpeed = (ImageButton) view.findViewById(R.id.setting_net_speed);
        sysUpdate = (ImageButton) view.findViewById(R.id.setting_update);
        fileManage = (ImageButton) view.findViewById(R.id.setting_file);
        about = (ImageButton) view.findViewById(R.id.setting_about);
        Setting_Clean = (ImageButton) view.findViewById(R.id.setting_clean);
        Setting_Accelerate = (ImageButton) view.findViewById(R.id.setting_accelerate);
        autoRun = (ImageButton) view.findViewById(R.id.setting_autorun);

        appUninstall.setOnFocusChangeListener(mFocusChangeListener);
        setNet.setOnFocusChangeListener(mFocusChangeListener);
        setMore.setOnFocusChangeListener(mFocusChangeListener);
        netSpeed.setOnFocusChangeListener(mFocusChangeListener);
        sysUpdate.setOnFocusChangeListener(mFocusChangeListener);
        fileManage.setOnFocusChangeListener(mFocusChangeListener);
        about.setOnFocusChangeListener(mFocusChangeListener);
        Setting_Clean.setOnFocusChangeListener(mFocusChangeListener);
        Setting_Accelerate.setOnFocusChangeListener(mFocusChangeListener);
        autoRun.setOnFocusChangeListener(mFocusChangeListener);

    }

    private void setListener() {
        Setting_Clean.setOnClickListener(this);
        Setting_Accelerate.setOnClickListener(this);
        about.setOnClickListener(this);
        setMore.setOnClickListener(this);
        appUninstall.setOnClickListener(this);
        setNet.setOnClickListener(this);
        fileManage.setOnClickListener(this);
        netSpeed.setOnClickListener(this);
        sysUpdate.setOnClickListener(this);
        autoRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent jumpIntent;
        switch (view.getId()) {
            case R.id.setting_clean:
                jumpIntent = new Intent(context, GarbageClear.class);
                startActivity(jumpIntent);
                break;
            case R.id.setting_accelerate:
                jumpIntent = new Intent(context, EliminateMainActivity.class);
                startActivity(jumpIntent);
                break;
            case R.id.setting_about:
                showShortToast("About");
                break;
            case R.id.setting_more:
                jumpIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(jumpIntent);
                break;
            case R.id.setting_file:
                showShortToast("File");
                break;
            case R.id.setting_update:
                showShortToast("Update");
                break;
            case R.id.setting_net:
                jumpIntent = new Intent(context, SettingCustom.class);
                startActivity(jumpIntent);
                break;
            case R.id.setting_uninstall:
                jumpIntent = new Intent(context, AppUninstall.class);
                startActivity(jumpIntent);
                break;
            case R.id.setting_autorun:
                jumpIntent = new Intent(context, AppAutoRun.class);
                startActivity(jumpIntent);
                break;
            case R.id.setting_net_speed:
                jumpIntent = new Intent(context, SpeedTestActivity.class);
                startActivity(jumpIntent);
                break;
        }
    }
}