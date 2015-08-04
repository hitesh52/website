package com.localexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import java.util.List;


public class CustomListAdapter_speed_tips extends ArrayAdapter<ListItem_speed_tips> {
    Context context;
    List<ListItem_speed_tips> listItemList;
    int ResId;

    public CustomListAdapter_speed_tips(Context context, int LayoutResourceID, List<ListItem_speed_tips> listItems){
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
            holder.message= (TextView) view.findViewById(R.id.RECOMMENDATION);
            holder.score= (TextView) view.findViewById(R.id.TYPE);
            holder.value= (TextView) view.findViewById(R.id.PRIORITY);
            //holder.data=(TextView)view.findViewById(R.id.DATA);
            view.setTag(holder);
        }
        else{
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_speed_tips listing = (ListItem_speed_tips)this.listItemList.get(position);
        holder.message.setText(listing.getMessage());
        holder.score.setText(listing.getScore());
        //holder.value.setText(listing.getValue());
         //holder.data.setText(listing.getData());
        return view;
    }

    private static class ListItemHolder{
        TextView message;
        TextView score;
        TextView value;
        TextView data;



    }
}
