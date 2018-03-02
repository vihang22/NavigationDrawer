package com.ckeeda.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ckeeda.navigationdrawer.Model.Flower;

import java.util.List;

/**
 * Created by HP on 24-Jan-18.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.MyViewHolder> {

    interface OnItemClickListener{}

    List<Flower> list;
    Context context;
    OnItemClickListener listener;

    public FlowerAdapter(Context context, List<Flower> list ) {
        this.list = list;
        this.context = context;

    }

    @Override
    public FlowerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FlowerAdapter.MyViewHolder holder, int position) {
        holder.text.setText(list.get(position).getName());



    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        else
           return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            text = itemView.findViewById(R.id.recycler_item_tv);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Clicked.." ,Toast.LENGTH_SHORT).show();
        }
    }
}
