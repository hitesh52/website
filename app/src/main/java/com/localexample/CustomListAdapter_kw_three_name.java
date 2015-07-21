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


public class CustomListAdapter_kw_three_name extends ArrayAdapter<ListItem_kw_three_name> {
    Context context;
    List<ListItem_kw_three_name> listItemList;
    int ResId;

    public CustomListAdapter_kw_three_name(Context context, int LayoutResourceID, List<ListItem_kw_three_name> listItems) {
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
            holder.kw_nam = (TextView) view.findViewById(R.id.kw_3_fifth_1);
            holder.title=(ImageView)view.findViewById(R.id.TITLE3);
            holder.desc=(ImageView)view.findViewById(R.id.DESC3);
            holder.kw_content=(TextView)view.findViewById(R.id.kw_3_fifth_5);
            holder.fre=(TextView)view.findViewById(R.id.kw_3_fifth_2);
            view.setTag(holder);
        } else {
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_kw_three_name listing = (ListItem_kw_three_name) this.listItemList.get(position);
        holder.kw_nam.setText(listing.getKw3_name());

        String resid_title = listing.getKw3_title();

        if(resid_title == "0"){
            holder.title.setImageResource(R.drawable.major);
        }
        else
        {
            holder.title.setImageResource(R.drawable.pass);
        }


        String resid = listing.getKw3_desc();

        if(resid == "0"){
            holder.desc.setImageResource(R.drawable.major);
        }
        else
        {
            holder.desc.setImageResource(R.drawable.pass);
        }



        holder.kw_content.setText(listing.getKw3_content());
        String freq_percen=listing.getFreq3();
        freq_percen=freq_percen+"%";
        holder.fre.setText(freq_percen);

        return view;
    }



    private static class ListItemHolder{
        TextView kw_nam;
        TextView kw_content;
        ImageView desc;
        ImageView title;
        TextView fre;

    }
}
