package com.samyotech.testofindia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.samyotech.testofindia.activity.ThirdActivity;
import com.samyotech.testofindia.bean.Book1;

import java.util.List;

import com.samyotech.testofindia.R;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {

    private Context mContext;
    private List<Book1> blist;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView image;
        public RelativeLayout touchlay;
        public Book1 b;

        public MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
            image = (ImageView) view.findViewById(R.id.image);
            touchlay = (RelativeLayout) view.findViewById(R.id.touchlay);

        }
    }


    public DishAdapter(Context mContext, List<Book1> blist) {
        this.mContext = mContext;
        this.blist = blist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.b = blist.get(position);
        holder.text.setText(holder.b.getQue());
        holder.image.setImageResource(R.drawable.ic_launcher);
        holder.touchlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, ThirdActivity.class);

                in.putExtra("ans", holder.b.getAns());
                mContext.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blist.size();
    }

}
