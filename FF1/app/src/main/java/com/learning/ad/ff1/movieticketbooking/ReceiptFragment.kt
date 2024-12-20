package com.learning.ad.ff1.movieticketbooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.learning.ad.ff1.databinding.FragmentReceiptBinding

class ReceiptFragment : Fragment() {
   private var _binding:FragmentReceiptBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentReceiptBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}