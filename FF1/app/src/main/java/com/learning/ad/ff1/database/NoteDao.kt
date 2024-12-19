package com.learning.ad.ff1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
   @Query("SELECT * FROM notes")
   fun getAllNotes(): Flow<List<Note>>
   @Query("SELECT * FROM notes WHERE id = :id")
   fun getNoteById(id: Long): Flow<Note>
   @Insert
   fun insert(note: Note)
   @Update
   fun update(note: Note)
   @Delete
   fun delete(note: Note)
}