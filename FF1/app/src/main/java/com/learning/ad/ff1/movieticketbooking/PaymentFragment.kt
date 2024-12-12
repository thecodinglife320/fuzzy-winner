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
import com.learning.ad.ff1.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {
   private var _binding:FragmentPaymentBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.payConfirmBtn.setOnClickListener {
         findNavController().navigate(R.id.action_paymentFragment_to_confirmFragment)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}