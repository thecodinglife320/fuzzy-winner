package com.learning.ad.ff1
import android.app.Application
import com.learning.ad.ff1.database.NoteDatabase

class MyApp : Application() {
   val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
}
