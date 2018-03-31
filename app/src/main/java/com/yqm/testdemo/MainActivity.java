package com.yqm.testdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 首页Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/30 11:17
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListView = findViewById(R.id.list_view);
        String[] mListViewItems = getResources().getStringArray(R.array.MainListViewItems);
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<>(MainActivity.this
                , android.R.layout.simple_list_item_1
                , mListViewItems
        );
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent proximityTestIntent = new Intent(MainActivity.this, ProximityTestActivity.class);
                startActivity(proximityTestIntent);
                break;
            case 1:
                Intent getSystemPropertyIntent = new Intent(MainActivity.this, GetSystemPropertyActivity.class);
                startActivity(getSystemPropertyIntent);
                break;
            case 2:
                Intent phoneCardTestIntent = new Intent(MainActivity.this, PhoneCardTestActivity.class);
                startActivity(phoneCardTestIntent);
                break;
            case 3:
                Intent vibratorTestIntent = new Intent(MainActivity.this, VibratorTestActiity.class);
                startActivity(vibratorTestIntent);
                break;
            case 4:
                Intent keyTestIntent = new Intent(MainActivity.this, KeyTestActivity.class);
                startActivity(keyTestIntent);
                break;
            case 5:
                Intent execShellCmdTestIntent = new Intent(MainActivity.this, ExecShellCmdTestActivity.class);
                startActivity(execShellCmdTestIntent);
                break;
            case 6:
                Intent gpsTestIntent = new Intent(MainActivity.this, GpsTestActivity.class);
                startActivity(gpsTestIntent);
                break;
            default:
                Toast.makeText(this, "开发中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
