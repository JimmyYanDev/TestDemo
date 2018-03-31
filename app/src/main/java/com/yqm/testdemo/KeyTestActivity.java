package com.yqm.testdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 按键测试Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/30 17:33
 */
public class KeyTestActivity extends AppCompatActivity {

    private TextView mPowerKeyText;
    private TextView mVolumeUpKeyText;
    private TextView mVolumeDownKeyText;
    private PowerManager.WakeLock mWakeLock;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(Intent.ACTION_SCREEN_OFF.equals(action)) {
                mPowerKeyText.setTextColor(Color.GREEN);
                Log.e("qmyan", "PowerKey-off");
            }else if (Intent.ACTION_SCREEN_ON.equals(action)) {
                Log.e("qmyan", "PowerKey-on");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_key_test);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mBatInfoReceiver, filter);

        mPowerKeyText = findViewById(R.id.tv_power_key);
        mVolumeUpKeyText = findViewById(R.id.tv_volume_up_key);
        mVolumeDownKeyText = findViewById(R.id.tv_volume_down_key);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWakeLock.acquire();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_POWER:
                mPowerKeyText.setTextColor(Color.GREEN);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                mVolumeUpKeyText.setTextColor(Color.GREEN);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                mVolumeDownKeyText.setTextColor(Color.GREEN);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWakeLock.release();
        unregisterReceiver(mBatInfoReceiver);
    }
}
