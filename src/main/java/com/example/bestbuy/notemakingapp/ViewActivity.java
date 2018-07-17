package com.example.bestbuy.notemakingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    ArrayList<String> notes;

    ListView listView;

    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView=(ListView)findViewById(R.id.list);

        getNotes();

    }

    void getNotes(){

        notes= new ArrayList<String >();
        try{

            SQLiteDatabase db=this.openOrCreateDatabase("notesDB",MODE_PRIVATE,null);
            Cursor c=db.rawQuery("select * from list ",null);
            int noteIndex= c.getColumnIndex("notetext");
            c.moveToFirst();
            while (c!=null){

                Log.i("Notes",c.getString(noteIndex));
                notes.add(c.getString(noteIndex));
                c.moveToNext();

            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {


            arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notes);
            listView.setAdapter(arrayAdapter);

        }
    }
}
