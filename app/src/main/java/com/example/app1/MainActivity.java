package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] array = {"Volvo", "BMW", "Ford", "Mazda"};

        ListView view = new ListView(this);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.simple_list,array);
        view.setAdapter(adapter);
        setContentView(view);

    }
}