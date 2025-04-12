package com.mohyaseensidd.studynitp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NavAdapter extends ArrayAdapter<Nav_item> {

    public NavAdapter(Context context, List<Nav_item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Nav_item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nav_layout, parent, false);
        }

        ImageView icon = convertView.findViewById(R.id.item_icon);
        TextView title = convertView.findViewById(R.id.item_text);

        icon.setImageResource(item.icon);
        title.setText(item.title);

        return convertView;
    }
}

