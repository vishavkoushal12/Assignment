package com.example.bestbuy.notemakingapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText notes;
    Button addNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        notes=(EditText)findViewById(R.id.noteText);
        addNote=(Button)findViewById(R.id.addButton);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Note=notes.getText().toString();

                NoteAdd(Note);
            }
        });
    }

    void NoteAdd(String notes){

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("notesDB", MODE_PRIVATE, null);
            db.execSQL("create table if not exists list (notetext VARCHAR)");


            db.execSQL("insert into list values('" + notes + "')");
            Log.i("notes", "Success");
            Toast.makeText(getApplicationContext(), "Notes added successfully", Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
