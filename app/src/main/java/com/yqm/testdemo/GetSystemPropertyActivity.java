package com.yqm.testdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetSystemPropertyActivity extends AppCompatActivity {

    private boolean NoBt = getSystemProperty("ro.product.nobt", "0").equals("1");
    private boolean NoFm = getSystemProperty("ro.product.nofm", "0").equals("1");
    private boolean NoWifi = getSystemProperty("ro.product.nowifi", "0").equals("1");
    private boolean NoGps = getSystemProperty("ro.product.nogps", "0").equals("1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_system_property);

        StringBuffer toastStringBuffer = new StringBuffer();
        toastStringBuffer.append("是否有蓝牙：" + (NoBt ? "有" : "没有") + "\n");
        toastStringBuffer.append("是否有FM：" + (NoFm ? "有" : "没有") + "\n");
        toastStringBuffer.append("是否有Wifi：" + (NoWifi ? "有" : "没有") + "\n");
        toastStringBuffer.append("是否有Gps：" + (NoGps ? "有" : "没有") + "\n");

        Toast.makeText(this
                , toastStringBuffer.toString()
                , Toast.LENGTH_LONG
        ).show();
    }

    private String getSystemProperty(@NonNull String key, @NonNull String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, defaultValue));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

}
