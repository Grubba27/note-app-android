package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button addButton = findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNote(v);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.list);
        NotaRecyclerViewAdapter notasAdapter = new NotaRecyclerViewAdapter(getNotas());
        recyclerView.setAdapter(notasAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.list);
        NotaRecyclerViewAdapter notasAdapter = new NotaRecyclerViewAdapter(getNotas());
        recyclerView.setAdapter(notasAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void addNote(View v) {
        Intent intent = new Intent(this, AdicionaNota.class);
        startActivity(intent);
    }

    public ArrayList<Nota> getNotas() {
        NotasService notasService = new NotasService(getBaseContext());
        SQLiteDatabase db = notasService.getReadableDatabase();

        Cursor cursor = db.query(NotasDB.NotasEntry.TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Nota> items = new ArrayList<Nota>();
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(NotasDB.NotasEntry._ID));
            String txt = cursor.getString(cursor.getColumnIndexOrThrow(NotasDB.NotasEntry.COLUMN_NAME_NOTA));
            items.add(new Nota(txt, itemId));
        }
        cursor.close();

        return items;
    }

}