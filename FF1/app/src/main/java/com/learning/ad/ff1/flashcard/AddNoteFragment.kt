package com.learning.ad.ff1.flashcard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.learning.ad.ff1.MyApp
import com.learning.ad.ff1.databinding.FragmentAddNoteBinding
import com.learning.ad.ff1.viewmodel.NoteViewModel
import com.learning.ad.ff1.viewmodel.NoteViewModelFactory
import com.learning.ad.ff1.viewmodel.RepoViewModel
import kotlinx.coroutines.flow.observeOn

class AddNoteFragment : Fragment() {
   private var _binding : FragmentAddNoteBinding? = null
   private val binding get() = _binding!!
   private val viewModel: NoteViewModel by activityViewModels { NoteViewModelFactory((requireActivity().application as MyApp).database.noteDao())
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentAddNoteBinding.inflate(layoutInflater,container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.allNotes.observe(viewLifecycleOwner){
      }
      RepoViewModel()
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding.titleTf.addOnEditTextAttachedListener {
         if (binding.titleEdt.text!!.isNotEmpty()) it.isEndIconVisible = true
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   companion object
}