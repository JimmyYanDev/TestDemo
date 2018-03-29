package com.yqm.testdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private String[] mListViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view);
        mListViewItems = getResources().getStringArray(R.array.MainListViewItems);
        mArrayAdapter = new ArrayAdapter<>(MainActivity.this
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
            default:
                break;
        }
    }
}
