package com.droid.activitys.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droid.R;
import com.droid.activitys.WoDouGameBaseFragment;
import com.droid.adapter.DataPagerAdapter;
import com.droid.bean.AppBean;

import java.util.ArrayList;
import java.util.List;

public class AppFragment extends WoDouGameBaseFragment {

    private Context mContext;
    private int mPagerCount = -1;//一共的页数
    private List<AllAppLayout> mPagerListAllAppLayout = new ArrayList<>();
    private ViewPager mViewPager = null;
    private TextView pointer = null;
    private Receiver receiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_app, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.app_view_pager);
        pointer = (TextView) view.findViewById(R.id.app_pointer);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                String text = String.format("%d/%d", position + 1, mPagerCount);
                pointer.setText(text);
            }
        });

        initAllApp();

        return view;
    }

    /**
     * 初始化app数据和布局
     */
    public void initAllApp() {

        GetAppList getAppInstance = new GetAppList(mContext);
        List<AppBean> mAppList = getAppInstance.getLaunchAppList();
        if (mPagerListAllAppLayout != null && mPagerListAllAppLayout.size() > 0) {
            mPagerListAllAppLayout.clear();
        }
        if (mAppList.size() % 15 == 0) {
            mPagerCount = mAppList.size() / 15;
        } else {
            mPagerCount = mAppList.size() / 15 + 1;
        }

        for (int i = 0; i < mPagerCount; i++) {
            AllAppLayout mAllayout = new AllAppLayout(mContext);
            mAllayout.setAppList(mAppList, i, mPagerCount);
            mAllayout.managerAppInit();
            mPagerListAllAppLayout.add(mAllayout);
        }

        DataPagerAdapter adapter = new DataPagerAdapter(mPagerListAllAppLayout);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        receiver = new Receiver();
        mContext.registerReceiver(receiver, filter);
    }

    @Override
    public void onStop() {
        super.onDestroy();
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //安装广播
            boolean isPackageAdd = intent.getAction().equals("android.intent.action.PACKAGE_ADDED");
            //卸载广播
            boolean isPackageRemoved = intent.getAction().equals("android.intent.action.PACKAGE_REMOVED");
            if (isPackageAdd || isPackageRemoved) {
                initAllApp();
            }
        }
    }
}