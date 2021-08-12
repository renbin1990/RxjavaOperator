package com.renbin.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.renbin.rxlib.CreateOperatoeActivity;
import com.renbin.rxlib.FilterOperatorActivity;
import com.renbin.rxlib.MapOperatorActivity;
import com.renbin.rxlib.MergeOperatorActivity;
import com.renbin.rxlib.OtherOperatorActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CreateOperatoeActivity(View view) {
        startActivity(new Intent(this, CreateOperatoeActivity.class));
    }

    public void MapOperatorActivity(View view) {
        startActivity(new Intent(this, MapOperatorActivity.class));
    }

    public void MergeOperatorActivity(View view) {
        startActivity(new Intent(this, MergeOperatorActivity.class));
    }

    public void FilterOperatorActivity(View view) {
        startActivity(new Intent(this, FilterOperatorActivity.class));
    }

    public void OtherOperatorActivity(View view) {
        startActivity(new Intent(this, OtherOperatorActivity.class));
    }
}