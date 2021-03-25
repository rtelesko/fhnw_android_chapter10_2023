package com.example.lotterydbtwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> location;


    public ListAdapter(
            Context contextTemp,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> location
    ) {

        this.context = contextTemp;
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return id.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.items, null);
            holder = new Holder();
            holder.tvID = child.findViewById(R.id.tvID);
            holder.tvName = child.findViewById(R.id.tvName);
            holder.tvLocation = child.findViewById(R.id.tvLocation);
            child.setTag(holder);
        } else {
            holder = (Holder) child.getTag();
        }
        holder.tvID.setText(id.get(position));
        holder.tvName.setText(name.get(position));
        holder.tvLocation.setText(location.get(position));
        return child;
    }

    public class Holder {
        TextView tvID;
        TextView tvName;
        TextView tvLocation;
    }

}