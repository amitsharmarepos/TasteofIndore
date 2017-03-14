package com.samyotech.testofindia.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.samyotech.testofindia.activity.SecondActivity;
import com.samyotech.testofindia.adapter.MyAdapter;
import com.samyotech.testofindia.bean.Book;
import com.samyotech.testofindia.database.TestAdapter;

import java.util.ArrayList;

import com.samyotech.testofindia.R;

public class HomeFragment extends Fragment {
    public ArrayList<Book> blist;
    ListView listView;
    protected ImageLoader imageLoader;
    MyAdapter md;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        TestAdapter mDbHelper = new TestAdapter(getActivity());
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor testdata = mDbHelper.getTestData();
        final int category[] = new int[testdata.getCount()];

        int i = 0;
        blist = new ArrayList<Book>();
        Book b;

        if (testdata.moveToFirst()) {
            do {
                b = new Book();
                b.setCategoryid(testdata.getInt(0));
                b.setName(testdata.getString(1));
                b.setImg(testdata.getString(2));
                blist.add(b);
                //Toast.makeText(HomeActivity.this,testdata.getString(2),Toast.LENGTH_LONG).show();
                category[i] = testdata.getInt(0);

                i++;
            } while (testdata.moveToNext());
        }
        md = new MyAdapter(this, R.layout.show, blist);
        listView.setAdapter(md);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book b = blist.get(position);
                Intent in = new Intent(getActivity(), SecondActivity.class);
                in.putExtra("id", String.valueOf(category[position]));
                in.putExtra("dishname", b.getName());
                in.putExtra("img", b.getImg());
                //Toast.makeText(HomeActivity.this, String.valueOf(category[position]), Toast.LENGTH_LONG).show();
                startActivity(in);


            }
        });
        return v;
    }


}

