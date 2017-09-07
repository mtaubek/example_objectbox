package com.taubek.examples.objectboxexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * Created by markus on 15.07.17.
 */

public class EntityRecyclerAdapter extends RecyclerView.Adapter<EntityRecyclerAdapter.ViewHolder> {

    List<Contact> contactList;
    Context context;
    private OnClickDelete onClickDelete;

    public EntityRecyclerAdapter(Context context, List<Contact> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFirstname;
        public Button button;

        public ViewHolder(View v) {
            super(v);
            tvFirstname = (TextView) v.findViewById(R.id.entity_textview);
            button = (Button) v.findViewById(R.id.entity_delete_btn);
        }
    }

    @Override
    public EntityRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.entity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvFirstname.setText(contactList.get(position).getFirstname());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDelete.onDelete(contactList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setOnClickDelete(OnClickDelete onClickDelete) {
        this.onClickDelete = onClickDelete;
    }

    interface OnClickDelete {
        void onDelete(Contact contact);
    }
}
