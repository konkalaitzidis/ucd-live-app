package com.example.ucdlive;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventsFragment extends ListFragment implements AdapterView.OnItemClickListener {

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_events, container, false);
            return view;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            /*ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.Events, android.R.layout.simple_list_item_1);*/
            /*ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getContext(), android.R.layout.simple_list_item_1);
            for(int i=0; i<20; i++){
                adapter.add(new Event(i, "Event " + i, "Description " + i, LocalDateTime.now()));
            }
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);*/

            ArrayList<Event> events = new ArrayList<>();
            for(int i=0; i<20; i++){
                events.add(new Event(i, "Event " + i, "Description " + i, LocalDateTime.now()));
            }
            EventAdapter adapter = new EventAdapter(getContext(), R.layout.event, events);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Toast.makeText(getActivity(), "Item: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
        }
}
