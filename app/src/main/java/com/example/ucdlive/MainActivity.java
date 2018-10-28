package com.example.ucdlive;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.add_event_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                addEvent(view);
            }
        });

        db = FirebaseDatabase.getInstance().getReference();
        db.child("events").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListView list = (ListView) findViewById(android.R.id.list);
                ArrayList<Event> events = new ArrayList<>();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Event toAdd = childSnapshot.getValue(Event.class);
                    events.add(toAdd);
                }
                Collections.sort(events);
                EventAdapter adapter = new EventAdapter(getBaseContext(), events);
                list.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // TODO revove the item in the list ????
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText text;
        switch(item.getItemId()) {
            case R.id.map_view:
                goToMapsActivity();
            return(true);
            case R.id.settings:
                goToSettingsActivity();
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    private void goToMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void goToSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addEvent(View v){
        Random ran = new Random();
        int i = ran.nextInt();
        System.out.println("Pressed");
        String userId = db.push().getKey();
        db.child("events").child(userId)
                .setValue(new Event("Event name" + i, "Event brief" + i,
                        "Event description" + i, new Date()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getBaseContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
