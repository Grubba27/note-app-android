package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditaNota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_nota);
        Intent intent = getIntent();
        String txt = intent.getStringExtra(NotaRecyclerViewAdapter.TXT);
        String id = intent.getStringExtra(NotaRecyclerViewAdapter.ID);
        EditText editText = findViewById(R.id.nota_txt);
        editText.setText(txt);

        final Button editBtn = findViewById(R.id.edit_nota);
        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nota_txt = editText.getText().toString();
                if (nota_txt.equals("")) {
                    Toast
                            .makeText(v.getContext(), "Não pode editar nota vazia!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                editNota(id, nota_txt);
            }
        });


    }

    public void editNota(String id, String txt) {
        NotasService notasService = new NotasService(getBaseContext());
        SQLiteDatabase db = notasService.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotasDB.NotasEntry.COLUMN_NAME_NOTA, txt);
        db.update(NotasDB.NotasEntry.TABLE_NAME, values, "_id=" + id, null);
        Toast
                .makeText(EditaNota.this, "Nota Editada!", Toast.LENGTH_SHORT)
                .show();
        finish();
    }

    public void voltar() {
        EditaNota.super.getOnBackPressedDispatcher();
    }
}