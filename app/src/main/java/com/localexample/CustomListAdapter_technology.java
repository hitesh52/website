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


public class CustomListAdapter_technology extends ArrayAdapter<ListItem_technology> {
    Context context;
    List<ListItem_technology> listItemList;
    int ResId;

    public CustomListAdapter_technology(Context context, int LayoutResourceID, List<ListItem_technology> listItems){
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
            holder.tech_name= (TextView) view.findViewById(R.id.TECHNOLOGY_NAME);
            holder.tech_image= (ImageView) view.findViewById(R.id.TECHNOLOGY_IMAGE);
            view.setTag(holder);
        }
        else{
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_technology listing = (ListItem_technology)this.listItemList.get(position);
        holder.tech_name.setText(listing.getTech_used());
        String res =listing.getTech_used();
        String final1 =res.toLowerCase();
        String final2 =final1.replace(" ","");
        int identifier = context.getResources().getIdentifier(final2, "drawable", "com.localexample.website_analyser");
        holder.tech_image.setImageResource(identifier);

        return view;
    }

    private static class ListItemHolder{
       TextView tech_name;
        ImageView tech_image;
    }
}
