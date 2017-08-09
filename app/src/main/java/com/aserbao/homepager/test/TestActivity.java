package com.aserbao.homepager.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aserbao.homepager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    private List<Integer> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            mList.add(i);
        }
    }

    public void showClick(View view) {
        int s = 5;
        /*for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i) == s) {
                mList.remove(i);
            }
        }*/
        mList.set(4,100);
        Toast.makeText(this, mList.toString()+ "\n"+mList.get(4), Toast.LENGTH_SHORT).show();
    }
}
