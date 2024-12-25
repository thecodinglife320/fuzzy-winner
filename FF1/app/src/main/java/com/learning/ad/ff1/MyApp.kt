package com.learning.ad.ff1
import android.app.Application

class MyApp : Application() {
   val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
}
