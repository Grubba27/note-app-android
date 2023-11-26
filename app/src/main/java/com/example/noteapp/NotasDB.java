package com.example.noteapp;

import static com.example.noteapp.NotasDB.SQL_CREATE_ENTRIES;
import static com.example.noteapp.NotasDB.SQL_DELETE_ENTRIES;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class NotasDB {
    private NotasDB() {


    }

    public static class NotasEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NOTA = "nota";
    }


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NotasEntry.TABLE_NAME + " (" +
                    NotasEntry._ID + " INTEGER PRIMARY KEY," +
                    NotasEntry.COLUMN_NAME_NOTA + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NotasEntry.TABLE_NAME;


}

