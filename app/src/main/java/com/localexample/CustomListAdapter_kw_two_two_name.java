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


public class CustomListAdapter_kw_two_two_name extends ArrayAdapter<ListItem_kw_two_two_name> {
    Context context;
    List<ListItem_kw_two_two_name> listItemList;
    int ResId;

    public CustomListAdapter_kw_two_two_name(Context context, int LayoutResourceID, List<ListItem_kw_two_two_name> listItems) {
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
            holder.twokw_nam = (TextView) view.findViewById(R.id.one);
            holder.twotitle=(ImageView)view.findViewById(R.id.third);
            holder.twodesc=(ImageView)view.findViewById(R.id.fourth);
            holder.twokw_content=(TextView)view.findViewById(R.id.fifth);
            holder.twofre=(TextView)view.findViewById(R.id.second);
            view.setTag(holder);
        } else {
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_kw_two_two_name listing = (ListItem_kw_two_two_name) this.listItemList.get(position);
        holder.twokw_nam.setText(listing.getKw_twoname());

        String resid_title = listing.getKw_twotitle();

        if(resid_title == "0"){
            holder.twotitle.setImageResource(R.drawable.major);
        }
        else
        {
            holder.twotitle.setImageResource(R.drawable.pass);
        }


        String resid = listing.getKw_twodesc();

        if(resid == "0"){
            holder.twodesc.setImageResource(R.drawable.major);
        }
        else
        {
            holder.twodesc.setImageResource(R.drawable.pass);
        }



        holder.twokw_content.setText(listing.getKw_twocontent());
        String freq_percen=listing.gettwoFreq();
        freq_percen=freq_percen+"%";
        holder.twofre.setText(freq_percen);

        return view;
    }



    private static class ListItemHolder{
        TextView twokw_nam;
        TextView twokw_content;
        ImageView twodesc;
        ImageView twotitle;
        TextView twofre;

    }
}
