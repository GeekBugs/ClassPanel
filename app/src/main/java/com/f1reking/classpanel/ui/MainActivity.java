package com.f1reking.classpanel.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.f1reking.classpanel.R;
import com.f1reking.classpanel.entity.ClassEntity;
import com.f1reking.classpanel.widget.GridViewGallery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llGallery;
    private GridViewGallery mGallery;

    private List<ClassEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = getData();
        initView();
    }

    private void initView() {
        llGallery = (LinearLayout) findViewById(R.id.ll_gallery);
        mGallery = new GridViewGallery(MainActivity.this, list);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        llGallery.addView(mGallery, params);
    }

    public List<ClassEntity> getData(){
        list = new ArrayList<ClassEntity>();

        for(int i= 0;i<23;i++){
            int j = i+1;
            ClassEntity w = new ClassEntity(("第"+j+"个"),R.mipmap.ic_diancang);
            list.add(w);
        }
        return list;
    }
}
