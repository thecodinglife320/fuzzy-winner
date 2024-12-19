package com.learning.ad.ff1.flashcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.learning.ad.ff1.databinding.FragmentMyNoteBinding

class MyNoteFragment : Fragment() {
   private var _binding: FragmentMyNoteBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentMyNoteBinding.inflate(layoutInflater,container,false)
      return binding.root
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   companion object
}