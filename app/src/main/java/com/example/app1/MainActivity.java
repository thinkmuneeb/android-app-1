package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        notes = (ArrayList<Note>) intent.getSerializableExtra("list");

        if(notes == null){
            notes = new ArrayList<Note>();
            notes.add(new Note("Pakistan"));
            notes.add(new Note("India"));
        }

        createView();
    }

    private void createView(){
        ListView view = new ListView(this);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        String [] array = new String[notes.size()];
        for(int i=0; i < notes.size(); i++){
            array[i] = notes.get(i).getContent();
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.simple_list,array);
        view.setAdapter(adapter);
        setContentView(view);
    }
}