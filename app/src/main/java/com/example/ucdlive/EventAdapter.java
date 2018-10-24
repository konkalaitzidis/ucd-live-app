package com.example.ucdlive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>{

    private Context mContext;
    private List<Event> events;

    public EventAdapter(Context context, ArrayList<Event> list) {
        super(context, 0 , list);
        mContext = context;
        events = list;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.event,parent,false);

        Event currentEvent = this.events.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.event_name);
        name.setText(currentEvent.getName());

        return listItem;
    }

}
