package com.droid.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.droid.R;
import com.droid.utils.NetWorkUtil;

public class GameTitleView extends RelativeLayout {

    private static final boolean d = false;
    
    private Context context;
    private TextView tvTime, tvDate;
    private ImageView imgNetWorkState;

    public GameTitleView(Context context) {
        super(context);
        this.context = context;
        if (!isInEditMode()) {
            initTitleView();
        }
    }

    public GameTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode()) {
            initTitleView();
        }
    }

    public void initTitleView() {
        View view = LayoutInflater.from(context).inflate(R.layout.game_titleview, this, true);
        tvTime = (TextView) view.findViewById(R.id.title_time_hour);
        tvDate = (TextView) view.findViewById(R.id.home_date);
        imgNetWorkState = (ImageView) view.findViewById(R.id.home_networkstate);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/helvetica_neueltpro_thex.otf");
        tvTime.setTypeface(typeface);
        tvDate.setTypeface(typeface);
        timeHandle.post(timeRun);
        imgNetWorkState = (ImageView) this.findViewById(R.id.home_networkstate);
        context.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        context.registerReceiver(wifiChange, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
    }

    private Handler timeHandle = new Handler();

    private Runnable timeRun = new Runnable() {

        public void run() {
            setTvTimeText(TitleViewUtil.getTime());
            setTvDateDate(TitleViewUtil.getDate());
            timeHandle.postDelayed(this, 1000);
        }

    };

    public void setTvTimeText(String text) {
        tvTime.setText(text);
    }

    public void setTvDateDate(String text) {
        tvDate.setText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * wifi 信号强度
     */
    private BroadcastReceiver wifiChange = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo.getBSSID() != null) {
                // wifi信号强度
                int signalLevel = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 4);
                if (signalLevel == 0) {
                    imgNetWorkState.setImageResource(R.drawable.wifi_1);
                } else if (signalLevel == 1) {
                    imgNetWorkState.setImageResource(R.drawable.wifi_2);
                } else if (signalLevel == 2) {
                    imgNetWorkState.setImageResource(R.drawable.wifi_3);
                } else if (signalLevel == 3) {
                    imgNetWorkState.setImageResource(R.drawable.networkstate_on);
                }
                if (d) {
                    Toast.makeText(context, "wifi level" + signalLevel, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 网络状态
     */
    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            NetworkInfo currentNetworkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            int type = currentNetworkInfo.getType();

            if (currentNetworkInfo.isConnected() && type == ConnectivityManager.TYPE_WIFI) {
                if (d) {
                    Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
                }
                imgNetWorkState.setImageResource(R.drawable.networkstate_on);
            } else if (currentNetworkInfo.isConnected() && type == ConnectivityManager.TYPE_ETHERNET) {
                imgNetWorkState.setImageResource(R.drawable.networkstate_ethernet);
            } else if (!currentNetworkInfo.isConnected()) {
                imgNetWorkState.setImageResource(R.drawable.networkstate_off);
            }

            if (d) {
                String text = "currentNetworkInfo=>>" + NetWorkUtil.isNetWorkConnected(context);
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        }
    };
}
