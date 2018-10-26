package com.example.ucdlive;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public ArrayList<Event> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getListView();
        events = new ArrayList<>();
        for(int i=0; i<20; i++){
            events.add(new Event(i, "Name " + i, "Brief " +i, "Desctiption " + i, LocalDateTime.now()));
        }

        EventAdapter adapter = new EventAdapter(getContext(), events);
        listView.setAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), EventDetailActivity.class);
        intent.putExtra("com.example.ucdlive.MESSAGE", this.events.get(position));
        startActivity(intent);
    }
}
