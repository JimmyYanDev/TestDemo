package com.yqm.testdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * GPS测试Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/31 14:30
 */
public class GpsTestActivity extends AppCompatActivity {

    private TextView mGpsResultText;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private static int REQUEST_FOR_ACCESS_FINE_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_test);

        mGpsResultText = findViewById(R.id.tv_gps_result);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updaeLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "请在户外进行测试", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates();
        } else {
            ActivityCompat.requestPermissions(this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                    , REQUEST_FOR_ACCESS_FINE_LOCATION_PERMISSION);
        }
        boolean providerEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!providerEnabled) {
            Toast.makeText(this, "请先打开GPS,在重新进行测试", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            this.finish();
        } else {
            Location lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updaeLocationInfo(lastKnownLocation);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (REQUEST_FOR_ACCESS_FINE_LOCATION_PERMISSION == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "您拒绝了权限申请", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @SuppressLint("MissingPermission")
    private void requestLocationUpdates() {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);

    }

    private void updaeLocationInfo(Location location) {
        if (location != null) {
            String result;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("实时的位置信息：\n经度：");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("\n纬度：");
            stringBuilder.append(location.getLatitude());
            stringBuilder.append("\n高度：");
            stringBuilder.append(location.getAltitude());
            stringBuilder.append("\n速度：");
            stringBuilder.append(location.getSpeed());
            stringBuilder.append("\n方向：");
            stringBuilder.append(location.getBearing());
            stringBuilder.append("\n精度：");
            stringBuilder.append(location.getAccuracy());
            result = stringBuilder.toString();
            Log.d("qmyan", "updaeLocationInfo: \n" + result);
            mGpsResultText.setText(result);
        } else {
            // 如果传入的Location对象为空则清空EditText
            mGpsResultText.setText("正在获取位置信息...");
        }
    }
}
