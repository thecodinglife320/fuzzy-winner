package com.learning.ad.ff1.movieticketbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.learning.ad.ff1.viewmodel.MovieViewModel
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.FragmentTimeSelectBinding

class TimeSelectFragment : Fragment() {
   private var _binding:FragmentTimeSelectBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTimeSelectBinding.inflate(layoutInflater,container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.time.observe(viewLifecycleOwner){
         when(it){
            binding.radioButton.text->binding.radioButton.isChecked = true
            binding.radioButton2.text->binding.radioButton2.isChecked = true
            binding.radioButton3.text->binding.radioButton3.isChecked = true
            binding.radioButton4.text->binding.radioButton4.isChecked = true
            else ->binding.radioButton.isChecked = true
         }
      }
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.confirmDayBtn.setOnClickListener { findNavController().navigate(R.id.action_timeSelectFragment_to_paymentFragment) }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   companion object
}