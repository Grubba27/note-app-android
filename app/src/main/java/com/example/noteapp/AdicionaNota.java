package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionaNota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_nota);
        final Button addButton = findViewById(R.id.add_nota);
        final EditText nota = findViewById(R.id.nota_txt);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nota_txt = nota.getText().toString();
                if (nota_txt.equals("")) {
                    Toast
                            .makeText(AdicionaNota.this, "Não pode adicionar nota vazia!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                addNota(nota_txt);
            }
        });


    }

    public void addNota(String txt) {
        NotasService notasService = new NotasService(getBaseContext());
        SQLiteDatabase db = notasService.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotasDB.NotasEntry.COLUMN_NAME_NOTA, txt);
        Long row = db.insert(NotasDB.NotasEntry.TABLE_NAME, null, values);
        Toast
                .makeText(AdicionaNota.this, "Adicionado nota!", Toast.LENGTH_SHORT)
                .show();
        finish();
    }

    public void voltar() {
        AdicionaNota.super.getOnBackPressedDispatcher();
    }
}