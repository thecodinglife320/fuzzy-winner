package com.learning.ad.ff1.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.learning.ad.ff1.MovieViewModel
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.FragmentSeatSelectBinding

class SeatSelectFragment : Fragment() {
   private var _binding:FragmentSeatSelectBinding? = null
   private val binding get() = _binding!!
   private val viewModel:MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSeatSelectBinding.inflate(layoutInflater,container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.seatLoc.observe(viewLifecycleOwner){
         Log.d(TAG1, it.toString())
         when(it){
            viewModel.seatLocationList[0]->binding.radioButton.isChecked = true
            viewModel.seatLocationList[1]->binding.radioButton2.isChecked = true
            viewModel.seatLocationList[2]->binding.radioButton3.isChecked = true
            viewModel.seatLocationList[3]->binding.radioButton4.isChecked = true
            else ->binding.radioButton.isChecked = true
         }
      }

      return binding.root
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.confirmDayBtn.setOnClickListener { findNavController().navigate(R.id.action_seatSelectFragment_to_daySelectFragment) }
   }
   companion object{
      const val TAG1 = "selectedSeat"
   }
}