package com.learning.ad.ff1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.learning.ad.ff1.database.Note
import com.learning.ad.ff1.database.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val dao:NoteDao): ViewModel() {
   val allNotes = dao.getAllNotes().asLiveData()
   private val selectedNote = MutableLiveData<Note>()
   val noteTitle = MutableLiveData("")
   val noteContent = MutableLiveData("")
   fun getNote() = dao.getNoteById(selectedNote.value!!.id)
   fun isEntryValid(noteTitle: String, noteContent: String): Boolean = !(noteTitle.isBlank() || noteContent.isBlank())
   fun addNote() =viewModelScope.launch(Dispatchers.IO) { dao.insert(Note(title =  noteTitle.value!!, content = noteContent.value!!)) }
   fun updateNote(note: Note) = viewModelScope.launch { dao.update(note) }
   fun deleteNote(note: Note) = viewModelScope.launch { dao.delete(note) }
}