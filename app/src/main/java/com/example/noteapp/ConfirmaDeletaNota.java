package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmaDeletaNota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_deleta_nota);
        Intent intent = getIntent();
        String txt = intent.getStringExtra(NotaRecyclerViewAdapter.TXT);
        String id = intent.getStringExtra(NotaRecyclerViewAdapter.ID);
        TextView textView = findViewById(R.id.nota);
        textView.setText(txt);

        final Button confirmDelete = findViewById(R.id.deletar);
        confirmDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteNota(id);
                Toast
                        .makeText(v.getContext(), "Deletando", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });
    }

    void deleteNota(String id) {
        NotasService notasService = new NotasService(getBaseContext());
        SQLiteDatabase db = notasService.getWritableDatabase();
        db.delete(NotasDB.NotasEntry.TABLE_NAME, "_id=" + id, null);
    }

    public void voltar() {
        ConfirmaDeletaNota.super.getOnBackPressedDispatcher();
    }

}