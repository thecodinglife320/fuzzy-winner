package com.learning.ad.ff1.movieticketbooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.learning.ad.ff1.viewmodel.MovieViewModel
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.FragmentConfirmBinding

class ConfirmFragment : Fragment() {
   private var _binding:FragmentConfirmBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentConfirmBinding.inflate(layoutInflater, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.calculateTotal()
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.payToBookBtn.setOnClickListener {
         findNavController().navigate(R.id.action_confirmFragment_to_receiptFragment)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null

   }
}