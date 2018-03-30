package com.yqm.testdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * 获取系统属性Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/30 11:17
 */
public class GetSystemPropertyActivity extends AppCompatActivity {

    private boolean noBlutooh = "1".equals(getSystemProperty("ro.product.nobt", "0"));
    private boolean noFm = "1".equals(getSystemProperty("ro.product.nofm", "0"));
    private boolean nowifi = "1".equals(getSystemProperty("ro.product.nowifi", "0"));
    private boolean noGps = "1".equals(getSystemProperty("ro.product.nogps", "0"));
    private String productName = getSystemProperty("ro.product.name", "unknown");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_system_property);

        StringBuffer toastStringBuffer;
        toastStringBuffer = new StringBuffer();
        toastStringBuffer.append("是否有蓝牙：")
                .append(!noBlutooh ? "有" : "没有")
                .append("\n")
                .append("是否有FM：")
                .append(!noFm ? "有" : "没有")
                .append("\n")
                .append("是否有Wifi：")
                .append(!nowifi ? "有" : "没有")
                .append("\n")
                .append("是否有Gps：")
                .append(!noGps ? "有" : "没有")
                .append("\n")
                .append("产品名称：")
                .append(productName);

        Toast.makeText(this
                , toastStringBuffer.toString()
                , Toast.LENGTH_LONG
        ).show();
    }

    /**
     * 利用反射获取系统属性
     *
     * @param key          系统属性
     * @param defaultValue 默认值
     * @return 属性值
     */
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
