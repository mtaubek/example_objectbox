package com.taubek.examples.objectboxexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by markus on 15.07.17.
 */

public class EntityRecyclerAdapter extends RecyclerView.Adapter<EntityRecyclerAdapter.ViewHolder> {

    List<Contact> contactList;
    Context context;
    View view1;
    ViewHolder viewHolder1;

    public EntityRecyclerAdapter(Context context, List<Contact> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFirstname;

        public ViewHolder(View v) {
            super(v);
            tvFirstname = (TextView) v.findViewById(R.id.entity_textview);
        }
    }

    @Override
    public EntityRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.entity_item, parent, false);
        viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvFirstname.setText(contactList.get(position).getFirstname());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

}
