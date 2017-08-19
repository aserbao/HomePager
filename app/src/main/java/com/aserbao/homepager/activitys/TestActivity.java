package com.aserbao.homepager.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.homepager.MainActivity;
import com.aserbao.homepager.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
    }

    public void jump(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
