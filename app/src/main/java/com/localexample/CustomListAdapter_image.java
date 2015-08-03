package com.localexample;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import java.util.List;


public class CustomListAdapter_image extends ArrayAdapter<ListItem_image> {
    Context context;
    List<ListItem_image> listItemList;
    int ResId;

    public CustomListAdapter_image(Context context, int LayoutResourceID, List<ListItem_image> listItems) {
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
            holder.imag = (TextView) view.findViewById(R.id.IMAGE);
            holder.img=(ImageView)view.findViewById(R.id.imageView9);
            view.setTag(holder);
        } else {
            holder = (ListItemHolder) view.getTag();
        }

        holder = (ListItemHolder) view.getTag();
        ListItem_image listing = (ListItem_image) this.listItemList.get(position);
        holder.imag.setText(listing.getImage());
        String imgid=listing.getImage();
        Log.v("IMAGE",imgid);

        return view;
    }


    private static class ListItemHolder{
        TextView imag;
        ImageView img;


    }
}
