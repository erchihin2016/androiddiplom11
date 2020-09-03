package com.example.androiddiplom.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androiddiplom.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDataBase extends RoomDatabase {
    public abstract NoteDao getNoteDao();
}