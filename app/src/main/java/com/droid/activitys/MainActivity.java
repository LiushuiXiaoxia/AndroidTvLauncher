package com.droid.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.droid.R;
import com.droid.activitys.app.AppFragment;
import com.droid.activitys.setting.SettingFragment;
import com.droid.adapter.MainActivityAdapter;
import com.droid.views.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private MyViewPager mViewPager;
    private RadioButton localService;
    private RadioButton setting;
    private RadioButton app;

    private List<Fragment> fragments = new ArrayList<>();
    private View mViews[];
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initFragment();
    }

    private void initView() {
        mViewPager = (MyViewPager) this.findViewById(R.id.main_viewpager);
        localService = (RadioButton) findViewById(R.id.main_title_local);
        setting = (RadioButton) findViewById(R.id.main_title_setting);
        app = (RadioButton) findViewById(R.id.main_title_app);
        localService.setSelected(true);
        mViews = new View[]{localService, setting, app};
    }

    private void setListener() {
        localService.setOnClickListener(this);
        setting.setOnClickListener(this);
        app.setOnClickListener(this);

        localService.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mViewPager.setCurrentItem(0);
                }
            }
        });
        setting.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mViewPager.setCurrentItem(1);
                }
            }
        });
        app.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mViewPager.setCurrentItem(2);
                }
            }
        });
    }

    // 初始化Fragment
    private void initFragment() {
        fragments.clear();//清空

        LocalServiceFragment interactTV = new LocalServiceFragment();
        SettingFragment setting = new SettingFragment();
        AppFragment app = new AppFragment();

        fragments.add(interactTV);
        fragments.add(setting);
        fragments.add(app);

        MainActivityAdapter mAdapter = new MainActivityAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                switch (position) {
                    case 0:
                        localService.setSelected(true);
                        MainActivity.this.setting.setSelected(false);
                        MainActivity.this.app.setSelected(false);
                        break;
                    case 1:
                        localService.setSelected(false);
                        MainActivity.this.setting.setSelected(true);
                        MainActivity.this.app.setSelected(false);
                        break;
                    case 2:
                        localService.setSelected(false);
                        MainActivity.this.setting.setSelected(false);
                        MainActivity.this.app.setSelected(true);
                        break;
                }
            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_title_local:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.main_title_setting:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.main_title_app:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    /**
     * 顶部焦点获取
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean focusFlag = false;
        for (View v : mViews) {
            if (v.isFocused()) {
                focusFlag = true;
            }
        }
        if (focusFlag) {
            if (KeyEvent.KEYCODE_DPAD_LEFT == keyCode) {
                if (mCurrentIndex > 0) {
                    mViews[--mCurrentIndex].requestFocus();
                }
                return true;
            } else if (KeyEvent.KEYCODE_DPAD_RIGHT == keyCode) {
                if (mCurrentIndex < 2) {
                    mViews[++mCurrentIndex].requestFocus();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}