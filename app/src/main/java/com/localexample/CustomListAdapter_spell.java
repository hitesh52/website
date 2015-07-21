package com.localexample;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


public class CustomListAdapter_spell extends ArrayAdapter<ListItem_spell> {
    Context context;
    List<ListItem_spell> listItemList;
    int ResId;

    public CustomListAdapter_spell(Context context, int LayoutResourceID, List<ListItem_spell> listItems){
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
           holder.wrong= (TextView) view.findViewById(R.id.Substitution);
            holder.substitution= (TextView) view.findViewById(R.id.Suggestion);
            holder.info= (TextView) view.findViewById(R.id.INFORMATION);
            view.setTag(holder);
        }
        else{
            holder = (ListItemHolder) view.getTag();
        }
        holder = (ListItemHolder) view.getTag();
        ListItem_spell listing = (ListItem_spell)this.listItemList.get(position);
        holder.wrong.setText(listing.getWrong_word());
        holder.substitution.setText(listing.getSubstitute());
        String inf=listing.getInfo();
        try {
            String result = URLDecoder.decode(inf, "UTF-8");
            String result2 = Html.fromHtml((String) result).toString();
            holder.info.setText(result2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




        return view;
    }

    private static class ListItemHolder{
        TextView wrong;
        TextView substitution;
        TextView info;

    }
}
