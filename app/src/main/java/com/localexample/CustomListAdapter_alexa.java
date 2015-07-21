package com.localexample;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import java.util.List;


public class CustomListAdapter_alexa extends ArrayAdapter<ListItem_alexa> {
    Context context;
    List<ListItem_alexa> listItemList;
    int ResId;

    public CustomListAdapter_alexa(Context context, int LayoutResourceID, List<ListItem_alexa> listItems){
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
           holder.country_name= (TextView) view.findViewById(R.id.COUNTRY_NAME);
            holder.percentage= (TextView) view.findViewById(R.id.PERCENTAGE);
            holder.value= (ProgressBar) view.findViewById(R.id.progressBar);
            holder.flag=(ImageView)view.findViewById(R.id.imageView5);
            view.setTag(holder);
        }
        else{
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_alexa listing = (ListItem_alexa)this.listItemList.get(position);
        holder.country_name.setText(listing.getCountry_name());
        holder.percentage.setText(listing.getCountry_value());
        String country = listing.getCountry_name();
        String final1 =country.toLowerCase();
        int identifier = context.getResources().getIdentifier(final1, "drawable", "com.localexample.website_app");
        holder.flag.setImageResource(identifier);
        String val=listing.getCountry_value();
        String val_final=val.replace("%", "");
        ;
        Float f = Float.parseFloat(val_final);
        holder.value.setScrollBarStyle(ProgressBar.SCROLLBARS_OUTSIDE_INSET);
        holder.value.setVisibility(View.VISIBLE);
        holder.value.setProgress(0);
        holder.value.setMax(100);
        int b=(int)(Math.round(f));
        holder.value.incrementProgressBy(b);

        return view;
    }



    private static class ListItemHolder{
        ImageView flag;
        TextView country_name;
        TextView percentage;
        ProgressBar value;



    }
}
