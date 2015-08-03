package com.localexample;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.List;


public class CustomListAdapter_speed extends ArrayAdapter<ListItem_speed> {
    Context context;
    List<ListItem_speed> listItemList;
    int ResId;

    public CustomListAdapter_speed(Context context, int LayoutResourceID, List<ListItem_speed> listItems){
        super(context, LayoutResourceID, listItems);
        this.context = context;
        this.listItemList = listItems;
        this.ResId = LayoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListItemHolder holder;
        View view = convertView;
        if(view == null){

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ListItemHolder();
            view = inflater.inflate(ResId, parent, false);
           holder.obj_name= (TextView) view.findViewById(R.id.COMPONENT);
            holder.request= (TextView) view.findViewById(R.id.REQUEST);
            holder.size= (TextView) view.findViewById(R.id.SIZE);
            holder.img=(ImageView)view.findViewById(R.id.imageView_first);

            view.setTag(holder);
        }
        else{
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_speed listing = (ListItem_speed)this.listItemList.get(position);
        holder.obj_name.setText(listing.getObj_name());
        holder.request.setText(listing.getRequest());
        holder.size.setText(listing.getSpeed());
         String id =listing.getObj_name();
        if(id=="doc"|| id== "font"||id=="favicon"||id=="iframe")
        {
            holder.img.setImageResource(R.drawable.icon1);
        }
        if(id=="cssimage"|| id== "css")
        {
            holder.img.setImageResource(R.drawable.icon3);

        }
        if(id=="image")
        {
            holder.img.setImageResource(R.drawable.icon4);
        }
        if(id=="js")
        {
            holder.img.setImageResource(R.drawable.icon2);
        }
        return view;
    }

    private static class ListItemHolder{
        TextView obj_name;
        TextView request;
        TextView size;
        ImageView img;

    }
}
