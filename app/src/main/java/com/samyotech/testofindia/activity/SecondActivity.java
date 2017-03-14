package com.samyotech.testofindia.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.samyotech.testofindia.database.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import com.samyotech.testofindia.R;
import com.samyotech.testofindia.adapter.DishAdapter;
import com.samyotech.testofindia.bean.Book1;


public class SecondActivity extends AppCompatActivity {
    protected ImageLoader imageLoader;
    private RecyclerView recyclerView;
    private DishAdapter adapter;
    private List<Book1> blist;
    String imgss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Intent in = getIntent();
        String id = in.getStringExtra("id");
        imgss = in.getStringExtra("img");
        toolbar.setTitle(in.getStringExtra("dishname"));
        setSupportActionBar(toolbar);
        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        final Cursor testdata = mDbHelper.getData(id);

        final String name[] = new String[testdata.getCount()];
        int i = 0;
        blist = new ArrayList<Book1>();
        Book1 b;

        if (testdata.moveToFirst()) {

            do {
                b = new Book1();
                b.setCategoryid(testdata.getInt(0));
                b.setQue(testdata.getString(1));
                b.setAns(testdata.getString(2));
                //b.setImg(testdata.getString(3));
                blist.add(b);
                name[i++] = testdata.getString(2);


            } while (testdata.moveToNext());
        }
        adapter = new DishAdapter(this, blist);

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);


        try {
            ImageLoader.getInstance().displayImage(imgss, (ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
