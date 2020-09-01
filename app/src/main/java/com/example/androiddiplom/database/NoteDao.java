package com.example.androiddiplom.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androiddiplom.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("SELECT * FROM note")
    List<Note> getAllNote();

    @Query("SELECT * FROM note WHERE noteId = :id")
    Note getById(int id);
}
