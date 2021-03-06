package com.example.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity
{
	ArrayList<Note> notes;
	int selectedItem;
	
	EditText text;
	ListView list;
	NoteListAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        notes = (ArrayList<Note>) intent.getSerializableExtra("list");

        /// @dev this is used if some ones opens this screen directly from code. He will not receive this data intent.getSerializableExtra("list"); so he will render this other given data.
        if(notes == null){
        	notes = new ArrayList<Note>();
			notes.add(new Note("BMW"));
			notes.add(new Note("TESLA"));
        }
        
        selectedItem = -1;        
        createView();
    }
    
    private EditText createText(){
    	text = new EditText(this);
    	text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
    	text.setHint("Filter");
    	text.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) { }

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { }

			@Override
			public void onTextChanged(CharSequence text, int start, int before,int count) {
				adapter.getFilter().filter(text.toString());
			}
    	});
    	return text;
    }
    
    private ListView createList(){
    	list = new ListView(this);
    	list.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	    	
        adapter = new NoteListAdapter(this,notes, true);
    	list.setAdapter(adapter);    	
    	
    	list.setOnItemClickListener(new OnItemClickListener() {
       		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				Log.d("TAG", "onItemClick: " + position);
       			selectedItem = position;
//    			prepareResult();
//    			finish();
    		}
		});
    	
    	return list;
    }
    
    private void createView(){
    	LinearLayout layout = new LinearLayout(this);
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    	layout.setFocusable(true);
    	layout.setFocusableInTouchMode(true);
    	
    	
    	layout.addView(createText());
    	layout.addView(createList());
    	
    	setContentView(layout);
    }
    
    private void prepareResult(){
    	Intent intent = new Intent();
    	intent.putExtra("list", notes);
    	intent.putExtra("selecteditemindex", selectedItem);
    	setResult(RESULT_OK, intent);
    }
    
    @Override
    public void onBackPressed() {
    	prepareResult();
    	super.onBackPressed();
    }
    
    
}
