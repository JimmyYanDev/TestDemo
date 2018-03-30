package com.yqm.testdemo;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 震动测试Activity
 *
 * @author michealyan
 * email yanqinming@hymost.com
 * created at 2018/3/30 16:38
 */
public class VibratorTestActiity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartVibratorButton;
    private Button mStopVibratorButton;
    private Vibrator mVibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator_test);

        mStartVibratorButton = findViewById(R.id.btn_start_vibrator);
        mStopVibratorButton = findViewById(R.id.btn_stop_vibrator);
        mStartVibratorButton.setOnClickListener(this);
        mStopVibratorButton.setOnClickListener(this);

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_vibrator:
                // {等待时间, 震动时间, 等待时间, 震动时间...}, 0重复/-1不重复
                mVibrator.vibrate(new long[]{0, 1000, 2000, 0}, 0);
                break;
            case R.id.btn_stop_vibrator:
                mVibrator.cancel();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mVibrator) {
            mVibrator.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mVibrator) {
            mVibrator = null;
        }
    }
}
