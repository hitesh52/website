package com.localexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import java.util.List;


public class CustomListAdapter extends ArrayAdapter<ListItem> {
    Context context;
    List<ListItem> listItemList;
    int ResId;

    public CustomListAdapter(Context context, int LayoutResourceID, List<ListItem> listItems) {
        super(context, LayoutResourceID, listItems);
        this.context = context;
        this.listItemList = listItems;
        this.ResId = LayoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemHolder holder;
        View view = convertView;
        if (view == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ListItemHolder();
            view = inflater.inflate(ResId, parent, false);
            holder.h = (TextView) view.findViewById(R.id.H1);
            holder.h_name = (TextView) view.findViewById(R.id.H1_NAME);
            //holder.icon = //(ImageView) view.findViewById(R.id.META_DESC);
            view.setTag(holder);
        } else {
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem listing = (ListItem) this.listItemList.get(position);
        holder.h_name.setText(listing.getCounter());
        holder.h.setText(listing.getUrl());


//        if(resid == "4"){
//            holder.icon.setImageResource(R.mipmap.ic_down);
//        }
//        if(resid == "3"){
//            holder.icon.setImageResource(R.mipmap.ic_downdown);
//
//        }
//        if(resid == "2")
//        {
//            holder.icon.setImageResource(R.mipmap.ic_up);
//        }
//        if(resid == "1")
//        {
//            holder.icon.setImageResource(R.mipmap.ic_upup);
//        }
//        if(resid == "0")
//        {
//            holder.icon.setImageResource(R.mipmap.ic_normal);
//
//        }
        return view;
    }


    private static class ListItemHolder{
       TextView h;
        TextView h_name;
    }
}
