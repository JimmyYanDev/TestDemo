package com.yqm.testdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * 按键测试Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/30 17:33
 */
public class KeyTestActivity extends AppCompatActivity {

    private TextView powerKeyText;
    private TextView volumeUpKeyText;
    private TextView volumeDownKeyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_test);

        powerKeyText = findViewById(R.id.tv_power_key);
        volumeUpKeyText = findViewById(R.id.tv_volume_up_key);
        volumeDownKeyText = findViewById(R.id.tv_volume_down_key);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_POWER:
                powerKeyText.setTextColor(Color.GREEN);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                volumeUpKeyText.setTextColor(Color.GREEN);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                volumeDownKeyText.setTextColor(Color.GREEN);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_POWER:
                powerKeyText.setTextColor(Color.GREEN);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                volumeUpKeyText.setTextColor(Color.GREEN);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                volumeDownKeyText.setTextColor(Color.GREEN);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
