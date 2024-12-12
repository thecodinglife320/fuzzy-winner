package com.learning.ad.ff1.movieticketbooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.learning.ad.ff1.viewmodel.MovieViewModel
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {
   private var _binding: FragmentIntroBinding? = null
   private val viewModel: MovieViewModel by activityViewModels()
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.seekBar.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
         override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            viewModel.ticketNumber.value = progress
         }
         override fun onStartTrackingTouch(seekBar: SeekBar?) {
         }
         override fun onStopTrackingTouch(seekBar: SeekBar?) {
         }
      })
      binding.buyBtn.setOnClickListener { findNavController().navigate(R.id.action_introFragment_to_seatSelectFragment) }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}