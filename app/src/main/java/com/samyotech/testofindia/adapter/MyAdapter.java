package com.samyotech.testofindia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.samyotech.testofindia.bean.Book;

import java.util.ArrayList;

import com.samyotech.testofindia.R;

import com.samyotech.testofindia.fragment.HomeFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by samyotech on 28/12/16.
 */

public class MyAdapter extends BaseAdapter {
    ArrayList<Book> blist = null;
    HomeFragment homeFragment;
    Context context;

    public MyAdapter(HomeFragment homeFragment, int textViewResourceId,
                     ArrayList<Book> blist) {
        super();

        this.blist = blist;
        this.homeFragment = homeFragment;
        this.context = homeFragment.getContext();

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return blist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub

        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lif = LayoutInflater.from(context);
        //LayoutInflater lif = context.getLayoutInflater();
        View v = lif.inflate(R.layout.show, null);
        CircleImageView imageView = (CircleImageView) v.findViewById(R.id.imageView);
        TextView title = (TextView) v.findViewById(R.id.title);


        Book b = blist.get(position);

        title.setText(b.getName());
        ImageLoader.getInstance().displayImage(b.getImg(), imageView);
        blist.get(position);
        return v;
    }


}
