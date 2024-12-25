package com.learning.ad.ff1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.learning.ad.ff1.databinding.ActivityFlashcardsBinding
import com.learning.ad.ff1.databinding.FragmentAddNoteBinding
import com.learning.ad.ff1.databinding.FragmentEditNoteBinding
import com.learning.ad.ff1.databinding.FragmentMyNoteBinding
import com.learning.ad.ff1.databinding.FragmentViewNoteBinding
import com.learning.ad.ff1.databinding.NoteCardLayoutBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max

class FlashcardsActivity : AppCompatActivity() {
   private lateinit var binding: ActivityFlashcardsBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityFlashcardsBinding.inflate(layoutInflater)
      setContentView(binding.root)
      setSupportActionBar(binding.toolbar)
      val navView: BottomNavigationView = binding.bottomNavigationView
      val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav_flash_card) as NavHostFragment
      val navController = navHostFragment.navController
      val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_my_note, R.id.navigation_add_note))
      setupActionBarWithNavController(navController, appBarConfiguration)
      navView.setupWithNavController(navController)
   }
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      menuInflater.inflate(R.menu.menu_daynight, menu)
      return true
   }

}
class AddNoteFragment : Fragment() {
   private var _binding : FragmentAddNoteBinding? = null
   private val binding get() = _binding!!
   private val viewModel: NoteViewModel by activityViewModels { NoteViewModelFactory((requireActivity().application as MyApp).database.noteDao()) }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentAddNoteBinding.inflate(layoutInflater,container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.saveBtn.setOnClickListener {
         viewModel.addNote()
      }
      viewModel.noteTitle.value = ""
      viewModel.noteContent.value=""
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class MyNoteFragment : Fragment() {
   private var _binding: FragmentMyNoteBinding? = null
   private val binding get() = _binding!!
   private val viewModel: NoteViewModel by activityViewModels { NoteViewModelFactory((requireActivity().application as MyApp).database.noteDao()) }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentMyNoteBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val notesViewPager = binding.notesviewPager
      notesViewPager.setPageTransformer(ViewTransformer())
      val adapter = NoteAdapter { note->
         run {
            viewModel.selectedNote.value = note
            findNavController().navigate(R.id.action_navigation_my_note_to_viewNoteFragment)
         }
      }
      notesViewPager.adapter = adapter

      viewModel.allNotes.observe(viewLifecycleOwner) {
         if (it.isEmpty()) {
            binding.emptyLb.visibility = View.VISIBLE
         } else {
            binding.emptyLb.visibility = View.GONE
         }
         adapter.submitList(it)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class ViewNoteFragment:BottomSheetDialogFragment(){
   private var _binding:FragmentViewNoteBinding?=null
   private val binding get() = _binding!!
   private val viewModel:NoteViewModel by activityViewModels{
      NoteViewModelFactory((requireActivity().application as MyApp).database.noteDao())
   }
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_note, container,false)
      binding.lifecycleOwner = viewLifecycleOwner
      lifecycle.coroutineScope.launch {
         viewModel.getNote().collect {
            binding.note = it
         }
      }
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.btnDelete.setOnClickListener {
         viewModel.selectedNote.value?.let { viewModel.deleteNote(it) }
         Toast.makeText(requireContext(), R.string.note_deleted, Toast.LENGTH_SHORT).show()
         dismiss()
      }
      binding.btnEdit.setOnClickListener { showEditDialog() }
   }
   private fun showEditDialog() =findNavController().navigate(R.id.action_viewNoteFragment_to_editNoteFragment)
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class EditNoteFragment:BottomSheetDialogFragment(){
   private var _binding: FragmentEditNoteBinding? = null
   private val binding get() = _binding!!
   private val viewModel: NoteViewModel by activityViewModels {
      NoteViewModelFactory((requireActivity().application as MyApp).database.noteDao())
   }
   private var note = Note(title = "g", content = "j")
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
      binding.viewmodel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.fabUpdate.setOnClickListener {
         viewModel.updateNote(note)
         Toast.makeText(requireContext(), R.string.note_updated, Toast.LENGTH_SHORT).show()
         findNavController().navigateUp()
      }
      viewModel.getNote().asLiveData().observe(viewLifecycleOwner){
         note = it
         viewModel.noteTitle.value = note.title
         viewModel.noteContent.value = note.content
      }
   }
}
class ViewTransformer : ViewPager2.PageTransformer {
   override fun transformPage(page: View, position: Float) {
      val height = page.height.toFloat()
      val width = page.width.toFloat()
      val scale = min(if (position < 0.0f) 1.0f else abs(1.0f - position))
      page.scaleX = scale
      page.scaleY = scale
      page.pivotX = width * 0.5f
      page.pivotY = height * 0.5f
      page.translationX = if (position < 0.0f) width * position else -width * position * 0.25f
   }
   companion object {
      private fun min(value: Float, min: Float = .5f): Float {
         return max(value, min)
      }
   }
}

class NoteAdapter(private val onItemClicked: (Note) -> Unit) :
   ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallback) {

   class NoteViewHolder(private var binding: NoteCardLayoutBinding) :
      RecyclerView.ViewHolder(binding.root) {
      fun bind(note: Note, onItemClicked: (Note) -> Unit) {
         binding.apply {
            textTitle.text = note.title
            textContent.text = note.content
            ivViewNote.setOnClickListener {
               onItemClicked(note)
            }
         }
      }
   }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = NoteViewHolder(NoteCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.bind(getItem(position), onItemClicked)
   companion object {
      private val DiffCallback = object : DiffUtil.ItemCallback<Note>() {
         override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
         }
         override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
         }
      }
   }
}
class NoteViewModelFactory(private val dao: NoteDao) : ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
         @Suppress("UNCHECKED_CAST")
         return NoteViewModel(dao) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class")
   }
}
class NoteViewModel(private val dao: NoteDao): ViewModel() {
   val allNotes = dao.getAllNotes().asLiveData()
   val selectedNote = MutableLiveData<Note>()
   val noteTitle = MutableLiveData("")
   val noteContent = MutableLiveData("")
   fun getNote(): Flow<Note> = dao.getNoteById(selectedNote.value!!.id)
   fun addNote() {
      viewModelScope.launch {
         dao.insert(Note(title =  noteTitle.value!!, content = noteContent.value!!))
      }
   }
   fun updateNote(note: Note) {
      note.title = noteTitle.value!!
      note.content = noteContent.value!!
      viewModelScope.launch { dao.update(note) }
   }
   fun deleteNote(note: Note)= viewModelScope.launch { dao.delete(note) }
}