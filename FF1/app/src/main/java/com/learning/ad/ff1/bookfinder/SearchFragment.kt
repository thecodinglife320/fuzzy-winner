package com.learning.ad.ff1.bookfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.FragmentSearchBinding
import com.learning.ad.ff1.viewmodel.BookApiStatus
import com.learning.ad.ff1.viewmodel.BookViewModel

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

   private var _binding: FragmentSearchBinding? = null
   private val binding get() = _binding!!
   private val viewModel: BookViewModel by activityViewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSearchBinding.inflate(inflater, container, false)
      binding.lifecycleOwner = viewLifecycleOwner
      binding.viewModel = viewModel
      viewModel.status.observe(viewLifecycleOwner) {
         if (it == BookApiStatus.DONE) findNavController().navigate(R.id.action_searchFragment_to_resultFragment)
      }
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.searchView.setOnQueryTextListener(this)
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   override fun onQueryTextSubmit(query: String?): Boolean {
      if (!query.isNullOrBlank()){
         viewModel.searchBooks(query)
         return true
      }else{
         Toast.makeText(requireContext(), "Please enter book name", Toast.LENGTH_SHORT).show()
         return false
      }
   }
   override fun onQueryTextChange(newText: String?): Boolean {
      return false
   }
}