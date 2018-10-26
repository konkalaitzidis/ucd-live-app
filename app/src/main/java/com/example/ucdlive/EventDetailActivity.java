package com.example.ucdlive;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class EventDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();
        Event event = (Event) intent.getExtras().getSerializable("com.example.ucdlive.MESSAGE");

        TextView txt = findViewById(R.id.event_txt);
        txt.setText(event.getName() + " -- " + event.getBrief());
    }
}
