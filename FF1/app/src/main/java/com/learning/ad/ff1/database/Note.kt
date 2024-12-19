package com.learning.ad.ff1.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "notes")
data class Note(
   @PrimaryKey(autoGenerate = true)
   val id: Long = 0,
   @ColumnInfo(name = "noteTitle")
   val title: String,
   @ColumnInfo(name = "noteContent")
   val content: String
)
