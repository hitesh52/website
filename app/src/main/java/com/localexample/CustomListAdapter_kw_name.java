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


public class CustomListAdapter_kw_name extends ArrayAdapter<ListItem_kw_name> {
    Context context;
    List<ListItem_kw_name> listItemList;
    int ResId;

    public CustomListAdapter_kw_name(Context context, int LayoutResourceID, List<ListItem_kw_name> listItems) {
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
            holder.kw_nam = (TextView) view.findViewById(R.id.kw_title);
            holder.kw_title=(TextView)view.findViewById(R.id.kw_length);
            view.setTag(holder);
        } else {
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_kw_name listing = (ListItem_kw_name) this.listItemList.get(position);
        holder.kw_nam.setText(listing.getKw_name());
        holder.kw_title.setText(listing.getKw_title());
        return view;
    }


    private static class ListItemHolder{
        TextView kw_nam;
        TextView kw_title;

    }
}
