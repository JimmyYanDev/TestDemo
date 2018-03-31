package com.yqm.testdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 手机卡测试Activity
 *
 * @author: michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/30 11:17
 */
public class PhoneCardTestActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private static int REQUEST_FOR_READ_PHONE_STATE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_card_test);

        mResultTextView = findViewById(R.id.tv_result);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_FOR_READ_PHONE_STATE_PERMISSION);
            } else {
                showPhoneCardInfo();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_FOR_READ_PHONE_STATE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showPhoneCardInfo();
            } else {
                Toast.makeText(this, "您拒绝了权限申请", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPhoneCardInfo() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Class<?> aClass = telephonyManager.getClass();
        boolean isMultiSimEnabled = false;
        int sim1Enabled = -1;
        int sim2Enabled = -1;
        String result = "";
        try {
            Method method = aClass.getMethod("isMultiSimEnabled");
            isMultiSimEnabled = (boolean) (method.invoke(telephonyManager));
            if (isMultiSimEnabled) {
                Method getSimState = aClass.getMethod("getSimState", int.class);
                sim1Enabled = (int) (getSimState.invoke(telephonyManager, 0));
                sim2Enabled = (int) (getSimState.invoke(telephonyManager, 1));
                result = "手机卡1: " + ((sim1Enabled != -1) ? "可用" : "不可用")
                        + "\n手机卡2: " + ((sim2Enabled != -1) ? "可用" : "不可用");
                if (-1 != sim1Enabled) {
                    result += "\n卡1号码: " + (String) (aClass.getMethod("getLine1NumberForSubscriber", int.class).invoke(telephonyManager, 1));
                    result += "\n卡1id: " + (String) (aClass.getMethod("getSubscriberId", int.class).invoke(telephonyManager, 1));
                    result += "\n卡1Imei: " + (String) (aClass.getMethod("getImei", int.class).invoke(telephonyManager, 0));
                }
                if (-1 != sim2Enabled) {
                    result += "\n卡2号码: " + (String) (aClass.getMethod("getLine1NumberForSubscriber", int.class).invoke(telephonyManager, 2));
                    result += "\n卡2id: " + (String) (aClass.getMethod("getSubscriberId", int.class).invoke(telephonyManager, 2));
                    result += "\n卡2Imei: " + (String) (aClass.getMethod("getImei", int.class).invoke(telephonyManager, 1));
                }
                mResultTextView.setText(result);
                Log.d("qmyan", result);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
