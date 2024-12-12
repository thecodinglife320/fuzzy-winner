package com.learning.ad.ff1.movieticketbooking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.learning.ad.ff1.viewmodel.MovieViewModel
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.FragmentDaySelectBinding

class DaySelectFragment : Fragment() {
   private var _binding: FragmentDaySelectBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentDaySelectBinding.inflate(layoutInflater, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.date.observe(viewLifecycleOwner){
         Log.d(TAG1, viewModel.date.value.toString())
         when(it){
            viewModel.dateList[0]->binding.radioButton.isChecked = true
            viewModel.dateList[1]->binding.radioButton2.isChecked = true
            viewModel.dateList[2]->binding.radioButton3.isChecked = true
            viewModel.dateList[3]->binding.radioButton4.isChecked = true
            else ->binding.radioButton.isChecked = true
         }
      }
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.confirmDayBtn.setOnClickListener {
         findNavController().navigate(R.id.action_daySelectFragment_to_timeSelectFragment)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   companion object{
      const val TAG1 = "selectedDate"
   }
}