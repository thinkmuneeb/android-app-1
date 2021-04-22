package com.example.app1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity
{
	ArrayList<Note> notes;
    ArrayList<Note> selectedNotes;
	Note currentNote;
	
	EditText textArea;
	CheckBox importanceCheck;

    ListView listViewCountries;
    NoteListAdapter adapter;

    final int REQUEST_CODE = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listViewCountries = (ListView) findViewById(R.id.listViewCountries);

        notes = new ArrayList<Note>();
        notes.add(new Note("Pakistan", true, 0));
        notes.add(new Note("India", true, 1));
        notes.add(new Note("USA", true, 10));
        notes.add(new Note("Canada", false, 8));
        notes.add(new Note("Maldives", false, 7));
        notes.add(new Note("Afghanistan", false, 2));
        notes.add(new Note("Nigeria", false, 6));

        selectedNotes = new ArrayList<Note>();
        for(Note n: notes){
            if(n.isImportant())
                selectedNotes.add(n);
        }
        adapter = new NoteListAdapter(this,selectedNotes, false);
        listViewCountries.setAdapter(adapter);

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        try{
           savedInstanceState.putSerializable("noteslist",notes);
        }
        catch(Exception ex){ }         

    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        try{
           notes = (ArrayList<Note>) savedInstanceState.getSerializable("noteslist");
        }
        catch(Exception ex){ }

    }
    
    public void buttonClick(View v){

        if(v.getId() == R.id.button_save){
           saveNote();
        }

        if(v.getId() == R.id.button_new){
           newNote();
        }

        if(v.getId() == R.id.button_list){
           listNotes();
        }

    }
    
    public void checkboxClick(View v){
    	if(currentNote != null){
	    
    		boolean checked = ((CheckBox) v).isChecked();
    		currentNote.setImportance(checked);
	    		    	
    	}
    }
    
    private void saveNote(){
    	   String content = textArea.getText().toString();

    	   if(currentNote == null){
    		 currentNote = new Note(content);
    		 notes.add(currentNote);
    	   }

    	   currentNote.setImportance(importanceCheck.isChecked());
    	   currentNote.setContent(content);
    	
        showMessage("Note saved successfully");
    }
    
    private void newNote(){
    	   saveNote();
    	   textArea.setText("");
    	   importanceCheck.setChecked(false);    	  
    	   currentNote = null;
    }
     
    private void listNotes(){
    	   Intent intent = new Intent(this, SearchActivity.class);
    	   intent.putExtra("list",notes);
    	   startActivityForResult(intent,REQUEST_CODE);
    }

    private void showMessage(String message){
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_SHORT);
        toast.show();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       	super.onActivityResult(requestCode, resultCode, data);
       	if(requestCode != RESULT_CANCELED){
            Log.d("TAG", "onActivityResult: requestCode != RESULT_CANCELED");
        }
       	if(requestCode == REQUEST_CODE){
       		if(resultCode == RESULT_OK){
       			notes = (ArrayList<Note>) data.getSerializableExtra("list");
       			adapter.notifyDataSetChanged();

       			selectedNotes = new ArrayList<Note>();
       			for(Note n: notes){
       			    if(n.isImportant())
       			        selectedNotes.add(n);
                }
                adapter = new NoteListAdapter(this,selectedNotes, false);
                listViewCountries.setAdapter(adapter);

                Log.d("TAG", "onActivityResult: selectedNotes size: " + selectedNotes.size());

//       			int selectedItemIndex = data.getIntExtra("selecteditemindex", -1);
//       			if(selectedItemIndex != -1){
//       				setNote(selectedItemIndex);
//       			}
       		}
       	}
    }
    
    private void setNote(int index){
    	currentNote = notes.get(index);
    	textArea.setText(currentNote.getContent());
    	importanceCheck.setChecked(currentNote.isImportant());
    }
}
