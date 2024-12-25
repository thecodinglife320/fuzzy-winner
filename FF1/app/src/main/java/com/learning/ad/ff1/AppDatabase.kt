package com.learning.ad.ff1

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
   abstract fun noteDao(): NoteDao
   companion object {
      @Volatile
      private var INSTANCE: NoteDatabase? = null
      fun getDatabase(context: Context): NoteDatabase {
         return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
               context.applicationContext,
               NoteDatabase::class.java,
               "note_database"
            )
               .fallbackToDestructiveMigration()
               .build()
            INSTANCE = instance
            return instance
         }
      }
   }
}
@Dao
interface NoteDao {
   @Query("SELECT * FROM notes")
   fun getAllNotes(): Flow<List<Note>>
   @Query("SELECT * FROM notes WHERE id = :id")
   fun getNoteById(id: Long): Flow<Note>
   @Insert
   suspend fun insert(note: Note)
   @Update
   suspend fun update(note: Note)
   @Delete
   suspend fun delete(note: Note)
}
@Entity(tableName = "notes")
data class Note(
   @PrimaryKey(autoGenerate = true)
   val id: Long = 0,
   @ColumnInfo(name = "noteTitle")
   var title: String,
   @ColumnInfo(name = "noteContent")
   var content: String
)
