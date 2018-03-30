package com.yqm.testdemo;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 距离感应测试Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/30 11:17
 */
public class ProximityTestActivity extends AppCompatActivity {

    private ConstraintLayout mConstraintLayout;
    private SensorManager mSensorManager;
    private Sensor mProximitySensor;
    private SensorEventListener mProximitySensorEventListener;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_test);

        setTitle("距离感应测试");
        mConstraintLayout = findViewById(R.id.constraint_layout);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mProximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mProximitySensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] < 5F) {
                    mConstraintLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
                } else {
                    mConstraintLayout.setBackgroundColor(getResources().getColor(android.R.color.black));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mProximitySensorEventListener, mProximitySensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mProximitySensorEventListener, mProximitySensor);
    }
}
